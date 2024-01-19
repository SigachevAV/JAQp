package com.example.JAQpApi.Entity;

import com.example.JAQpApi.Entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageMetadata
{
    @Column
    @Unique
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
