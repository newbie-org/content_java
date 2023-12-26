package com.example.content_java.controller;

import com.example.content_java.domain.Content;
import com.example.content_java.dto.ContentCreateDetailRequest;
import com.example.content_java.dto.ContentResponse;
import com.example.content_java.dto.ContentUpdateRequest;
import com.example.content_java.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/contents")
@RequiredArgsConstructor
@RestController //객체 data를 json형식으로 반환
public class ContentApiController {
    private final ContentService contentService;

    /*컨텐츠 추가 메서드*/
    @PostMapping("")
    public ResponseEntity<Content> addContent(@RequestBody ContentCreateDetailRequest request) {
        Content savedContent = contentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedContent);
    }

    /* /v1/contents GET 요청이 오면 컨텐츠 목록 조회 후 반환하는 메서드*/
    @GetMapping("")
    public ResponseEntity<List<ContentResponse>> findAllContents() {
        List<ContentResponse> contents = contentService.findAll() //컨텐츠 전체 조회 위한 findAll() 메서드
                .stream()
                .map(ContentResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(contents); //ContentResponse로 파싱한 다음 body에 담아서 클라이언트에게
    }

    /* /v1/contents/{id} GET 요청이 오면 해당 id의 컨텐츠 조회 후 반환하는 메서드*/
    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> findContent(@PathVariable long id) {
        Content content = contentService.findById(id); //id값으로 findById를 이용해서 해당 id의 컨텐츠 찾음

        return ResponseEntity.ok()
                .body(new ContentResponse(content)); //해당 id의 컨텐츠를 body에 담아서 전송
    }

    /* /v1/contents/{id} Delete 요청이 오면 해당 id의 컨텐츠를 삭제하는 메서드*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable long id) {
        contentService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    /* /v1/contents/{id} Put 요청이 오면 해당 id의 컨텐츠를 수정하는 메서드*/
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable long id,
                                                 @RequestBody ContentUpdateRequest request) {
        Content updatedContent = contentService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedContent);
    }


}