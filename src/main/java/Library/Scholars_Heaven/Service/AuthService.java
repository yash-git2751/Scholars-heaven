package Library.Scholars_Heaven.Service;

import Library.Scholars_Heaven.Entity.User;
import Library.Scholars_Heaven.Repo.UserRepo;
import Library.Scholars_Heaven.dto.AuthRequest;
import Library.Scholars_Heaven.dto.AuthResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    /**
     * Authentication a user based on their email and password.
     *
     * @param request The authentication request containing email and password.
     * @return The authentication response with message and role
     */

    public AuthResponse authenticate(@NotNull AuthRequest request){
        Optional<User> userOptional = userRepo.findByEmail(request.getEmail());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (user.getPassword().equals(request.getPassword())){
                AuthResponse response = new AuthResponse();
                response.setMessage("Login successful");
                response.setRole(user.getRole());
                return response;
            }
        }
        AuthResponse response = new AuthResponse();
            response.setMessage("Invalid Email or Password");
        response.setRole("user/admin");
        return response;
        }
}
