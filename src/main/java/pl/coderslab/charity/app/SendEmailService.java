package pl.coderslab.charity.app;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String body, String subject) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom("charity.put.in.good.hands@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public void sendEmailToMyselfFromContactForm(String body, String sender) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("charity.put.in.good.hands@gmail.com");
        simpleMailMessage.setTo("charity.put.in.good.hands@gmail.com");
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject("Wiadomość od "+sender);
        javaMailSender.send(simpleMailMessage);
    }

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("charity.put.in.good.hands@gmail.com");
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(body);
//        javaMailSender.send(simpleMailMessage);

}
