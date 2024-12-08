package com.example.demo.controller;

import com.example.demo.model.EmailRequest;
import com.example.demo.model.OTPRequest;
import com.example.demo.service.EmailManager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:3000") 
public class EmailController {

    @Autowired
    private EmailManager emailManager;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        // Add a username here. It could come from the request body or be predefined.
        String username = "User";  // You could set this dynamically if available

        return emailManager.sendEmail(
            emailRequest.getToEmail(),
            username,               // Add the username here
            emailRequest.getSubject(),
            emailRequest.getMessage()
        );
    }
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody OTPRequest otpRequest) {
        String email = otpRequest.getEmail();
        if (email == null || email.isEmpty()) {
            return "Error: Email address is required.";
        }

        // Generate a 6-digit OTP
        int otp = (int) (Math.random() * 900000) + 100000;

        // Store OTP in the service (you can later use a database or cache)
        emailManager.storeOtp(email, otp);

        // Prepare and send OTP email
        String subject = "Your Login OTP";
        String message = String.format(
            "Hello,\n\nYour OTP for login is: %d\n\nPlease use this OTP within the next 5 minutes to log in.\n\n" +
            "Best regards,\nLMS Support Team",
            otp
        );

        return emailManager.sendEmail(email, "User", subject, message);
    }

    // New Method for Validating OTP
    @PostMapping("/validate-otp")
    public ResponseEntity<Map<String, Boolean>> validateOtp(@RequestBody OTPRequest otpRequest) {
        Map<String, Boolean> response = new HashMap<>();
        String email = otpRequest.getEmail();
        int otp = otpRequest.getOtp();

        if (emailManager.validateOtp(email, otp)) {
            response.put("success", true);
            return ResponseEntity.ok(response); // Send HTTP 200 status with the response
        } else {
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Send HTTP 401 for invalid OTP
        }
    }


    
}
