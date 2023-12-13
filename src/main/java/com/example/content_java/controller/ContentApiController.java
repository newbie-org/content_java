package com.example.content_java.controller;

import com.example.content_java.domain.Content;
import com.example.content_java.dto.AddContentRequest;
import com.example.content_java.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController //객체 data를 json형식으로 반환
public class ContentApiController {
    private final ContentService contentService;

    @PostMapping("/api/contents") // http 메서드
    public ResponseEntity<Content> addContent(@RequestBody AddContentRequest request) {
        Content savedContent = contentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedContent);
    }
}