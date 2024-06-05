package com.example.JAQpApi.Service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.JAQpApi.DTO.AuthenticationRequest;
import com.example.JAQpApi.DTO.RegistrationRequest;
import com.example.JAQpApi.Entity.User.Role;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Repository.QuizRepo;
import com.example.JAQpApi.Repository.TokenRepo;
import com.example.JAQpApi.Repository.UserRepo;

import ch.qos.logback.core.subst.Token;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public class QuizServiceTest {
    

    @Mock
    private QuizRepo quizRepo;

    @Mock private UserRepo userRepo;
    @Mock private TokenRepo tokenRepo;
    
    @InjectMocks
    private AuthService authService;

    @InjectMocks
    QuizService quizService;

    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);
        var admin = User.builder()
				.username("admin")
				.password("admin")
				.role(Role.ADMIN)
				.build();
        
        var adminAuth = AuthenticationRequest.builder()
				.username("admin")
				.password("admin")
				.build();
        try {
            userRepo.save(admin);
            var userList = userRepo.findAll();
            for ( User user : userList ){
                System.out.println(user.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    void testChangeQuiz() {
        
        

    }

    @Test
    void testChangeQuiz2() {

    }

    @Test
    void testCreateQuiz() {

        // assemble

        // act

        // assert

    }

    @Test
    void testDeleteQuiz() {

    }

    @Test
    void testGetOwnedQuiz() {

    }

    @Test
    void testGetQuestionsOfQuiz() {

    }

    @Test
    void testGetQuiz() {

    }

    @Test
    void testValidateAccessAndGetQuiz() {

    }
}
