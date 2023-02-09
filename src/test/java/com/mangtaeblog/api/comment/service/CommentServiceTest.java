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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .title("개인프로젝트 만들기.")
                .content("문득 새로운 직장을 찾다가 ORM 이라는것에 대해 알게되어 깊이 찾아보고 JPA 와 QueryDSL 공부를 통해 개인 프로젝트를 만들어 보았다.문득 게시판을 만들다 티스토리 블로그 같은걸 한번 만들어보는건 어떤지 생각이 들어 프로젝트 명세서를 만들어본다.\n" +
                        "\n" +
                        "프로젝트 명세서는 다음과 같다.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "1. 아이템 선정\n" +
                        "\n" +
                        "2. 개요\n" +
                        "\n" +
                        "3. 요구사항 분석\n" +
                        "\n" +
                        "4. DB설계 및 API설계\n" +
                        "\n" +
                        "5. 화면설계서\n" +
                        "\n" +
                        "6. 개발 및 테스트 배포\n" +
                        "\n" +
                        "7. 도메인 설정\n" +
                        "\n" +
                        "8. ReadMe와 포트폴리오 자조서 작성 후 회고\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "1. 아이템선정\n" +
                        "웹의 기본인 CRUD 게시판을 만들어 그이후 차차 기능들을 추가해 블로그를 만들거다.\n" +
                        "\n" +
                        "2. 개요\n" +
                        "프로젝트 명칭 : mangtaeBlog\n" +
                        "개발 인원 : 1명\n" +
                        "개발 기간 : 2022.12 ~ 2023.01\n" +
                        "주요 기능 :\n" +
                        "게시판 - CRUD 기능, 조회수, 페이징 및 검색 처리\n" +
                        "사용자 - Security 회원가입 및 로그인, OAuth 2.0 구글, 네이버 로그인, 회원 정보 수정, 유효성 검사 및 중복 검사\n" +
                        "댓글 - CRUD 기능 \n" +
                        "개발 언어 : Java 11\n" +
                        "개발 환경 : SpringBoot 2.5.6, gradle 7.2, jpa(Spring Data JPA), Spring Security, Oauth 2.0,Vue.js\n" +
                        "데이터베이스 : Postgresql\n" +
                        "형상관리 툴 : GitHub\n" +
                        "간단 소개 : 개인 블로그 만들기.\n" +
                        "3. 요구사항 분석\n" +
                        "1. 회원가입\n" +
                        "\n" +
                        "유효성 검사\n" +
                        "닉네임은 2~10자이며, 특수문자를 제외한 한글 (ㄱ~ㅎ, 가~힣) 알바벳 (a~z, A~Z) 숫자(0~9)\n" +
                        "이메일 패턴 적용 및 공백확인 후 \"000은 필수 입니다.\"띄우기\n" +
                        "중복확인 , SNS로그인 구현해보기\n" +
                        "2. 로그인 페이지\n" +
                        "\n" +
                        "로그인을 하지 않은 경우 아래 페이지만 가능 (클라이언트)\n" +
                        "전체글 읽기 , 상세보기, 댓글보기, 조회수 증가\n" +
                        "로그인시 사용 가능한 기능(클라이언트)\n" +
                        "댓글 기능(쓰기, 수정,삭제)\n" +
                        "좋아요 누르기\n" +
                        "로그인시 사용 가능한 기능(관리자계정)\n" +
                        "글쓰기, 글수정, 글삭제\n" +
                        "댓글 삭제, 카테고리 관리\n" +
                        "관리페이지(좋아요수, 방문수, 댓글수)\n" +
                        "3. 게시글 작성\n" +
                        "\n" +
                        "게시글 수정, 작성 시 제목 및 내용은 빈칸으로 지정할 수 없음\n" +
                        "마스터 계정만 이용할 수 있음\n" +
                        "카테고리칸 있고 이미지 첨부 가능\n" +
                        "작성 시 또는 수정 시 글작성 수정 날짜 반영\n" +
                        "4. 게시글 확인\n" +
                        "\n" +
                        "게시글 클릭시 조회 수 증가\n" +
                        "클릭시 상세 페이지 이동 (게시글, 댓글, 작성일, 내용, 이미지)\n" +
                        "5. 게시글 검사\n" +
                        "\n" +
                        "마스터만 수정, 및 삭제 가능\n" +
                        "로그인 안하면 댓글 쓰기 및 글쓰기 숨기기\n" +
                        "6. 댓글 검사\n" +
                        "\n" +
                        "본인 댓글만 수정 및 삭제 가능\n" +
                        "미로그인시 댓글쓰기 버튼 대신 로그인 버튼 띄우기\n" +
                        "게시글 삭제시 댓글 삭제\n" +
                        "공백에 대한 유효성 검사\n" +
                        "4. DB 설계 및 API 설계")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        CommentCreate commentCreate = CommentCreate.builder()
                .content("댓글 내용입니다.")
                .memberId(member.getId())
                .build();

        //when
        commentService.save(post.getId(),commentCreate);

        //then
        Assertions.assertEquals(1L, commentRepository.count());
        Comment comment = commentRepository.findAll().get(0);
        Assertions.assertEquals("댓글 내용입니다.",comment.getContent());

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