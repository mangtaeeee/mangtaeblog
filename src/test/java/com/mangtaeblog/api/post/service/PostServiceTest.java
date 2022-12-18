package com.mangtaeblog.api.post.service;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.service.MemberService;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.repository.PostRepository;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import com.mangtaeblog.api.post.request.PostSearch;
import com.mangtaeblog.api.post.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository ;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
        postRepository.deleteAll();
    }


    @Test
    @DisplayName("글 작성")
    void 글작성() {
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);



        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .memberId(member.getId())
                .build();


        //when
        postService.write(postCreate);
        //then
        assertEquals(1L,postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("전체 조회 페이징 처리")
    void 전체조회() {

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목 " + i)
                        .content("내용 " + i)
                        .writer("작성자 " + i)
                        .member(member)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        //when
        List<PostResponse> response = postService.findAll(postSearch);

        //then
        assertEquals(10L,response.size());
        assertEquals("제목 19",response.get(0).getTitle());
    }


    @Test
    @DisplayName("글 수정 null이 들어오면 기존의 있던값으로 저장되어야함")
    void 글제목수정() {

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


        PostEdit postEdit = PostEdit.builder()
                .title("수정했습니다.")
                .content(null)
                .writer(null)
                .build();

        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changepost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다." + post.getId()));
        Assertions.assertEquals("수정했습니다.",changepost.getTitle() );
        Assertions.assertEquals("내용입니다.",changepost.getContent());
        Assertions.assertEquals("작성자입니다.",changepost.getWriter());
        Assertions.assertNotEquals(changepost.getCreateDate(),changepost.getUpdateDate());
    }

    @Test
    void 글삭제() {
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
        //when
        postService.delete(post.getId());
        //then
        assertEquals(0, postRepository.count());
    }
}