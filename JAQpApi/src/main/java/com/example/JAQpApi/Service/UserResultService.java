package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.UserResultsData;
import com.example.JAQpApi.DTO.UserResultsResponse;
import com.example.JAQpApi.Entity.Quiz.Answer;
import com.example.JAQpApi.Entity.Quiz.Quiz;
import com.example.JAQpApi.Entity.Quiz.QuizIndex;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Entity.UserAnswer;
import com.example.JAQpApi.Entity.UserResult;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserResultService
{
    private final UserAnswerRepo userAnswerRepo;
    private final AnswerRepo answerRepo;
    private final UserResultRepo userResultRepo;
    private final QuizRepo quizRepo;
    private final UserRepo userRepo;
    private final AuthService authService;

    private final QuizIndexRepo quizIndexRepo;


    public void MakeAnswer(Integer _id, String _token) throws NotFoundException
    {
        List<QuizIndex> list = quizIndexRepo.findAllByName("quiz");
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
        userResultRepo.save(UserResult.builder().result(_result).user(user).quiz(quiz).createdAt(new Timestamp(System.currentTimeMillis())).build());
    }

    public UserResultsResponse GetResults(Integer _id) throws NotFoundException
    {
        User user = userRepo.findById(_id).orElseThrow(() -> new NotFoundException("user", "id", _id.toString()));
        List<UserResult> results = userResultRepo.findAllByUser(user, Sort.by(Sort.Order.asc("createdAt")));
        List<UserResultsData> result = new ArrayList<>();
        for (UserResult userResult : results)
        {
            if (!userResult.getQuiz().getIsPublic())
            {
                continue;
            }
            result.add(new UserResultsData(userResult.getQuiz().getId(), userResult.getResult(), userResult.getCreatedAt()));
        }
        return new UserResultsResponse(result);
    }

}
