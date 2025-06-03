//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.entity.Character;
//import org.example.selfstudy.entity.Setting;
//import org.example.selfstudy.repository.CharacterRepository;
//import org.example.selfstudy.repository.SettingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CharacterService {
//    @Autowired
//    private CharacterRepository characterRepository;
//
//    public List<Book> findBooksBySettingName(String keyword) {
//        List<Character> settings = characterRepository.findByNameContainingIgnoreCase(keyword);
//        return settings.stream()
//                .map(Character::getBook)
//                .distinct() // In case multiple settings link to the same book
//                .toList();
//    }
//}
