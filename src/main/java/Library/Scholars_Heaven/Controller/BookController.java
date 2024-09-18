package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
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

    @GetMapping("/Books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestBody Book book){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
       Optional<Book> book = bookService.updateBook(id, updatedBook);
       if (book.isPresent()){
           return new ResponseEntity<>(book.get(),HttpStatus.OK);
       } else {
         return new ResponseEntity<>("No Book found.",HttpStatus.NOT_FOUND);
       }
    }

}
