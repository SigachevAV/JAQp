package com.example.JAQpApi.Repository;

import com.example.JAQpApi.Entity.Token.Token;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Integer>
{
        /* and (t.expired = false or t.revoked = false) */
        @Query(value = """
             select t from Token t inner join user u on t.user.id = u.id
             where u.id = :uid
           """)
        List<Token> findAllValidTokensByUserId(@Param("uid") Integer uid);

        Optional<Token> findByToken(String token);

}
