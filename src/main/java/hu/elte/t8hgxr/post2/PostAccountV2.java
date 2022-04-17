package hu.elte.t8hgxr.post2;

import java.util.ArrayList;
import java.util.List;

public class PostAccountV2
{
    public String name;
    public List<MailV2> inbox;

    public PostAccountV2(String name, List<MailV2> inbox)
    {
        this.name = name;
        this.inbox = inbox;
    }
    public PostAccountV2(String name)
    {
        this(name, new ArrayList<MailV2>());
    }

    public void receive(MailV2 mail)
    {
        if(this.name.equals(mail.recipient))
        {
            inbox.add(mail);
        }else
        {
            throw new IllegalStateException(
                String.format("%s received post mailed to: %s", this.name, mail.recipient));
        }
    }

}
