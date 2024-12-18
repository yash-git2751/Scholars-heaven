package Library.Scholars_Heaven.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "books")
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String bookName;
    private String author;
    private String isbn;
    private String publisher;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

//    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<BookTransaction> transactions;
}
//Alter table book_transaction DROP  foreign key <FKrrqve6wymrorebayyh0cdgcg3>;

//        SELECT CONSTRAINT_NAME, TABLE_NAME
//FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
//where REFERENCED_TABLE_NAME = 'books' AND  REFERENCED_COLUMN_NAME = 'id';