package com.mangtaeblog.api.comment.service;

import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.comment.domain.CommentNotFound;
import com.mangtaeblog.api.comment.repository.CommentRepository;
import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.comment.request.CommentEdit;
import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.service.MemberService;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.repository.PostRepository;
import com.mangtaeblog.api.post.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository ;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
        postRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("댓글 작성")
    void 댓글작성(){

        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        CommentCreate commentCreate = CommentCreate.builder()
                .content("내용입니다.")
                .memberId(member.getId())
                .build();

        //when
        commentService.save(post.getId(),commentCreate);

        //then
        Assertions.assertEquals(1L, commentRepository.count());
        Comment comment = commentRepository.findAll().get(0);
        Assertions.assertEquals("내용입니다.",comment.getContent());

    }

    @Test
    @DisplayName("댓글 조회")
    void 댓글_조회() {

        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        CommentCreate commentCreate = CommentCreate.builder()
                .content("내용입니다.")
                .memberId(member.getId())
                .build();

        commentService.save(post.getId(), commentCreate);

        //when
        List<CommentResponse> responses = commentService.findAll(post.getId());

        //then
        assertEquals(1L,responses.size());
        assertEquals(post.getId(),responses.get(0).getPostId());
    }

    @Test
    @DisplayName("댓글 수정")
    void 댓글수정(){

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content("내용입니다.")
                .build();
        commentRepository.save(comment);

        CommentEdit commentEdit = CommentEdit.builder()
                .content("수정했습니다.")
                .build();
        //when
        commentService.update(comment.getId(),commentEdit);

        //then
        Comment findComment = commentRepository.findById(post.getId())
                .orElseThrow(() -> new CommentNotFound());
        Assertions.assertEquals("수정했습니다.", findComment.getContent());

    }

    @Test
    @DisplayName("댓글 수정")
    void 댓글삭제(){

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content("내용입니다.")
                .build();
        commentRepository.save(comment);


        //when
        commentService.delete(comment.getId());
        //then
        Assertions.assertEquals(0, commentRepository.count());

    }

}