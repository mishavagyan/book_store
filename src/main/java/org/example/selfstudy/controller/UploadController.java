package org.example.selfstudy.controller;

import org.example.selfstudy.Service.*;
import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.entity.Setting;
import org.example.selfstudy.gto.BookCsvDto;
import org.example.selfstudy.helper.HelperFunc;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final CsvHandlerService csvHandlerService;
    private final BookService bookService;
    private final AwardService awardService;
//    private final SettingService settingService;
//    private final CharacterService characterService;

//    public UploadController(CsvHandlerService csvHandlerService, BookService bookService, AwardService awardService, SettingService settingService, CharacterService characterService) {
//        this.csvHandlerService = csvHandlerService;
//        this.bookService = bookService;
//        this.awardService = awardService;
//        this.settingService = settingService;
//        this.characterService = characterService;
//    }

    public UploadController(CsvHandlerService csvHandlerService, BookService bookService, AwardService awardService) {
        this.csvHandlerService = csvHandlerService;
        this.bookService = bookService;
        this.awardService = awardService;
    }

    @PostMapping("/csv")
    public ResponseEntity<?> handleUpload(@RequestParam("file") MultipartFile file) throws IOException {
        csvHandlerService.handleUpload(file);
        List<BookCsvDto> dtos = csvHandlerService.getData();

//        bookService.saveBook(dtos);
//        awardService.saveAwards(dtos);
        csvHandlerService.some(dtos);
        return ResponseEntity.ok("Imported successfully");
    }

    @GetMapping("/get-dtos")
    public ResponseEntity<?> getDtos() {
        List<BookCsvDto> dtos = csvHandlerService.getData();
        if (dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data");
        }
        return ResponseEntity.ok(dtos);
    }

//    @GetMapping("/setting/{name}")
//    public ResponseEntity<?> getBooksWithSetting(@PathVariable String name) {
//        List<Book> books = settingService.findBooksBySettingName(name);
//        return ResponseEntity.ok(books);
//    }
//
//    @GetMapping("/character/{name}")
//    public ResponseEntity<?> getBooksWithCharacter(@PathVariable String name) {
//        List<Book> books = characterService.findBooksBySettingName(name);
//        return ResponseEntity.ok(books);
//    }

}
