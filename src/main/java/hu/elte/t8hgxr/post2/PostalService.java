package hu.elte.t8hgxr.post2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import hu.elte.t8hgxr.enums.*;

//TODO: test PostalService
public class PostalService
{
    public Set<PostOfficeV2> offices;

    public PostalService(Set<PostOfficeV2> offices)
    {
        this.offices = offices;
    }
    public PostalService()
    {
        this(new HashSet<PostOfficeV2>());
    }

    public void send(City sendFrom, DayOfWeek date, MailV2 mail)
    {
        mail.date = date;
        Optional<PostOfficeV2> processAt = getOfficeAt(sendFrom);
        while(processAt.isPresent())
        {
            Optional<City> whereToForward = processAt.get().process(mail);
            processAt = getOfficeAt(whereToForward);
        }

    }

    public Optional<PostOfficeV2> getOfficeAt(City city)
    {
        return offices.stream()
            .filter(office -> office.location.equals(city))
            .findFirst();
    }
    public Optional<PostOfficeV2> getOfficeAt(Optional<City> city)
    {
        if(city.isEmpty())
        {
            return Optional.empty();
        }
        return getOfficeAt(city.get());
    }
    public void registerOffice(City city)
    {
        Optional<PostOfficeV2> withSameLocation =
            offices.stream()
                .filter(office -> office.location.equals(city))
                .findFirst();

        if(withSameLocation.isPresent())
        {
            throw new IllegalArgumentException(
                String.format("There's a post office in %s already", city.toString()));
        }

        offices.add(new PostOfficeV2(city));
    }

    public void registerAccountToOffice(String accountName, City city)
    {
        getOfficeAt(city)
            .orElseThrow(
                () ->
                {
                    throw new RuntimeException("No post office in: " + city.toString());
                })
            .registerAccount(accountName);
    }

    public void registerAccountToOffice(String accountName, int address)
    {
        City city = City.get(address);
        registerAccountToOffice(accountName, city);
    }
    public void addForwardAddressToOffice(City location, City destination, City whereToSend)
    {
        PostOfficeV2 foundOffice =
            offices.stream()
                .filter(office -> office.location.equals(location))
                .findFirst()
                .orElseThrow(() ->
                 {
                     throw new IllegalArgumentException("No post office in: " + location.toString());
                 });

        foundOffice.addForwardAddress(destination, whereToSend);
    }

    public void addForwardAddressToOffice(int location, int destination, int whereToSend)
    {
        City loc = City.get(location);
        City dest = City.get(destination);
        City wToSend = City.get(whereToSend);
        addForwardAddressToOffice(loc, dest, wToSend);
    }
    public static void main(String[] args) throws IOException
    {
        PostalService postalService = new PostalService();

        String officeRoutes = "src/main/resources/office_connections.txt";;
        String accounts = "src/main/resources/accounts.txt";
        String mails = "src/main/resources/mails.txt";

        //Setup offices
        for (City city : City.values())
        {
            postalService.registerOffice(city);
        }
        Files.lines(Paths.get(accounts))
            .skip(1)
            .forEach(line ->
                 {
                     String[] addressAndAccName = line.split(" ");
                     int address = Integer.parseInt(addressAndAccName[0]);
                     String name = addressAndAccName[1];
                     postalService
                         .registerAccountToOffice(name, address);
                 });

        Files.lines(Paths.get(officeRoutes))
             .skip(1)
             .forEach(line ->
                  {
                      String[] locDestWhere = line.split(" ");
                      int loc = Integer.parseInt(locDestWhere[0]);
                      int dest = Integer.parseInt(locDestWhere[1]);
                      int where = Integer.parseInt(locDestWhere[2]);
                      postalService
                          .addForwardAddressToOffice(loc, dest, where);
                  });

        Files.lines(Paths.get(mails))
             .skip(1)
             .forEach(line ->
                  {
                      String[] startDestAcc = line.split(" ");
                      int start = Integer.parseInt(startDestAcc[0]);
                      int dest = Integer.parseInt(startDestAcc[1]);
                      String acc = startDestAcc[2];
                      postalService
                          .send(City.get(start), DayOfWeek.MON, new MailV2(acc, City.get(dest)));
                  });
    }
}
