//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.entity.Setting;
//import org.example.selfstudy.repository.SettingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SettingService {
//    @Autowired
//    private SettingRepository settingRepository;
//
//    public List<Book> findBooksBySettingName(String keyword) {
//        List<Setting> settings = settingRepository.findByNameContainingIgnoreCase(keyword);
//        return settings.stream()
//                .map(Setting::getBook)
//                .distinct() // In case multiple settings link to the same book
//                .toList();
//    }
//}
