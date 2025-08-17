package com.springSecurityP1.SpringSecurity.utils;

import com.springSecurityP1.SpringSecurity.dto.PostDto;
import com.springSecurityP1.SpringSecurity.entity.User;
import com.springSecurityP1.SpringSecurity.service.PostService;
import com.springSecurityP1.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {
    private final PostService postService;

    public boolean isOwnerOfThisPost(Long postId){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDto postDto = postService.getPostById(postId);
        return postDto.getAuthor().getId().equals(user.getId());
    }

}
