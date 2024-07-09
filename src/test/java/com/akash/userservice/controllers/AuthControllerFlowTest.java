package com.akash.userservice.controllers;

import com.akash.userservice.dtos.LoginRequestDTO;
import com.akash.userservice.dtos.SignUpRequestDTO;
import com.akash.userservice.dtos.UserDTO;
import com.akash.userservice.dtos.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthControllerFlowTest {
    @Autowired
    AuthController authController;

//    @Test
    public void Test_SignupLoginAndValidateToken(){
        // Arrange
        String email = "dummy";
        String password = "dummy";
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmail(email);
        requestDTO.setPassword(password);

        // signup
        ResponseEntity<UserDTO> signedUpUser = authController.signUp(requestDTO);
        assertEquals(email, signedUpUser.getBody().getEmail());

        // login
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(email);
        loginRequestDTO.setPassword(password);
        ResponseEntity<String> loginResponse = authController.login(loginRequestDTO);
        assertNotNull(loginResponse.getBody());

        // validate token
        ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO();
        validateTokenDTO.setToken(loginResponse.getBody());
        validateTokenDTO.setUserId(1L);
        ResponseEntity<Boolean> validateTokenResponse = authController.validateToken(validateTokenDTO);
        assertTrue(validateTokenResponse.getBody());
    }
}
