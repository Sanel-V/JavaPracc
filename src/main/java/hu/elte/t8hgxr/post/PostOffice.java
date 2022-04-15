package hu.elte.t8hgxr.post;

public class PostOffice
{
    //accountOne stores mail with even address, accountTwo with odd
    public PostAccount accountOne;
    public PostAccount accountTwo;

    public PostOffice(PostAccount accountOne, PostAccount accountTwo)
    {
        this.accountOne = accountOne;
        this.accountTwo = accountTwo;
    }
    public PostOffice()
    {
        this(new PostAccount("first"), new PostAccount("second"));
    }
    public void sort(Mail mail)
    {
        if(mail.address % 2 == 0)
        {
            accountOne.receive(mail);
        }else
        {
            accountTwo.receive(mail);
        }

    }
}
