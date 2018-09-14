package com.langpack;

import java.util.ArrayList;
import java.util.HashMap;

public class Language
{
    private final HashMap<String, ArrayList<String>> languages = new HashMap<>();

    public void registerLanguage(final String language)
    {
        if (!languages.containsKey(language))
        {
            languages.put(language, new ArrayList<>());
        }
    }

    public void registerString(final String language, final String string)
    {

    }
}
