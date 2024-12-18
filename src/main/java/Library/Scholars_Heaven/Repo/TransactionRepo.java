package Library.Scholars_Heaven.Repo;

import Library.Scholars_Heaven.Entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<BookTransaction,String> {
    List<BookTransaction> findByUserId(String userId);

    List<BookTransaction> findTransactionById(String Id);

    Optional<BookTransaction> findByBookIdAndUserId(String bookId,String userId);
}