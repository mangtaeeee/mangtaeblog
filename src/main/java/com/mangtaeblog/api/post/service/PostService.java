package com.mangtaeblog.api.post.service;

import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.MemberNotFound;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.domain.PostEditor;
import com.mangtaeblog.api.post.domain.PostNotFound;
import com.mangtaeblog.api.post.repository.PostRepository;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import com.mangtaeblog.api.post.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 글 저장
    @Transactional
    public void createPost(PostCreate postCreate) {
        Member member = memberRepository.findById(postCreate.memberId())
                .orElseThrow(MemberNotFound::new);

        Post post = Post.builder()
                .title(postCreate.title())
                .content(postCreate.content())
                .writer(member.getUserId())
                .member(member)
                .build();

        postRepository.save(post);
    }

    /**
     * @param : READ 단건 조회
     */
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findByIdWithComments(id)
                .orElseThrow(PostNotFound::new);
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .view(post.getView())
                .createDate(post.getCreatedDate())
                .updateDate(post.getModifiedDate())
                .comments(post.getComments().stream().map(CommentResponse::of).collect(Collectors.toList()))
                .build();
    }

    /**
     * @param : READ 글 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .view(post.getView())
                        .updateDate(post.getModifiedDate())
                        .createDate(post.getCreatedDate())
                        .comments(post.getComments().stream().map(CommentResponse::of).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> getPostsWithPagination(Pageable pageable) {
        return postRepository.findAllWithComments(pageable)
                .map(PostResponse::of);
    }

    @Transactional
    public void updatePost(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        PostEditor.PostEditorBuilder postEditorBuilder = post.toEditor();

        PostEditor postEditor = postEditorBuilder.title(postEdit.title())
                .content(postEdit.content())
                .build();

        post.edit(postEditor);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }

    @Transactional
    public void incrementViewCount(Long id) {
        postRepository.updateView(id);
    }
}
