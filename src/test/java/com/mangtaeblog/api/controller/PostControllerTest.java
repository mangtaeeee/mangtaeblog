package com.mangtaeblog.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangtaeblog.api.domain.Post;
import com.mangtaeblog.api.repository.PostRepository;
import com.mangtaeblog.api.request.PostCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("/posts 요청 시 json데이터로 저장한다")
    void 저장() throws Exception {
        //given
        PostCreate request = PostCreate.builder()
                .title("제목 입니다.")
                .content("내용 입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);


        //when
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //then
        Assertions.assertEquals(1L,postRepository.count());

        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목 입니다.",post.getTitle());
        Assertions.assertEquals("내용 입니다.",post.getContent());



    }

    @Test
    @DisplayName("/posts 요청시 title / content 값은 필수다")
    void 저장_예외() throws Exception {
        PostCreate postCreate = PostCreate.builder()
                .title("")
                .content("")
                .build();

        String json = objectMapper.writeValueAsString(postCreate);

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("타이틀을 입력해 주세요."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.content").value("내용을 입력해 주세요."))
                .andDo(MockMvcResultHandlers.print());
    }


}