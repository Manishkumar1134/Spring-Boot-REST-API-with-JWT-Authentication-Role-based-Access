package com.springSecurityP1.SpringSecurity.controller;

import com.springSecurityP1.SpringSecurity.dto.PostCreateRequest;
import com.springSecurityP1.SpringSecurity.dto.PostDto;
import com.springSecurityP1.SpringSecurity.dto.PostResponseDto;
import com.springSecurityP1.SpringSecurity.dto.PostUpdateRequest;
import com.springSecurityP1.SpringSecurity.service.PostService;
import com.springSecurityP1.SpringSecurity.swagger.annotation.post.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // Require JWT for all endpoints here
public class PostController {

    private final PostService postService;

    @PostMapping
    @Secured({"ROLE_USER"})
    @SwaggerCreatePost
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostCreateRequest postCreateRequest) {
        return ResponseEntity.ok(postService.createNewPost(postCreateRequest));
    }


    @GetMapping("/{id}")
    @PreAuthorize("@postSecurity.isOwnerOfThisPost(#id)")
    @SwaggerGetPostById
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Get All Posts (with Pagination & Sorting)
    @GetMapping
    @PreAuthorize("hasAuthority('POST_VIEW')")
    @SwaggerGetAllPosts
    public ResponseEntity<PostResponseDto> getAllPostsWithPaginationAndSorting(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        PostResponseDto response = postService.getAllPostsWithPaginationAndSorting(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("@postSecurity.isOwnerOfThisPost(#id)")
    @SwaggerDeletePostById
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PutMapping("/{id}")
    @PreAuthorize("@postSecurity.isOwnerOfThisPost(#id) or hasRole('ADMIN')")
    @SwaggerUpdatePost
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest updateRequest) {
        return ResponseEntity.ok(postService.updatePost(id, updateRequest));
    }

    // Search Posts
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('POST_VIEW')")
    @SwaggerSearchPosts
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPosts(keyword));
    }

}
