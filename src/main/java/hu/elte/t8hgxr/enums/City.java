package hu.elte.t8hgxr.enums;

import java.util.Arrays;

public enum City
{
    BUDAPEST(1),
    OSIJEK(2),
    WARSAW(3),
    TOKYO(4),
    PARIS(TOKYO);

    public final int address;

    City(int address)
    {
        this.address = address;
    }

    City(City other)
    {
        this(other.address);
    }

    public static City get(int address)
    {
        return Arrays.stream(values())
            .filter(city -> city.address == address)
            //This will never return PARIS
            .findFirst()
            .orElseThrow(() ->
            {
                throw new EnumConstantNotPresentException(
                    City.class, "No city with address: " + address);
            });
    }

    @Override
    public String toString()
    {
        return name() + ": " + address;
    }
}