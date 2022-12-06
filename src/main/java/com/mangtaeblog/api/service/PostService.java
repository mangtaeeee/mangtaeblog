package com.mangtaeblog.api.service;

import com.mangtaeblog.api.domain.Post;
import com.mangtaeblog.api.exception.PostNotFound;
import com.mangtaeblog.api.repository.PostRepository;
import com.mangtaeblog.api.request.PostCreate;
import com.mangtaeblog.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //글 저장
    public Post write(PostCreate postCreate) {

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        return postRepository.save(post);

    }

    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

    }
}
