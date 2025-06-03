package org.example.selfstudy.helper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperFunc {
    public static List<String> getList(String raw) {
        List<String> results = new ArrayList<>();

        // This regex matches values inside single or double quotes
        Pattern pattern = Pattern.compile("'([^']*)'|\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(raw);

        while (matcher.find()) {
            String match = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            results.add(match.trim());
        }

        return results;
    }

//    public static Map.Entry<String, Integer> parseNameAndYear(String s) {
//        if (s == null || s.isBlank()) {
//            return null; // or throw IllegalArgumentException
//        }
//
//        // Try to match "Name (2023)"
//        Pattern pattern = Pattern.compile("^(.*?)\\s*\\((\\d{4})\\)\\s*$");
//        Matcher matcher = pattern.matcher(s.trim());
//
//
//        if (matcher.matches()) {
//            String name = matcher.group(1).trim();
//            int year = Integer.parseInt(matcher.group(2));
//
//            if (!name.isBlank()) {
//                return Map.entry(name, year);
//            }
//        }
//
//        // If no year found, just return trimmed name
//        String nameOnly = s.replaceAll("\\s*\\(\\d{4}\\)", "").trim();
//        if (!nameOnly.isBlank()) {
////            return Map.entry(nameOnly, null);
//            return new AbstractMap.SimpleEntry<>(nameOnly, null);
//        }
//
//        return null; // or Optional.empty() if you prefer
//    }



    public static List<Map.Entry<String, Integer>> parseAwards(String input) {
        List<Map.Entry<String, Integer>> results = new ArrayList<>();

        if (input == null || input.isBlank()) {
            return results;
        }

        // Extract final year at end (e.g. "... (1988)")
        Pattern yearAtEndPattern = Pattern.compile("\\((\\d{4})\\)\\s*$");
        Matcher yearEndMatcher = yearAtEndPattern.matcher(input.trim());

        Integer finalYear = null;
        if (yearEndMatcher.find()) {
            finalYear = Integer.parseInt(yearEndMatcher.group(1));
            // Remove the final year from the input string
            input = input.substring(0, yearEndMatcher.start()).trim();
        }

        // Split safely on comma or ampersand not inside parentheses or quotes
        List<String> parts = splitAwards(input);

        for (String part : parts) {
            Map.Entry<String, Integer> entry = parseNameAndYear(part.trim());

            // If no year found in part and final year exists, apply final year
            if (entry != null && entry.getValue() == null && finalYear != null) {
                entry = new AbstractMap.SimpleEntry<>(entry.getKey(), finalYear);
            }

            if (entry != null) {
                results.add(entry);
            }
        }

        return results;
    }

    private static List<String> splitAwards(String input) {
        List<String> parts = new ArrayList<>();
        int depth = 0;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(' || c == '"') {
                depth++;
            } else if (c == ')' || c == '"') {
                depth = Math.max(0, depth - 1);
            }

            if ((c == ',' || c == '&') && depth == 0) {
                parts.add(current.toString());
                current.setLength(0);
                continue;
            }

            current.append(c);
        }

        if (!current.isEmpty()) {
            parts.add(current.toString());
        }

        return parts;
    }

    public static Map.Entry<String, Integer> parseNameAndYear(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }

        Pattern pattern = Pattern.compile("^(.*?)\\s*\\((\\d{4})\\)\\s*$");
        Matcher matcher = pattern.matcher(s.trim());

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            int year = Integer.parseInt(matcher.group(2));

            if (!name.isBlank()) {
                return Map.entry(name, year);
            }
        }

        String nameOnly = s.replaceAll("\\s*\\(\\d{4}\\)", "").trim();
        if (!nameOnly.isBlank()) {
            return new AbstractMap.SimpleEntry<>(nameOnly, null);
        }

        return null;
    }

    public static int extractNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0; // or throw an exception, depending on your use case
        }

        try {
            return Integer.parseInt(input.split(" ")[0]);
        } catch (NumberFormatException e) {
            return 0; // fallback or handle error
        }
    }

    public static String[] splitByCommas(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0]; // Return empty array
        }

        return input.split("\\s*,\\s*"); // Trim spaces around commas
    }

}
