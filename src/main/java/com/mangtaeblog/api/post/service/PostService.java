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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //글 저장
    @Transactional
    public void write(PostCreate postCreate) {

        Member member = memberRepository.findById(postCreate.getMemberId())
                .orElseThrow(() -> new MemberNotFound());

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .writer(member.getUserId())
                .member(member)
                .build();

        postRepository.save(post);

    }

    /**
     * @param : READ 단건 조회
     */
    @Transactional(readOnly = true)
    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .view(post.getView())
                .createDate(post.getCreatedDate())
                .updateDate(post.getModifiedDate())
                .comments(post.getComments().stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList()))
                .build();
    }

    /**
     * @param : READ 글 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        List<PostResponse> collect = postRepository.findAll().stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .view(post.getView())
                        .updateDate(post.getModifiedDate())
                        .createDate(post.getCreatedDate())
                        .comments(post.getComments().stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        return collect;
    }


    @Transactional(readOnly = true)
    public Page<PostResponse> findAllPaging(Pageable pageable) {

        Page<Post> postList = postRepository.findAll(pageable);
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .view(post.getView())
                    .updateDate(post.getModifiedDate())
                    .createDate(post.getCreatedDate())
                    .comments(post.getComments().stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList()))
                    .build();
            postResponseList.add(postResponse);
        }

        return new PageImpl<>(postResponseList, pageable, postList.getTotalElements());
    }


    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        PostEditor.PostEditorBuilder postEditorBuilder = post.toEditor();

        PostEditor postEditor = postEditorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);

    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        postRepository.delete(post);
    }


    @Transactional
    public int updateView(Long id) {
        return postRepository.updateView(id);
    }
}
