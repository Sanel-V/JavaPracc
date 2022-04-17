package hu.elte.t8hgxr.post2;

import hu.elte.t8hgxr.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MailV2
{
    public City destination;
    public String recipient;
    public List<City> signatures;
    public DayOfWeek date;

    public MailV2(String recipient, City destination, List<City> signatures)
    {
        this.recipient = recipient;
        this.destination = destination;
        this.signatures = signatures;
    }

    public MailV2(String recipient, City destination)
    {
        this(recipient, destination, new ArrayList<City>());
    }

    public void sign(City city)
    {
        signatures.add(city);
    }
}
