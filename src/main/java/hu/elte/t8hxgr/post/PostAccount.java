package hu.elte.t8hxgr.post;

import java.util.ArrayList;
import java.util.List;

public class PostAccount
{
    public String name;
    public List<Mail> storedMail;
    public PostAccount(String name, List<Mail> storedMail)
    {
        this.name = name;
        this.storedMail = storedMail;
    }
    public PostAccount(String name)
    {
        this(name, new ArrayList<Mail>());
    }
    public PostAccount()
    {
        this("defname", new ArrayList<Mail>());
    }
    public void receive(Mail mail)
    {
        storedMail.add(mail);
    }
}
