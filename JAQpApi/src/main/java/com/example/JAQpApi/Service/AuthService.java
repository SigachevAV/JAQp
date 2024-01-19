package com.example.JAQpApi.Service;

import com.example.JAQpApi.DTO.AuthenticationRequest;
import com.example.JAQpApi.DTO.AuthenticationResponse;
import com.example.JAQpApi.Entity.Token.Token;
import com.example.JAQpApi.Entity.Token.TokenType;
import com.example.JAQpApi.Entity.User.Role;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.UserNotFoundExeption;
import com.example.JAQpApi.Repository.TokenRepo;
import com.example.JAQpApi.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final TokenRepo tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private String StripToken(String _token)
    {
        return _token.substring(7);
    }

    public User GetUserByToken(String _token) throws UserNotFoundExeption
    {
        return  tokenRepository.findByToken(StripToken(_token)).orElseThrow(() -> new UserNotFoundExeption("")).getUser();
    }

    public AuthenticationResponse register(AuthenticationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        var usrTmp = userRepository.save(user);
        var jwtToken = jwtService.generateJwtToken(user);
        saveUserToken(usrTmp, jwtToken);

        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .id(user.getId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();////// !!!!!!!!!!!!!!!!!!!!!!!!!!!
        var jwtToken = jwtService.generateJwtToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .id(user.getId())
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAlLValidTokenByUserId(user.getId());
        if ( validUserTokens.isEmpty() ){
            return;
        }
        //tokenRepository.deleteAll(validUserTokens);
        validUserTokens.forEach(t->{
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    void saveUserToken(User user, String jwtToken) {
        Token.TokenBuilder builder = Token.builder();
        builder.user(user);
        builder.token(jwtToken);
        builder.tokenType(TokenType.BEARER);
        builder.expired(false);
        builder.revoked(false);
        var token = builder
                .build();
        tokenRepository.save(token);
    }
}
