package Library.Scholars_Heaven.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransaction {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Book book;

    @OneToOne
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

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
//    public boolean isReturned() {
//        return isReturned;
//    }

}
