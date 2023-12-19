package com.example.content_java.controller;

import com.example.content_java.domain.Content;
import com.example.content_java.dto.ContentCreateDetailRequest;
import com.example.content_java.repository.ContentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //MockMvc 생성, 자동 구성
class ContentApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ContentRepository contentRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        contentRepository.deleteAll();
    }

    @DisplayName("올바른 요청으로 컨텐츠 추가에 성공함.")
    @Test
    public void addContent() throws Exception {
        // given
        final String url = "/v1/contents";
        final String title = "title";
        final String author = "author";
        final String summary = "summary";
        final String genre = "genre";
        final ContentCreateDetailRequest userRequest = new ContentCreateDetailRequest(title, author, summary, genre);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Content> contents = contentRepository.findAll();

        assertThat(contents.size()).isEqualTo(1);
        assertThat(contents.get(0).getTitle()).isEqualTo(title);
        assertThat(contents.get(0).getAuthor()).isEqualTo(author);
        assertThat(contents.get(0).getSummary()).isEqualTo(summary);
        assertThat(contents.get(0).getGenre()).isEqualTo(genre);
    }

    @DisplayName("올바른 요청으로 컨텐츠 목록 조회에 성공함.")
    @Test
    public void findAllContents() throws Exception {
        // given
        final String url = "/v1/contents";
        final String title = "title";
        final String author = "author";
        final String summary = "summary";
        final String genre = "genre";

        contentRepository.save(Content.builder()
                .title(title)
                .author(author)
                .summary(summary)
                .genre(genre)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].summary").value(summary))
                .andExpect(jsonPath("$[0].genre").value(genre));
    }

    @DisplayName("올바른 요청으로 컨텐츠 조회에 성공함.")
    @Test
    public void findContent() throws Exception {
        // given
        final String url = "/v1/contents/{id}";
        final String title = "title";
        final String author = "author";
        final String summary = "summary";
        final String genre = "genre";

        Content savedContent = contentRepository.save(Content.builder()
                .title(title)
                .author(author)
                .summary(summary)
                .genre(genre)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedContent.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.summary").value(summary))
                .andExpect(jsonPath("$.genre").value(genre));
    }

    @DisplayName("올바른 요청으로 컨텐츠 삭제 성공함.")
    @Test
    public void deleteContent() throws Exception {
        // given : content 저장
        final String url = "/v1/contents/{id}";
        final String title = "title";
        final String author = "author";
        final String summary = "summary";
        final String genre = "genre";

        Content savedContent = contentRepository.save(Content.builder()
                .title(title)
                .author(author)
                .summary(summary)
                .genre(genre)
                .build());

        // when : 저장한 content의 id값으로 삭제 api 호출
        mockMvc.perform(delete(url, savedContent.getId()))
                .andExpect(status().isOk());

        // then : 해당 contents가 empty인지 확인
        List<Content> contents = contentRepository.findAll();

        assertThat(contents).isEmpty();
    }

}