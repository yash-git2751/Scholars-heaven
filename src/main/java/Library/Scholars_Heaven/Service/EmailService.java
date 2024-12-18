package Library.Scholars_Heaven.Service;
import Library.Scholars_Heaven.Entity.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookRequest(Email email){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email.getEmail());
        message.setTo("yasshgoud275@gmail.com");
        message.setSubject("Book request from user : " + email.getName());
        message.setText("User "+ email.getName() + " has requested the following book : \n\n"
        + email.getPleaseEnterRequiredBookName() + "\n\nContact Email: " + email.getEmail());
        mailSender.send(message);
    }
}
