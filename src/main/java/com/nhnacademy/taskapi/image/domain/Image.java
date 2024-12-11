package com.nhnacademy.taskapi.image.domain;

import com.nhnacademy.taskapi.book.domain.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Length(max = 255)
    private String imageUrl;



    @ManyToOne(optional = false)
    private Book book;
}
