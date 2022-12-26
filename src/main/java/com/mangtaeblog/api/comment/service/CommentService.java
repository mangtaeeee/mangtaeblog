package com.mangtaeblog.api.comment.service;

import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.comment.repository.CommentRepository;
import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.MemberNotFound;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.domain.PostNotFound;
import com.mangtaeblog.api.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment save(CommentCreate commentCreate){

        Member member = memberRepository.findById(commentCreate.getMemberId())
                .orElseThrow(() -> new MemberNotFound());

        Post post = postRepository.findById(commentCreate.getPostId())
                .orElseThrow(() -> new PostNotFound());

        Comment comment = Comment.builder()
                .content(commentCreate.getContent())
                .post(post)
                .member(member)
                .build();

        return commentRepository.save(comment);

    }






}
