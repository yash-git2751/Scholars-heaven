package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Entity.BookTransaction;
import Library.Scholars_Heaven.Entity.User;
import Library.Scholars_Heaven.Repo.BookRepo;
import Library.Scholars_Heaven.Repo.TransactionRepo;
import Library.Scholars_Heaven.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    public BookRepo bookRepo;
    @Autowired
    public TransactionRepo transactionRepo;
    @Autowired
    public UserRepo userRepo;

    public BookTransaction issueBook(String bookId, String userId)throws Exception{
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found with Id " + bookId));

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new Exception("User not found with Id " + userId));

        if (transactionRepo.findByBookIdAndUserId(bookId,userId).isPresent()) {
            throw new Exception("The book with ID" + bookId + " is already issued to the user with id " + userId);
        }

        BookTransaction transaction = new BookTransaction();
        transaction.setBook(book);
        transaction.setUser(user);

        // To set issued date to current date
        LocalDateTime issuedDate = LocalDateTime.now();
        transaction.setIssuedDate(LocalDate.now());

        // To set return date 5 days after the issue date
        LocalDateTime returnDate = issuedDate.plusDays(5);
        transaction.setReturnDate(LocalDate.from(returnDate));

        transaction.setReturned(false);
       String returnedDateString= transaction.getReturnedDateString();
      //  transaction.setReturnedDate(LocalDate.parse("not yet returned"));
        return transactionRepo.save(transaction);

    }

    public BookTransaction returnedBook(String bookId, String userId)throws Exception{
        BookTransaction transaction = transactionRepo.findByBookIdAndUserId(bookId,userId)
                .orElseThrow(() -> new  Exception("not found for book ID "+ bookId + "and user ID " + userId));

        transaction.setReturnedDate(LocalDate.from(LocalDateTime.now()));
        transaction.setReturned(true);
        return transactionRepo.save(transaction);
    }

    public List<BookTransaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
}




























//     public BookTransaction issueBook(Long bookId,long userId){
//         Optional<Book> books = bookRepo.findById(bookId);
//         Optional<User> users = userRepo.findById(userId);
//
//         if (books.isPresent() && users.isPresent()){
//             Book book = books.get();
//             User user = users.get();
//
//             if (!books.isPresent()){
//                 book.setIssued(true);
//                 bookRepo.save(book);
//
//                 BookTransaction transaction = new BookTransaction();
//                 transaction.setBook(book);
//                 transaction.setUser(user);
//                 transaction.setIssueDate(LocalDate.now());
//                 transaction.setTransactionType("Issue");
//
//                 return transactionRepo.save(transaction);
//             } else {
//                 throw new RuntimeException("Book is already issued.");
//             }
//
//         } else {
//                 throw new RuntimeException("Book or User not found");
//             }
//         }

