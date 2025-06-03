//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Language;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Service
//public class LanguageService {
//
//    private final Map<String, Language> languageMap = new HashMap<>();
//    private final AtomicInteger idGenerator = new AtomicInteger(1);
//
//    public Language getOrCreate(String name) {
//        return languageMap.computeIfAbsent(name.toLowerCase(), key -> {
//           Language language = new Language();
//           language.setId(idGenerator.getAndIncrement());
//           language.setName(name);
//           return language;
//        });
//    }
//
//    public Collection<Language> getAllLanguages() {
//        return languageMap.values();
//    }
//
//}
