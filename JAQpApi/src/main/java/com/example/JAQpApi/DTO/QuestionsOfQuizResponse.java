package com.example.JAQpApi.DTO;

import com.example.JAQpApi.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsOfQuizResponse
{
    private List<Integer> questions;

    static public QuestionsOfQuizResponse toDto(List<Question> _list)
    {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < _list.size(); i++)
        {
            list.add(_list.get(i).getQuestion_Id());
        }
        return new QuestionsOfQuizResponse(list);
    }
}
