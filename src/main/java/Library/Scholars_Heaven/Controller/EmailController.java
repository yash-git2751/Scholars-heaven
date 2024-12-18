package Library.Scholars_Heaven.Controller;

import Library.Scholars_Heaven.Entity.Email;
import Library.Scholars_Heaven.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<String> requestBook(@RequestBody Email email){
        emailService.sendBookRequest(email);

        return ResponseEntity.ok("Book request sent to the admin. You will be contacted shortly");
    }

}
