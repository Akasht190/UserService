package com.akash.userservice.controllers;

import com.akash.userservice.dtos.SignUpRequestDTO;
import com.akash.userservice.models.User;
import com.akash.userservice.services.IAuthService;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AuthControllerTest {
    @Autowired
    AuthController authController;

    @MockBean
    IAuthService authService;

    @Captor
    private ArgumentCaptor<Long> emailCaptor;
    @Captor
    private ArgumentCaptor<Long> passwordCaptor;

//    @Test
    public void Test_SignUpWithValidDetails_ReturnsUserDTO(){
        String email = "test@mail.com";
        String password = "password";
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmail(email);
        requestDTO.setPassword(password);

        User user = new User();
        user.setEmail(email);
//        when(authService.signUp(any(String.class),any(String.class))).thenReturn()
    }
}
