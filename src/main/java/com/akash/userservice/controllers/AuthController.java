package com.akash.userservice.controllers;

import com.akash.userservice.dtos.LoginRequestDTO;
import com.akash.userservice.dtos.SignUpRequestDTO;
import com.akash.userservice.dtos.UserDTO;
import com.akash.userservice.dtos.ValidateTokenDTO;
import com.akash.userservice.models.User;
import com.akash.userservice.services.IAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller // when we also want views as resposne along with JSON/XML
@RestController // when we want response to be JSON/XML
public class AuthController {
    // signup
    // login
    // logout
    // forgetPassword

    private IAuthService authService;

    AuthController(/*@Qualifier("AuthService")*/ IAuthService authService){
        this.authService = authService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){
        try{
            User user = authService.signUp(requestDTO.getEmail(), requestDTO.getPassword());
            UserDTO userDTO = getUserDTO(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO requestDTO){
        try{
            String token = authService.login(requestDTO.getEmail(), requestDTO.getPassword());

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE, token);

//            UserDTO userDTO = getUserDTO(user);
            return new ResponseEntity<>(token, headers, HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody ValidateTokenDTO requestDTO){
        try{
            boolean isValid = authService.validateToken(requestDTO.getToken(), requestDTO.getUserId());
            return new ResponseEntity<>(isValid, HttpStatus.OK);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    private UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
