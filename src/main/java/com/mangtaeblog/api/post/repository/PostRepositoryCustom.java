package com.mangtaeblog.api.post.repository;

import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);

}
