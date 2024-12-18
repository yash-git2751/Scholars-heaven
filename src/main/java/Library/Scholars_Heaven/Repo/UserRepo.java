package Library.Scholars_Heaven.Repo;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User repository interface for database operations on user entity.
 */
public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    @NotNull Optional<User> findById(String Id);
}
