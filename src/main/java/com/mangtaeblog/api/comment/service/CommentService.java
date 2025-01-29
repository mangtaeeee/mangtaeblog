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
    public void save(Long postId,CommentCreate commentCreate){

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        Member member = memberRepository.findById(commentCreate.getMemberId())
                .orElseThrow(MemberNotFound::new);

        Comment comment = Comment.builder()
                .content(commentCreate.getContent())
                .post(post)
                .member(member)
                .build();

        commentRepository.save(comment);

    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAll(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        List<Comment> comments = post.getComments();

        return comments.stream().map(CommentResponse::of).collect(Collectors.toList());
    }

    @Transactional
    public void update(Long commentId, CommentEdit commentEdit) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);


        CommentEditor.CommentEditorBuilder commentEditorBuilder = comment.toEditor();

        CommentEditor commentEditor = commentEditorBuilder.content(commentEdit.content())
                .build();

        comment.edit(commentEditor);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        commentRepository.delete(comment);
    }

}
