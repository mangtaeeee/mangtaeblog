package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostViewController {

    private final PostService postService;


    @GetMapping("/")
    public String index(Model model,@PageableDefault(page = 0,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponse> list = postService.findAllPaging(pageable);
        model.addAttribute("list", list);

        return "/blog/MainPage";
    }
    @GetMapping("/detail/{postId}")
    public String read(Model model, @PathVariable Long postId) {
        PostResponse response = postService.findOne(postId);
        postService.updateView(postId);

        model.addAttribute("detail",response);
        return "/blog/BlogDetail";
    }



}
