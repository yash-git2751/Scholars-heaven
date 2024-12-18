package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.Book;
import Library.Scholars_Heaven.Entity.User;
import Library.Scholars_Heaven.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Adds a new user to the database.
     * Checks if the email already exists before saving.
     *
     * @param user The user to be added
     * @return The saved user
     * @throws IllegalArgumentException If the email already exits
     */
    public User addUser(User user){
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("Email Already Exits");
        }
        return userRepo.save(user);
    }

    public List<User> getAllusers() {
        return userRepo.findAll();
    }

    public String deleteUserById(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return "User with ID " + id + " deleted successfully";
//            throw ("Book with id" + id + " not found");
        } else {
            return "User with ID " + id + " not found";
        }
    }

    public Optional<User> updateUser(String id, User updatedUser) {
        Optional<User> existingUser = userRepo.findById(id);
        if(existingUser.isPresent()){
            User userUpdate = existingUser.get();
//            userUpdate.setId(updatedUser.getId());
            userUpdate.setName(updatedUser.getName());
            userUpdate.setEmail(updatedUser.getEmail());
            userUpdate.setPassword(updatedUser.getPassword());
            userUpdate.setPhoneNumber(updatedUser.getPhoneNumber());
            userUpdate.setRole(updatedUser.getRole());
        }
        return Optional.empty();
    }



    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user.
     * @return The user if found.
     */
    public Optional<User> getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
