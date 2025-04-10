package com.example.bookproject.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class CsvHelper {

//    public static List<String> parseStringToList(String str) {
//        if (str == null || str.trim().equals("[]")) {
//            return List.of();
//        }
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(str, List.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }

    public static List<String> parseStringToList(String str) {
        if (str == null || str.trim().equals("[]")) {
            return List.of();
        }

        return Arrays.stream(str.substring(1, str.length() - 1)
                        .split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

}
