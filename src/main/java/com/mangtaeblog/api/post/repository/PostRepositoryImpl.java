//package com.mangtaeblog.api.post.repository;
//
//import com.mangtaeblog.api.post.domain.Post;
//import com.mangtaeblog.api.post.domain.QPost;
//import com.mangtaeblog.api.post.request.PostSearch;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//
//@RequiredArgsConstructor
//public class PostRepositoryImpl implements PostRepositoryCustom {
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<Post> getList(PostSearch postSearch) {
//        return jpaQueryFactory.selectFrom(QPost.post)
//                .limit(postSearch.getSize())
//                .offset(postSearch.getOffset())
//                .orderBy(QPost.post.id.desc())
//                .fetch();
//    }
//
//
//
//
//}
