package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RegisterUser;
import com.example.demo.repository.RegisterRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    // Register a new user - default role is "INSTRUCTOR"
    @PostMapping("/registernew")
    public RegisterUser newReg(@RequestBody RegisterUser newReg) {
        // Directly set the role as "INSTRUCTOR"
        newReg.setRole("STUDENT");
        return registerRepository.save(newReg);
    }

    // Get all registered users (student data)
    @GetMapping("/getregisterationdata")
    public List<RegisterUser> getAllUsers() {
        return registerRepository.findAll();
    }

    // Login user - checks email, password, and role
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RegisterUser loginRequest) {
        Optional<RegisterUser> optionalUser = registerRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        RegisterUser user = optionalUser.get();

        // Check if the password and role match
        if (user.getPassword().equals(loginRequest.getPassword()) && user.getRole().equals(loginRequest.getRole())) {
            return ResponseEntity.ok(user); // Return the user if credentials and role match
        } else {
            return new ResponseEntity<>("Invalid credentials or role mismatch", HttpStatus.UNAUTHORIZED);
        }
    }

    // Update user details
    @PutMapping("/updateuser/{id}")
    public RegisterUser updateUser(@PathVariable Long id, @RequestBody RegisterUser userDetails) {
        RegisterUser user = registerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user details
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setDob(userDetails.getDob());
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());
        user.setGender(userDetails.getGender());
        user.setRole(userDetails.getRole()); // Update the role field

        // Save the updated user
        return registerRepository.save(user);
    }

    // Delete a user
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id) {
        registerRepository.deleteById(id);
    }
}
