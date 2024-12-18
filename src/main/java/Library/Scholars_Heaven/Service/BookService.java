package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Optional<Book> getBookById(String id) {
        return bookRepo.findById(id);
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepo.findByAuthor(author);
    }

    public List<Book> getBookByBookName(String bookName) {
        return bookRepo.findByBookName(bookName);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

//    public boolean deleteBook(String id) {
//        if (bookRepo.existsById(id)) {
//            bookRepo.deleteById(id);
//            return true;
//        }
//        return false;
//
//    }
    public String deleteBookById(String id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return "Book with ID " + id + " deleted successfully";
//            throw ("Book with id" + id + " not found");
        } else {
            return "Book with ID " + id + " not found";
        }
    }

    public Optional<Book> updateBook(String id, Book updatedBook) {
        Optional<Book> existingBook = bookRepo.findById(id);
        if (existingBook.isPresent()) {
            Book bookUpdate = existingBook.get();
            bookUpdate.setBookName(updatedBook.getBookName());
            bookUpdate.setAuthor(updatedBook.getAuthor());
            bookUpdate.setIsbn(updatedBook.getIsbn());
            bookUpdate.setPublisher(updatedBook.getPublisher());
            bookRepo.save(bookUpdate);
            return Optional.of(bookUpdate);
        }
        return Optional.empty();
    }
}