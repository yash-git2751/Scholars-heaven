package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Service.AuthService;
import Library.Scholars_Heaven.dto.AuthRequest;
import Library.Scholars_Heaven.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Au")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint to authenticate a user.
     *
     * @param request The authentication request containing email and password.
     * @return The authentication response with message and role.
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }


}
