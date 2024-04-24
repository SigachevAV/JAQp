package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.UserChangeDataRequest;
import com.example.JAQpApi.DTO.UserGeneralResponse;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exceptions.AccessDeniedException;
import com.example.JAQpApi.Exceptions.NotFoundException;
import com.example.JAQpApi.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService
{

    private final UserRepo userRepository;
    private final AuthService authService;

    private User CheckAndGetUser(Integer _id, String _token) throws AccessDeniedException, NotFoundException
    {
        User user = authService.GetUserByToken(_token);
        if (!Objects.equals(user.getId(), _id))
        {
            throw new AccessDeniedException("Доступ запрещён");
        }
        return user;
    }

    public UserGeneralResponse GetUserGeneralInfo(int _id) throws NotFoundException
    {
        Optional<User> user = userRepository.findById(_id);
        if (user.isEmpty())
        {
            throw new NotFoundException("User", "id" , Integer.toString(_id));
        }
        return UserGeneralResponse.FromUser(user.get());
    }

    public void SetGeneralData(Integer _id, String _token, UserChangeDataRequest _request) throws NotFoundException, AccessDeniedException
    {
        User user = CheckAndGetUser(_id, _token);
        user.setFirstName(_request.getFirstName());
        user.setLastName(_request.getLastName());
        user.setSecondName(_request.getSecondName());
        user.setBirthDate(_request.getBirthDate());
        userRepository.save(user);
    }
}
