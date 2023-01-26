package com.mangtaeblog.api.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


//
//    @Override
//    public List<Post> getList(PostSearch postSearch) {
//        return jpaQueryFactory.selectFrom(QPost.post)
//                .limit(postSearch.getSize())
//                .offset(postSearch.getOffset())
//                .orderBy(QPost.post.id.desc())
//                .fetch();
//    }


}
