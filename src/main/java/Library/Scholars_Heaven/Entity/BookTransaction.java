package Library.Scholars_Heaven.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "return_date")
    private LocalDate returnDate;


    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @Column(name = "is_returned" ,nullable = false)
    private boolean isReturned = false;

    public String getReturnedDateString(){
        return returnedDate !=null ? returnedDate.toString():" yet returned";
    }

//    public boolean isReturned() {
//        return isReturned;
//    }

}
