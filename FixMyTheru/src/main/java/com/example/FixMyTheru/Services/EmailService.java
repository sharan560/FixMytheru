package com.example.FixMyTheru.Services;

import com.example.FixMyTheru.Models.Issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Issues issues) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your_email@gmail.com");
        message.setTo(issues.getRegisterDetails().getEmail());
        String subject = "Upadate on Issue Raised @ FixMytheru ";
        String body="Your Complaint "+issues.getIssueDescription()+ " has been Completed succesfully";
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }
}
