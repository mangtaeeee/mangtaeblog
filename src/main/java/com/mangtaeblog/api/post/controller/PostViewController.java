package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
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
public class PostViewController {

    private final PostService postService;


    @GetMapping("/")
    public String index(Model model,@PageableDefault(page = 0,size = 5,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
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
    @GetMapping("/edit/{postId}")
    public String edit(Model model, @PathVariable Long postId) {
        PostResponse response = postService.findOne(postId);

        model.addAttribute("editData",response);
        return "/blog/PostEdit";
    }

    @GetMapping("/write")
    public String write() {
        return "/blog/PostWrite";
    }


}
