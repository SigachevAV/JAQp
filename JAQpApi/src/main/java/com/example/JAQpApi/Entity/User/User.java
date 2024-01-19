package com.example.JAQpApi.Entity.User;

import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.Token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails
{
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String lastName;

    @Column
    private OffsetDateTime birthDate;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<ImageMetadata> imageMetadata;

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority("User"));
    }

//    @OneToMany(mappedBy = "owner")
//    private Set<Quez> ownerQuezes;
//
//    @OneToMany(mappedBy = "user")
//    private Set<UserAnswer> userUserAnswers;

}
