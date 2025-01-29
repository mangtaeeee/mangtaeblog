package com.mangtaeblog.api.post.repository;

import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

    @EntityGraph(attributePaths = {"comments", "comments.member"})
    @Query("SELECT p FROM Post p")
    Page<Post> findAllWithComments(Pageable pageable);

    @Query("SELECT p FROM Post p " +
            "LEFT JOIN FETCH p.comments c " +
            "LEFT JOIN FETCH c.member m " +
            "WHERE p.id = :id " +
            "ORDER BY c.createdDate DESC")
    Optional<Post> findByIdWithComments(Long id);


}
