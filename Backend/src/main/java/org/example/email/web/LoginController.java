package org.example.email.web;


import jakarta.mail.internet.MimeMessage;
import org.example.email.Repository.RepositoryUser;
import org.example.email.entites.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin

@RequestMapping("/api")
public class LoginController {

    @Autowired
    private RepositoryUser userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginRequest) {
        Optional<Users> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok().body("{\"success\": true}");
            } else {
                return ResponseEntity.status(401).body("{\"success\": false, \"message\": \"Invalid credentials\"}");
            }
        } else {
            return ResponseEntity.status(401).body("{\"success\": false, \"message\": \"Invalid credentials\"}");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////


    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> resetCodes = new HashMap<>();

    @PostMapping("/forgot-password")
    public ResponseEntity<?> sendResetCode(@RequestBody Users userRequest) {
        Optional<Users> userOpt = userRepository.findByEmail(userRequest.getEmail());

        if (userOpt.isPresent()) {
            String resetCode = UUID.randomUUID().toString().substring(0, 6); // Generate a 6-character code
            resetCodes.put(userRequest.getEmail(), resetCode);

            // Send reset code to user's email
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setTo(userRequest.getEmail());
                helper.setSubject("Password Reset Code");

                // HTML message with the reset code in bold
                String htmlMsg = "<p>Hello,</p>"
                        + "<p>You requested to reset your password. Here is your reset code:</p>"
                        + "<h3><strong>" + resetCode + "</strong></h3>"  // Bold code
                        + "<p>Please use this code to reset your password.</p>"
                        + "<p>If you didn't request a password reset, you can ignore this email.</p>"
                        + "<p>Best regards,<br>VenteX Team</p>";

                helper.setText(htmlMsg, true); // true indicates HTML content

                mailSender.send(mimeMessage);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"Failed to send email\"}");
            }

            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Reset code sent to your email\"}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"User not found\"}");
        }
    }
    @PostMapping("/verify-reset-code")
    public ResponseEntity<?> verifyResetCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");

        if (resetCodes.containsKey(email) && resetCodes.get(email).equals(code)) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Code verified\"}");
        } else {
            return ResponseEntity.status(400).body("{\"success\": false, \"message\": \"Invalid code\"}");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        Optional<Users> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Password updated\"}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"User not found\"}");
        }
    }
}
