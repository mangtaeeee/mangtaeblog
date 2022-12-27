package com.mangtaeblog.api.comment.service;

import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.comment.domain.CommentEditor;
import com.mangtaeblog.api.comment.domain.CommentNotFound;
import com.mangtaeblog.api.comment.repository.CommentRepository;
import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.comment.request.CommentEdit;
import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.MemberNotFound;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.domain.PostNotFound;
import com.mangtaeblog.api.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long save(Long postId,CommentCreate commentCreate){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound());

        Member member = memberRepository.findById(commentCreate.getMemberId())
                .orElseThrow(() -> new MemberNotFound());

        Comment comment = Comment.builder()
                .content(commentCreate.getContent())
                .post(post)
                .member(member)
                .build();

        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAll(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        List<Comment> comments = post.getComments();

        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    @Transactional
    public void update(Long commentId, CommentEdit commentEdit) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFound());


        CommentEditor.CommentEditorBuilder commentEditorBuilder = comment.toEditor();

        CommentEditor commentEditor = commentEditorBuilder.content(commentEdit.getContent())
                .build();

        comment.edit(commentEditor);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFound());
        commentRepository.delete(comment);
    }

}
