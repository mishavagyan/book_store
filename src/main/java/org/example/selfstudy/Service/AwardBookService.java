//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Award;
//import org.example.selfstudy.entity.AwardBook;
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.gto.BookCsvDto;
//import org.example.selfstudy.helper.HelperFunc;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
////@Service
//public class AwardBookService {
//    List<AwardBook> awardBooks = new ArrayList<>();
//
////    public void saveAwardBooks(List<BookCsvDto> dtos) {
////        for (BookCsvDto dto : dtos) {
////
////        }
////    }
//
//    public List<AwardBook> linkAwardBooks(List<BookCsvDto> dtos, List<Book> books, Map<String, Award> awardByName) {
//        AtomicLong idGenerator = new AtomicLong(1);
//        List<AwardBook> result = new ArrayList<>();
//
//        for (int i = 0; i < dtos.size(); i++) {
//            BookCsvDto dto = dtos.get(i);
//            Book book = books.get(i); // assuming same order
//
//            for (String rawAward : HelperFunc.getList(dto.getAwards())) {
//                Matcher m = Pattern.compile("^(.*?)(?:\\s*\\((\\d{4})\\))?$").matcher(rawAward.trim());
//                if (!m.matches()) continue;
//
//                String name = m.group(1).trim();
//                int year = (m.group(2) != null) ? Integer.parseInt(m.group(2)) : -1;
//
//                Award award = awardByName.get(name);
//                if (award == null) continue; // award not found, skip
//
//                AwardBook ab = new AwardBook();
//                ab.setId((int) idGenerator.getAndIncrement());
//                ab.setBookId(book.getBookId());
//                ab.setAwardId(award.getAwardId());
//                ab.setYear(year);
//                result.add(ab);
//            }
//        }
//
//        return result;
//    }
//
//}
