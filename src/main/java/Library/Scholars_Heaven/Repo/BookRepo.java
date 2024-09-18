package Library.Scholars_Heaven.Repo;

import Library.Scholars_Heaven.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findByBookName(String bookName);
  List<Book> findByAuthor(String author);
    Optional<Book> findById(Long Id);

}
