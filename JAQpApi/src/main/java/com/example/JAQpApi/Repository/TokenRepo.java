package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Token.Token;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Hidden
public interface TokenRepo extends JpaRepository<Token, Integer>
{
    @Query(value = """
        select t from Token t inner join User u\s
        on t.user.id = u.id\s
        where u.id = :id and (t.expired = false or t.revoked = false)\s
        """)
    List<Token> findAllValidTokensByUserId(Integer id);

    Optional<Token> findByToken(String token);

}
