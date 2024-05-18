package sptech.faztudo.comLOCAL.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

   @Autowired
   private JavaMailSender mailSender;

   public void sendEmail(Email email){
      var message = new SimpleMailMessage();
      message.setFrom("noreplayfaztudo.com@gmail.com");
      message.setTo(email.to());
      message.setSubject(email.subject());
      message.setText(email.body());
      mailSender.send(message);
   }
}
