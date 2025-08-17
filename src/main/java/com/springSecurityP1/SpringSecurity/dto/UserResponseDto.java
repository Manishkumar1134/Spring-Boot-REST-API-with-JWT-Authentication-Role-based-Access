package com.springSecurityP1.SpringSecurity.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private List<UserDto> users;  // list of users
    private int pageNo;           // current page number
    private int pageSize;         // size of page
    private long totalElements;   // total records
    private int totalPages;       // total pages
    private boolean first;        // is first page?
    private boolean last;         // is last page?
    private Integer nextPage;     // next page if exists
    private Integer previousPage; // previous page if exists
}

