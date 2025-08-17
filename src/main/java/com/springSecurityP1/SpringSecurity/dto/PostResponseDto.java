package com.springSecurityP1.SpringSecurity.dto;


import lombok.Data;
import java.util.List;
@Data
public class PostResponseDto {
    private List<PostDto> posts;   // instead of entity, better to use DTO

    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    private boolean first;
    private boolean last;

    private Long nextPage;
    private Long previousPage;
}
