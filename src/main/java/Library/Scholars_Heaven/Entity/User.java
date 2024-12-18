package Library.Scholars_Heaven.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
  * User entity class representing the user and admin details
  */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Email
   // @NotBlank
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    //@NotBlank
    private String password;

    //@NotBlank
    private String phoneNumber;
    
   // @NotBlank
    private String role;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<BookTransaction> transactions;
}
