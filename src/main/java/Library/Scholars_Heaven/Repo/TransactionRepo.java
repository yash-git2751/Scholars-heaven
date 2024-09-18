package Library.Scholars_Heaven.Repo;

import Library.Scholars_Heaven.Entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<BookTransaction,Long> {
    List<BookTransaction> findByUserId(Long userId);
    Optional<BookTransaction> findByBookIdAndUserId(Long bookId,Long userId);
}