package com.example.JAQpApi.Service;

import com.example.JAQpApi.Entity.Quiz.Answer;
import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Entity.UserAnswer;
import com.example.JAQpApi.Entity.UserResult;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.AnswerRepo;
import com.example.JAQpApi.Repository.QuizRepo;
import com.example.JAQpApi.Repository.UserAnswerRepo;
import com.example.JAQpApi.Repository.UserResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResultService
{
    private final UserAnswerRepo userAnswerRepo;
    private final AnswerRepo answerRepo;
    private final UserResultRepo userResultRepo;
    private final QuizRepo quizRepo;
    private final AuthService authService;


    public void MakeAnswer(Integer _id, String _token) throws NotFoundException
    {
        User user = null;
        if(_token != null)
        {
            user = authService.GetUserByToken(_token);
        }
        Answer answer = answerRepo.findById(_id).orElseThrow(() -> new NotFoundException("answer", "id", _id.toString()));
        userAnswerRepo.save(UserAnswer.builder().answer(answer).user(user).build());
    }

    public void MakeResult(Integer _id, Float _result, String _token) throws NotFoundException
    {
        User user = null;
        if(_token != null)
        {
            user = authService.GetUserByToken(_token);
        }
        Quiz quiz = quizRepo.findById(_id).orElseThrow(() -> new NotFoundException("Quiz", "id", _id.toString()));
        userResultRepo.save(UserResult.builder().result(_result).user(user).quiz(quiz).build());
    }

}
