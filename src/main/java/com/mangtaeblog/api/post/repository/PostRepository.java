package com.mangtaeblog.api.post.repository;

import com.mangtaeblog.api.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);
}
