package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.BookTransaction;
import Library.Scholars_Heaven.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/book")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestParam Long bookId, @RequestParam Long userId) {
        try {
            BookTransaction transaction = transactionService.issueBook(bookId, userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

        @PostMapping("return")
        public ResponseEntity<?> returnedBook(@RequestParam Long bookId,@RequestParam Long userId){
        try {
            BookTransaction transaction = transactionService.returnedBook(bookId,userId);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }























//    @PostMapping("/{bookId}/issue")
//    public BookTransaction issueBook(@PathVariable Long bookId, @RequestParam Long userId){
//        return transactionService.issueBook(bookId, userId);
//    }
}
