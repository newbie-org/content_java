package com.example.content_java.dto;


import com.example.content_java.domain.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성자
@AllArgsConstructor
@Getter
public class ContentCreateDetailRequest {
    private String title;
    private String author;

    private String summary;

    private String genre;

    public Content toEntity() {
        return Content.builder()
                .title(title)
                .author(author)
                .summary(summary)
                .genre(genre)
                .build();
    }
}
