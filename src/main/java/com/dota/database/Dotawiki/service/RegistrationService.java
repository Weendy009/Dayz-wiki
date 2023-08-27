package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.Roles;
import com.dota.database.Dotawiki.entity.User;
import com.dota.database.Dotawiki.entity.UserDetails;
import com.dota.database.Dotawiki.entity.UserRoles;
import com.dota.database.Dotawiki.repository.RolesRepository;
import com.dota.database.Dotawiki.repository.UserDetailsRepository;
import com.dota.database.Dotawiki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final JavaMailSender mailSender;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                               UserDetailsRepository userDetailsRepository,
                               JavaMailSender mailSender,
                               RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.mailSender = mailSender;
        this.rolesRepository = rolesRepository;
    }

    public void registerUser(User user) throws MessagingException {
        String token = UUID.randomUUID().toString();
        createUserAndDetails(user, token);

        String confirmationLink = "http://localhost:8080/activeToken/" + token;
        String gifLink = "https://media.tenor.com/Oj6i7LwJdlMAAAAC/youre-welcome.gif";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(user.getEmail());
        helper.setSubject("Подтверждение регистрации");

        String emailBody = generateEmailBody(user.getName(), confirmationLink, gifLink);
        helper.setText(emailBody, true);

        mailSender.send(mimeMessage);
    }

    public void resetPassword(String email) throws MessagingException {
        User user = userRepository.getUserByEmail(email);
        String token = UUID.randomUUID().toString();
        resetPassword(user, token);

        String resetLink = "http://localhost:8080/reset/password/" + token + "?email="+ email;
        String gifLink = "https://media.tenor.com/Oj6i7LwJdlMAAAAC/youre-welcome.gif";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(email);
        helper.setSubject("Reset password");
        String emailBody = generatePasswordResetEmail(user.getName(), resetLink, gifLink);
        helper.setText(emailBody, true);

        mailSender.send(mimeMessage);
    }


    public boolean activateUserByToken(String token) {
        User user = userRepository.getUserByConfirmationToken(token);
        if (user != null) {
            UserDetails userDetails = userDetailsRepository.findByUserId(user.getId());
            if (userDetails != null) {
                userDetails.setEnabled(true);
                userDetails.setActivatedEmail(true);
                userDetailsRepository.save(userDetails);

                user.setConfirmationToken(null);
                userRepository.save(user);

                System.out.println(user);
                System.out.println(user.getId());
                UserRoles roles = new UserRoles();
                roles.setUserId(user.getId());
                roles.setName(Roles.USER);
                rolesRepository.save(roles);
                return true;
            }
        }

        return false;
    }

    private String generatePasswordResetEmail(String userName, String resetLink, String gifLink) {
        String emailBody = "<html><body style=\"font-family: Arial, sans-serif;\">";
        emailBody += "<div style=\"background-color: #f8f8f8; padding: 20px; text-align: center;\">";
        emailBody += "<h1 style=\"margin-bottom: 10px;\">Dotabase</h1>";
        emailBody += "<p style=\"font-size: 18px; margin: 0;\">Hello, " + userName + "!</p>";
        emailBody += "<p style=\"font-size: 16px; margin-top: 10px;\">You've requested a password reset for your Dotabase account.</p>";
        emailBody += "<img src=\"" + gifLink + "\" style=\"max-width: 100%; margin-top: 20px;\">";
        emailBody += "<p style=\"font-size: 16px; margin-top: 20px;\">Click the button below to reset your password:</p>";
        emailBody += "<a href=\"" + resetLink + "\" style=\"display: inline-block; background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; margin-top: 10px;\">RESET PASSWORD</a>";
        emailBody += "</div></body></html>";

        return emailBody;
    }

    private String generateEmailBody(String userName, String confirmationLink, String gifLink) {
        String emailBody = "<html><body style=\"font-family: Arial, sans-serif;\">";
        emailBody += "<div style=\"background-color: #f8f8f8; padding: 20px; text-align: center;\">";
        emailBody += "<h1 style=\"margin-bottom: 10px;\">Dotabase</h1>";
        emailBody += "<p style=\"font-size: 18px; margin: 0;\">Welcome, " + userName + "!</p>";
        emailBody += "<p style=\"font-size: 16px; margin-top: 10px;\">You have just taken your first step towards learning Dota 2. Good job, we are very proud of you.</p>";
        emailBody += "<img src=\"" + gifLink + "\" style=\"max-width: 100%; margin-top: 20px;\">";
        emailBody += "<p style=\"font-size: 16px; margin-top: 20px;\">Click the button below to confirm your email address:</p>";
        emailBody += "<a href=\"" + confirmationLink + "\" style=\"display: inline-block; background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; margin-top: 10px;\">CONFIRM EMAIL</a>";
        emailBody += "</div></body></html>";;

        return emailBody;
    }

    private void resetPassword(User user, String resetToken) {
        user.setResetToken(resetToken);
        userRepository.save(user);
    }

    private void createUserAndDetails(User user, String token) {
        user.setConfirmationToken(token);
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getId());
        userDetails.setActivatedEmail(false);
        userDetailsRepository.save(userDetails);
    }


}
