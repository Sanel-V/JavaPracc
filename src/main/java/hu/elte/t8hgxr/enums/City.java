package hu.elte.t8hgxr.enums;

public enum City
{
    BUDAPEST(1),
    OSIJEK(2),
    WARSAW(3),
    TOKYO(4),
    PARIS(TOKYO);

    final int address;

    City(int address)
    {
        this.address = address;
    }

    City(City other)
    {
        this(other.address);
    }


    @Override
    public String toString()
    {
        return name() + ": " + address;
    }
}