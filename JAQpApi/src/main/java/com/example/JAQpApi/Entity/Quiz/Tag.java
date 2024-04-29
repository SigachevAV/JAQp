package com.example.JAQpApi.Entity.Quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag
{
    @Id
    @Column
    @Unique
    private String tagId;

    @Column
    private String name;
}
