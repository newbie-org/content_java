package com.example.content_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성자
@AllArgsConstructor
@Getter
public class ContentUpdateRequest {
    private String title;
    private String author;
    private String summary;
    private String genre;
}
