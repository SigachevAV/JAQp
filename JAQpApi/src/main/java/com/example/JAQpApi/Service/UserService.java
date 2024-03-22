package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.UserChangeDataRequest;
import com.example.JAQpApi.DTO.UserGeneralResponse;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.UserAccessDeniedExeption;
import com.example.JAQpApi.Exeptions.UserExeption;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Repository.TokenRepo;
import com.example.JAQpApi.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService
{

    private final UserRepo userRepository;
    private final AuthService authService;

    private User CheckAndGetUser(Integer _id, String _token) throws UserExeption
    {
        User user = authService.GetUserByToken(_token);
        if (!Objects.equals(user.getId(), _id))
        {
            throw new UserAccessDeniedExeption("Доступ запрещён");
        }
        return user;
    }

    public void SetLastName(Integer _id, String _token, String _lastName) throws UserExeption
    {
        User user = CheckAndGetUser(_id, _token);
        user.setLastName(_lastName);
        userRepository.save(user);
    }

    public void SetSecondName(Integer _id, String _token, String _secondName) throws UserExeption
    {
        User user = CheckAndGetUser(_id, _token);
        user.setSecondName(_secondName);
        userRepository.save(user);
    }

    public void SetFirstName(Integer _id, String _token, String _firstName) throws UserExeption
    {
        User user = CheckAndGetUser(_id, _token);
        user.setFirstName(_firstName);
        userRepository.save(user);
    }

    public void SetBirthDate(Integer _id, String _token, OffsetDateTime _birthDate) throws UserExeption
    {
        User user = CheckAndGetUser(_id, _token);
        user.setBirthDate(_birthDate);
        userRepository.save(user);
    }


    public UserGeneralResponse GetUserGeneralInfo(int _id) throws UserExeption
    {
        Optional<User> user = userRepository.findById(_id);
        if (user.isEmpty())
        {
            throw new UserNotFoundExeption("Пользователь не найден");
        }
        return UserGeneralResponse.FromUser(user.get());
    }

    public void SetGeneralData(Integer _id, String _token, UserChangeDataRequest _request) throws UserExeption
    {
        User user = CheckAndGetUser(_id, _token);
        user.setFirstName(_request.getFirstName());
        user.setLastName(_request.getLastName());
        user.setSecondName(_request.getSecondName());
        user.setBirthDate(_request.getBirthDate());
        userRepository.save(user);
    }
}
