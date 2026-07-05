package com.apelisser.algashop.product.catalog.infrastructure.utility;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class Slugfier {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private Slugfier() {
        throw new AssertionError("Utility class");
    }

    public static String slugify(String text) {
        if (text == null) {
            return null;
        }

        if (text.isEmpty()) {
            return text;
        }

        String noWhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
