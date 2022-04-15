package hu.elte.t8hxgr.post;

public class Mail
{
    public int address;

    public Mail(int address)
    {
        if(address < 0)
        {
            throw new IllegalArgumentException("Address should be non-negative");
        }
        this.address = address;
    }
}
