package com.mangtaeblog.api.post.service;

import com.mangtaeblog.api.exception.MemberNotFound;
import com.mangtaeblog.api.exception.PostNotFound;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.domain.PostEditor;
import com.mangtaeblog.api.post.repository.PostRepository;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import com.mangtaeblog.api.post.request.PostSearch;
import com.mangtaeblog.api.post.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //글 저장
    @Transactional
    public Post write(PostCreate postCreate) {

        Member member = memberRepository.findById(postCreate.getMemberId())
                .orElseThrow(() -> new MemberNotFound());

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .writer(postCreate.getWriter())
                .view(0)
                .member(member)
                .build();

        return postRepository.save(post);

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
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }

    /**
     *
     * @param : READ 글 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PostResponse> findAll(PostSearch postSearch) {
        List<PostResponse> collect = postRepository.getList(postSearch).stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .updateDate(post.getUpdateDate())
                        .createDate(post.getCreateDate())
                        .build())
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        PostEditor.PostEditorBuilder postEditorBuilder = post.toEditor();

        PostEditor postEditor = postEditorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);

    }
    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        postRepository.delete(post);
    }
}
