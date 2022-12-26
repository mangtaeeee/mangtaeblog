package com.mangtaeblog.api.comment.repository;

import com.mangtaeblog.api.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
