package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Book> getBookById(Long id) {
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

    public boolean deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return true;
        }
        return false;

    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
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