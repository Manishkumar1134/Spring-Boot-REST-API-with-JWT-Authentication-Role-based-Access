package com.springSecurityP1.SpringSecurity.service;

import com.springSecurityP1.SpringSecurity.dto.PostCreateRequest;
import com.springSecurityP1.SpringSecurity.dto.PostDto;
import com.springSecurityP1.SpringSecurity.dto.PostResponseDto;
import com.springSecurityP1.SpringSecurity.dto.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto createNewPost(PostCreateRequest inputPost);

    PostDto getPostById(Long id);

//    List<PostDto> getAllPost();

    void deletePostById(Long id);

    PostDto updatePost(Long id, PostUpdateRequest updateRequest);

    PostResponseDto getAllPostsWithPaginationAndSorting(int pageNo, int pageSize, String sortBy, String sortDir);

    List<PostDto> searchPosts(String keyword);
}
