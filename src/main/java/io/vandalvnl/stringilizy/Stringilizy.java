package io.vandalvnl.stringilizy;

import java.util.StringTokenizer;
import java.util.regex.Matcher;

public abstract class Stringilizy {
    public static String truncate(String string, int length, String end) {
        if (string.length() <= length) {
            return string;
        }
        String safeEnd = end.trim();
        return string.substring(0, length - safeEnd.length()).concat(safeEnd);
    }

    public static String truncate(String string, int length) {
        return truncate(string, length, "...");
    }

    public static String capitalizeWords(String words) {
        if (words.length() == 0) {
            return words;
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(words, " \t\n", true);
        while (tokens.hasMoreTokens()) {
            String slice = tokens.nextToken();
            stringBuilder.append(slice.substring(0, 1).toUpperCase().concat(slice.substring(1).toLowerCase()));
        }
        return stringBuilder.toString().trim();
    }

    public static String brazilize(final String words) {
        return capitalizeWords(words).replaceAll(" E ", " e ").replaceAll(" Da ", " da ").replaceAll(" Das ", " das ")
                .replaceAll(" De ", " de ").replaceAll(" Del ", " del ").replaceAll(" Do ", " do ")
                .replaceAll(" Dos ", " dos ");
    }

    public static String toPascalCase(final String string) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = RegexMatcher.getMatcher(string);
        while (matcher.find()) {
            stringBuilder.append(matcher.group(0).substring(0, 1).toUpperCase())
                    .append(matcher.group(0).substring(1).toLowerCase());
        }
        return String.valueOf(stringBuilder).trim();
    }

    public static String toCamelCase(String string) {
        String camelCase = toPascalCase(string);
        return camelCase.substring(0, 1).toLowerCase().concat(camelCase.substring(1));
    }

    public static String toSneakCase(final String string) {
        Matcher matcher = RegexMatcher.getMatcher(string);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group(0).toLowerCase().concat("_"));
        }
        return stringBuilder.toString().replaceAll("_$", "");
    }

    public static String toSlugCase(final String string) {
        StringBuilder n = new StringBuilder();
        Matcher matcher = RegexMatcher.getMatcher(string);
        while (matcher.find()) {
            n.append(matcher.group(0).toLowerCase().concat("-"));
        }
        return n.toString().replaceAll("-$", "");
    }

    public static Boolean isBlank(final String string) {
        return string.replaceAll(" ", "").replaceAll("\t", "").trim().length() == 0;
    }

    public static String fullTrim(final String string) {
        return string.replace("[ ]+", " ").replaceAll("[\t]+", "\t").trim();
    }

    public static String trim(final String string, final String target) {
        return string.replaceAll(target, "");
    }

    public static String[] words(final String string) {
        return string.split("\\s+");
    }

    public static String escapeHTML(String string) {
        for (int i = 0; i < RegexMatcher.HTML_ESCAPED.length; i++) {
            string = string.replaceAll(RegexMatcher.HTML_UNSAFE[i], RegexMatcher.HTML_ESCAPED[i]);
        }
        return string;
    }

    public static String purgeHTML(final String string) {
        return string.replaceAll("<[^>]*>", "");
    }

    public static String reverse(final String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = string.length(); i > 0; i--) {
            builder.append(string.charAt(i));
        }
        return builder.toString();
    }

    public static String getter2prop(final String getter) {
        return getter.startsWith("get") ? toCamelCase(getter.replaceAll("^get", "")) : toCamelCase(getter);
    }
}
