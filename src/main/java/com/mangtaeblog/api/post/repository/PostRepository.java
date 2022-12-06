package com.mangtaeblog.api.post.repository;

import com.mangtaeblog.api.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
