package hu.elte.t8hgxr.post2;

import hu.elte.t8hgxr.enums.City;

import java.util.*;

public class PostOfficeV2
{
    //ids must be unique
    public List<PostAccountV2> accounts;

    //Which address to forward mail with given destination address
    public Map<City, City> whereToForwardMail;
    public City location;
    public PostOfficeV2(City location, List<PostAccountV2> accounts, Map<City, City> whereToForwardMail)
    {
        this.location = location;
        this.accounts = accounts;
        this.whereToForwardMail = whereToForwardMail;
    }
    public PostOfficeV2(City location)
    {
        this(location, new ArrayList<PostAccountV2>(), new HashMap<City, City>());
    }

    public Optional<City> process(MailV2 mail)
    {
        mail.sign(this.location);
        if(this.location.equals(mail.destination))
        {
            //Send to proper account
            Optional<PostAccountV2> recipient =
                accounts.stream()
                    .filter(account -> account.name.equals(mail.recipient))
                    .findFirst();
            //Send to account at destination, otherwise throw away
            if(recipient.isPresent())
            {
                recipient.get().receive(mail);
            }
            return Optional.empty();
        }
        mail.date = mail.date.nextDay();
        City toForward = whereToForwardMail.get(mail.destination);
        if(toForward == null)
        {
            return Optional.empty();
        }
        return Optional.of(toForward);
    }

    public void registerAccount(String name)
    {
        //We could override 'equals' in PostAccountV2 to compare names.
        //While that would be logically sound for this task,
        //it would be technically inaccurate
        //(two offices could have accounts with same name,
        //yet represent differing accounts)

        Optional<PostAccountV2> withSameName =
            accounts.stream()
                .filter(account -> account.name.equals(name))
                .findFirst();

        if(withSameName.isPresent())
        {
            throw new IllegalArgumentException(
                    String.format("Account with name: %s already exists.", name));
        }
        accounts.add(new PostAccountV2(name));
    }

    public void addForwardAddress(City destination, City whereToSend)
    {
        whereToForwardMail.put(destination, whereToSend);
    }
    public void addForwardAddress(int destination, int whereToSend)
    {
        whereToForwardMail.put(City.get(destination), City.get(whereToSend));
    }

    public void removeForwardAddress(City destination)
    {
        whereToForwardMail.remove(destination);
    }

    public void removeForwardAddress(int destination)
    {
        whereToForwardMail.remove(City.get(destination));
    }
}
