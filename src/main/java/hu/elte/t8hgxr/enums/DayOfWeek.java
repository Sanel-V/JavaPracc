package hu.elte.t8hgxr.enums;

import java.util.*;

public enum DayOfWeek
{
    MON("hu", "hetfo", "en", "monday"),
    TUE(Map.of("hr", "utorak", "en", "tuesday")),
    WED,
    THU,
    FRI,
    SAT,
    SUN;

 /*    final static List<String> langCodes =
            List.of("en","hu", "hr");
*/
    private Map<String, String> translations = new HashMap<>(){};
    DayOfWeek(){}
    DayOfWeek(String... pairs)
    {
        if(pairs.length % 2 == 0)
        {
            for(int i = 0; i < pairs.length; i+= 2)
            {
                translations.put(pairs[i], pairs[i + 1]);
            }
        }
    }
    DayOfWeek(Map<String, String> pairs)
    {
        translations = pairs;
    }

    public String get(String langCode)
    {   /*
        if(langCodes.contains(langCode))
            return translations.get(langCode);
        return "?";
        */
        String val = translations.get(langCode);
        return val == null ? "?" : val;
    }

    public Optional<String> get2(String langCode)
    {
        String val = translations.get(langCode);
        return val == null ? Optional.empty() : Optional.of(val);
    }

    public DayOfWeek nextDay()
    {
        return values()[(this.ordinal() + 1) % values().length];

    }
}
