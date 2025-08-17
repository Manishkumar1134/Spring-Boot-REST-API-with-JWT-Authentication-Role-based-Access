package com.springSecurityP1.SpringSecurity.service;

import com.springSecurityP1.SpringSecurity.dto.PostCreateRequest;
import com.springSecurityP1.SpringSecurity.dto.PostDto;
import com.springSecurityP1.SpringSecurity.dto.PostResponseDto;
import com.springSecurityP1.SpringSecurity.dto.PostUpdateRequest;
import com.springSecurityP1.SpringSecurity.entity.PostEntity;
import com.springSecurityP1.SpringSecurity.entity.User;
import com.springSecurityP1.SpringSecurity.exceptions.ResourceNotFoundException;
import com.springSecurityP1.SpringSecurity.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    private final ModelMapper mapper;
    private final PostRepository postRepository;
    @Override
    public PostDto createNewPost(PostCreateRequest inputPost) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity post = mapper.map(inputPost,PostEntity.class);
        post.setAuthor(user);
        postRepository.save(post);
        return mapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id "+id));
        return mapper.map(postEntity,PostDto.class);
    }

//    @Override
//    public List<PostDto> getAllPost() {
//        return postRepository.findAll()
//                .stream()
//                .map(postEntity -> mapper.map(postEntity,PostDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public void deletePostById(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id:"+ id));
        postRepository.deleteById(id);
        log.info("Post Delete Successfully: {}",id);
    }

    @Override
    public PostDto updatePost(Long id, PostUpdateRequest updateRequest) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        if (updateRequest.getTitle() != null) {
            post.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getDescription() != null) {
            post.setDescription(updateRequest.getDescription());
        }

        PostEntity updatedPost = postRepository.save(post);
        return mapper.map(updatedPost, PostDto.class);
    }

    public PostResponseDto getAllPostsWithPaginationAndSorting(
            int pageNo, int pageSize, String sortBy, String sortDir) {

        // Validate pageNo
        if (pageNo < 0) {
            throw new IllegalArgumentException("pageNo must be >= 0");
        }

        // Validate pageSize
        if (pageSize <= 0 || pageSize > 100) { // put a max limit to avoid heavy queries
            throw new IllegalArgumentException("pageSize must be between 1 and 100");
        }

        // Validate sortBy
        List<String> validSortFields = List.of("id", "title", "createdAt", "author");
        if (!validSortFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        // Validate sortDir
        if (!sortDir.equalsIgnoreCase("asc") && !sortDir.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("sortDir must be 'asc' or 'desc'");
        }

        // Create a Sort object based on sortBy and sortDir
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable object (page request with pagination + sorting)
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Fetch paginated and sorted results from DB
        Page<PostEntity> page = postRepository.findAll(pageable);

        // Convert PostEntity objects to PostDto using mapper
        List<PostDto> posts = page.getContent()
                .stream()
                .map(postEntity -> mapper.map(postEntity, PostDto.class))
                .toList();

        // Build PostResponseDto containing results and metadata
        PostResponseDto response = new PostResponseDto();
        response.setPosts(posts);
        response.setPageNo(page.getNumber());            // current page number
        response.setPageSize(page.getSize());           // size of the page
        response.setTotalElements(page.getTotalElements()); // total number of records
        response.setTotalPages(page.getTotalPages());   // total number of pages
        response.setFirst(page.isFirst());              // is this the first page?
        response.setLast(page.isLast());                // is this the last page?
        response.setNextPage(page.hasNext() ? (long) page.getNumber() + 1 : null); // next page number if exists
        response.setPreviousPage(page.hasPrevious() ? (long) page.getNumber() - 1 : null); // previous page if exists

        return response;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }

        List<PostEntity> posts = postRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);

        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("No posts found matching keyword: " + keyword);
        }

        return posts.stream()
                .map(post -> mapper.map(post, PostDto.class))
                .toList();
    }



}
