package com.example.content_java.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id //id필드를 기본키로
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id가 1씩 자동 증가하게
    @Column(name = "id", updatable = false) //id
    private Long id;

    @Column(name = "title", nullable = false) //제목
    private String title;

    @Column(name = "author", nullable = false) //작가
    private String author;

    @Column(name = "summary", nullable = false) //콘텐츠 설명 요약
    private String summary;

    @Column(name = "genre", nullable = false) //콘텐츠 장르
    private String genre;


    @Builder
    public Content(String title, String author,String summary, String genre) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.genre = genre;
    }

    public void update(String title, String author,String summary, String genre) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.genre = genre;
    }
}