package com.example.content_java.service;

import com.example.content_java.domain.Content;
import com.example.content_java.dto.ContentCreateDetailRequest;
import com.example.content_java.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드 생성자 추가할 수 있게 해줌
@Service //Bean 등록
public class ContentService {
    private final ContentRepository contentRepository;

    /*컨텐츠 추가 메서드*/
    public Content save(ContentCreateDetailRequest request){
        //save()는 JpaRepository에서 지원하는 저장 메서드. AddContentRequest클래스에 저장된 값들을 content db에 저장
        return contentRepository.save(request.toEntity());
    }

    /*컨텐츠 목록 조회 메서드*/
    public List<Content> findAll() {
        return contentRepository.findAll();
    }


    /*id 이용해서 컨텐츠 조회 메서드*/
    public Content findById(long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }



}
