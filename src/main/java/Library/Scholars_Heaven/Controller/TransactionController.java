package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.BookTransaction;
import Library.Scholars_Heaven.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestParam String bookId, @RequestParam String userId) {
        try {
            BookTransaction transaction = transactionService.issueBook(bookId, userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

        @PostMapping("return")
        public ResponseEntity<?> returnedBook(@RequestParam String bookId, @RequestParam String userId){
        try {
            BookTransaction transaction = transactionService.returnedBook(bookId,userId);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

    @GetMapping("transactions")
    public ResponseEntity<List<BookTransaction>> getAllTransactions(){
    List<BookTransaction> transactions = transactionService.getAllTransactions();
    return ResponseEntity.ok(transactions);
    }
}