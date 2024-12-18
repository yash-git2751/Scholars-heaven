package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Book")
@CrossOrigin(origins = "http://locolhost:4200")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id){
        Optional<Book> book = bookService.getBookById(id);
       if (book.isPresent()){
           return new ResponseEntity<>(book.get(),HttpStatus.OK);
       }else {
           return new ResponseEntity<>("Book Not Found",HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/BookName")
    public ResponseEntity<?> getBybookName(@RequestParam String bookName){
        List<Book> books = bookService.getBookByBookName(bookName);
        if (books.isEmpty()) {
            return new ResponseEntity<>("No Books found with the given BOOK NAME.", HttpStatus.NOT_FOUND);
        }
                return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/Author")
    public ResponseEntity<?> getByAuthor(@RequestParam String author){
        List<Book> books = bookService.getBookByAuthor(author);
        if (books.isEmpty()){
            return new ResponseEntity<>("No books found with the given AUTHOR",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List< Book >> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable String id) {
//        System.out.println("Received delete request for book with id " + id);
//        try {
//            boolean isDeleted = bookService.deleteBook(id);
//            if (isDeleted) {
//                System.out.println("Book deleted successfully");
//                return ResponseEntity.ok("Book deleted successfully");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found");
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while delteing the book");
//        }
//    }
//        boolean isDeleted = bookService.deleteBook(id);
//        if (isDeleted) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        System.out.println(id instanceof String);
//        bookService.deleteBook(id);
//        return ResponseEntity.noContent().build();

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book updatedBook){
       Optional<Book> book = bookService.updateBook(id, updatedBook);
       if (book.isPresent()){
           return new ResponseEntity<>(book.get(),HttpStatus.OK);
       } else {
         return new ResponseEntity<>("No Book found.",HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        String response = bookService.deleteBookById(id);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", response);
  if(response.contains("not found")){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
  }
  return ResponseEntity.ok(responseBody);
    }

}
