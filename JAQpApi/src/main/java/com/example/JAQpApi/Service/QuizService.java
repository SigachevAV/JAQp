package com.example.JAQpApi.Service;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.UserException;
import com.example.JAQpApi.Repository.QuizRepo;
import com.example.JAQpApi.Repository.UserRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final UserRepo userRepo;


    public void addQuiz( String username, Quiz quiz ) throws UserException{

        Optional<User> user = userRepo.findByUsername(username);
        if ( user.isPresent() ){
            quiz.setUser(user.get());
            quizRepo.save(quiz);
        }
        else {
            throw new UserException("no user exception");
        }
        
    }
    
}
