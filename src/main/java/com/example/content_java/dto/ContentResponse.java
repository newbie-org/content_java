package com.example.content_java.dto;

import com.example.content_java.domain.Content;
import lombok.Getter;

@Getter
public class ContentResponse {
    private final String title;
    private final String author;
    private final String summary;
    private final String genre;

    public ContentResponse(Content content) {
        this.title = content.getTitle();
        this.author = content.getAuthor();
        this.summary = content.getSummary();
        this.genre = content.getGenre();
    }
}
