package org.example.selfstudy.Service;


import org.example.selfstudy.entity.Award;
import org.example.selfstudy.entity.AwardBook;
import org.example.selfstudy.gto.BookCsvDto;
import org.example.selfstudy.helper.HelperFunc;
import org.example.selfstudy.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

@Service
public class AwardService {

    @Autowired
    AwardRepository awardRepository;

    public void saveAwards(List<BookCsvDto> dtos) {
        Set<Award> awards = new HashSet<>();
        for (BookCsvDto dto : dtos) {
            for (String s : HelperFunc.getList(dto.getAwards())) {
                String name = s.replaceAll("\\s*\\(\\d{4}\\)", "").trim();
                if (!name.isBlank()) {
                    Award a = new Award();
                    a.setName(name);
                    awards.add(a);
                }
            }
        }

        awardRepository.saveAll(awards);
    }

//    public Award convertToEntity(String s) {
//
//    }

//    public Award convertToEntity(BookCsvDto dto) {
//        for (String s : HelperFunc.getList(dto.getAwards())) {
//            String name = s.replaceAll("\\s*\\(\\d{4}\\)", "").trim();
//            if (!name.isBlank()) {
//                Award a = new Award();
//                a.setName(name);
//                return a;
//            }
//        }
//    }

//    public Map.Entry<String, Integer> parseNameAndYear(String s) {
//        if (s == null || s.isBlank()) {
//            return null; // or throw IllegalArgumentException
//        }
//
//        // Try to match "Name (2023)"
//        Pattern pattern = Pattern.compile("^(.*?)\\s*\\((\\d{4})\\)\\s*$");
//        Matcher matcher = pattern.matcher(s.trim());
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
//            return Map.entry(nameOnly, null);
//        }
//
//        return null; // or Optional.empty() if you prefer
//    }



}


//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Award;
//import org.example.selfstudy.gto.BookCsvDto;
//import org.example.selfstudy.helper.HelperFunc;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Service
//public class AwardService {
//
//    private final Set<Award> awards = new HashSet<>();
//    private Map<String, Award> awardByNameMap = new HashMap<>();
//
//    public Set<Award> getAwards() {
//        return awards;
//    }
//
//    public Map<String, Award> getAwardByNameMap() {
//        return awardByNameMap;
//    }
//
//    public void saveAwards(List<BookCsvDto> dtos) {
//        AtomicLong idGenerator = new AtomicLong(1); // add ID if needed
//
//        for (BookCsvDto dto : dtos) {
//            for (String s : HelperFunc.getList(dto.getAwards())) {
//                String name = s.replaceAll("\\s*\\(\\d{4}\\)", "").trim();
//                if (!name.isBlank()) {
//                    Award a = new Award();
//                    a.setName(name);
//                    a.setAwardId((int) idGenerator.getAndIncrement()); // if needed
//                    awards.add(a);
//                }
//            }
//        }
//
//        // 👇 Set the map for quick lookup
//        awardByNameMap = awards.stream()
//                .collect(Collectors.toMap(Award::getName, Function.identity()));
//    }
//}
//
////@Service
////public class AwardService {
////
////    Set<Award> awards = new HashSet<>();
////
////    public Set<Award> getAwards() {
////        return awards;
////    }
////
////    public void saveAwards(List<BookCsvDto> dtos) {
////        for (BookCsvDto dto : dtos) {
////            for (String s : HelperFunc.getList(dto.getAwards())) {
////                String res = s.replaceAll("\\s*\\(\\d{4}\\)", "");
////                if (!(res.isEmpty() || res.isBlank())) {
////                    Award a = new Award();
////                    a.setName(res);
////                    awards.add(a);
////                }
////            }
////        }
////    }
////
////}
//
