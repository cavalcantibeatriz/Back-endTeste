package sptech.faztudo.comLOCAL.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public void sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
    }
}
