//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Format;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Service
//public class FormatService {
//
//    private final Map<String, Format> formatMap = new HashMap<>();
//    private final AtomicInteger idGenerator = new AtomicInteger(1);
//
//    public Format getOrCreate(String name) {
//        return formatMap.computeIfAbsent(name.toLowerCase(), key -> {
//           Format format = new Format();
//           format.setId(idGenerator.getAndIncrement());
//           format.setName(name);
//           return format;
//        });
//    }
//
//    public Collection<Format> getAllFormats() {
//        return formatMap.values();
//    }
//
//}
