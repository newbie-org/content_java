package com.example.content_java.service;

import com.example.content_java.domain.Content;
import com.example.content_java.dto.ContentCreateDetailRequest;
import com.example.content_java.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Bean 등록
public class ContentService {
    private final ContentRepository contentRepository;
    // ContentRepository를 주입받는 생성자
    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content save(ContentCreateDetailRequest request) {

        Content content = Content.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .summary(request.getSummary())
                .genre(request.getGenre())
                .build();

        // contentRepository를 사용하여 저장
        return contentRepository.save(content);
    }


    /*컨텐츠 목록 조회 메서드*/
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    /*id로 컨텐츠 조회 메서드*/
    public Content findById(long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + id+ "의 content를 찾을 수 없습니다."));
    }

    /*id로 컨텐츠 삭제 메서드*/
    public void delete(long id) {
        contentRepository.deleteById(id); //Jpa의 deleteByID() 이용해서 DB에서 데이터 삭제
    }
}