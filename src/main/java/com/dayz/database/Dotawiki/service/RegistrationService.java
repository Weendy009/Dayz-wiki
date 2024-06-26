package com.dayz.database.Dotawiki.service;

import com.dayz.database.Dotawiki.entity.users.Roles;
import com.dayz.database.Dotawiki.entity.users.User;
import com.dayz.database.Dotawiki.entity.users.UserDetails;
import com.dayz.database.Dotawiki.entity.users.UserRoles;
import com.dayz.database.Dotawiki.repository.RolesRepository;
import com.dayz.database.Dotawiki.repository.UserDetailsRepository;
import com.dayz.database.Dotawiki.repository.UserRepository;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
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
        UserDetails userDetails = userDetailsRepository.getUserDetailsByConfirmationToken(token);
        if (userDetails != null) {
                userDetails.setEnabled(true);
                userDetails.setActivatedEmail(true);
                userDetails.setConfirmationToken(null);
                userDetailsRepository.save(userDetails);

                UserRoles roles = new UserRoles();
                roles.setUserId(userDetails.getUserId());
                roles.setName(Roles.USER);
                rolesRepository.save(roles);
                return true;
        }

        return false;
    }

    private String generatePasswordResetEmail(String userName, String resetLink, String gifLink) {
        String emailBody = "<html><body style=\"font-family: Arial, sans-serif;\">";
        emailBody += "<div style=\"background-color: #f8f8f8; padding: 20px; text-align: center;\">";
        emailBody += "<h1 style=\"margin-bottom: 10px;\">Dayz-wiki</h1>";
        emailBody += "<p style=\"font-size: 18px; margin: 0;\">Hello, " + userName + "!</p>";
        emailBody += "<p style=\"font-size: 16px; margin-top: 10px;\">You've requested a password reset for your Dayz-wiki account.</p>";
        emailBody += "<img src=\"" + gifLink + "\" style=\"max-width: 100%; margin-top: 20px;\">";
        emailBody += "<p style=\"font-size: 16px; margin-top: 20px;\">Click the button below to reset your password:</p>";
        emailBody += "<a href=\"" + resetLink + "\" style=\"display: inline-block; background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; margin-top: 10px;\">RESET PASSWORD</a>";
        emailBody += "</div></body></html>";

        return emailBody;
    }

    private String generateEmailBody(String userName, String confirmationLink, String gifLink) {
        String emailBody = "<html><body style=\"font-family: Arial, sans-serif;\">";
        emailBody += "<div style=\"background-color: #f8f8f8; padding: 20px; text-align: center;\">";
        emailBody += "<h1 style=\"margin-bottom: 10px;\">Dayz-wiki</h1>";
        emailBody += "<p style=\"font-size: 18px; margin: 0;\">Welcome, " + userName + "!</p>";
        emailBody += "<p style=\"font-size: 16px; margin-top: 10px;\">You have just taken your first step towards learning Dayz. Good job, we are very proud of you.</p>";
        emailBody += "<img src=\"" + gifLink + "\" style=\"max-width: 100%; margin-top: 20px;\">";
        emailBody += "<p style=\"font-size: 16px; margin-top: 20px;\">Click the button below to confirm your email address:</p>";
        emailBody += "<a href=\"" + confirmationLink + "\" style=\"display: inline-block; background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; margin-top: 10px;\">CONFIRM EMAIL</a>";
        emailBody += "</div></body></html>";;

        return emailBody;
    }

    private void resetPassword(User user, String resetToken) {
        UserDetails userDetails = userDetailsRepository.findByUserId(user.getId());
        userDetails.setResetToken(resetToken);
        userDetailsRepository.save(userDetails);
    }

    private void createUserAndDetails(User user, String token) {
        userRepository.save(user);
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getId());
        userDetails.setActivatedEmail(false);
        userDetails.setConfirmationToken(token);
        userDetails.setCreateDate(LocalDateTime.now());
        userDetailsRepository.save(userDetails);
    }

}
