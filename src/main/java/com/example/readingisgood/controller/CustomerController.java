package com.example.readingisgood.controller;

import com.example.readingisgood.jwt.JwtTokenProvider;
import com.example.readingisgood.dto.customer.CustomerRequestDTO;
import com.example.readingisgood.dto.customer.CustomerResponseDTO;
import com.example.readingisgood.dto.auth.TokenRequestDTO;
import com.example.readingisgood.dto.auth.TokenResponseDTO;
import com.example.readingisgood.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/create-token")
    public ResponseEntity<TokenResponseDTO> createToken(@Valid @RequestBody TokenRequestDTO tokenRequestDTO) throws Exception  {
        authenticate(tokenRequestDTO.getEmail(), tokenRequestDTO.getPassword());
        final UserDetails userDetails = customerService.loadUserByUsername(tokenRequestDTO.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(TokenResponseDTO.builder().token(token).build());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO response = customerService.save(customerRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/credential")
    public ResponseEntity<CustomerResponseDTO> credential() {
        CustomerResponseDTO response = customerService.credential();
        return ResponseEntity.ok(response);
    }

}
