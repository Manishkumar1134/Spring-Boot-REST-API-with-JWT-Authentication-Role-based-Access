package com.springSecurityP1.SpringSecurity.service;


import com.springSecurityP1.SpringSecurity.dto.*;
import com.springSecurityP1.SpringSecurity.entity.User;
import com.springSecurityP1.SpringSecurity.exceptions.ResourceNotFoundException;
import com.springSecurityP1.SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Username not found with email: "+ username));
    }

    public UserDto signUp(SignUpDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User Already present");
        }
        User newUser = mapper.map(userDto,User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return mapper.map(newUser,UserDto.class);
    }

    public User getUserById(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User is not Found with id: "+ userId));
        return user;
    }

    public UserResponseDto getAllUsersWithPaginationAndSorting(
            int pageNo, int pageSize, String sortBy, String sortDir) {

        // Validation
        if (pageNo < 0) {
            throw new IllegalArgumentException("pageNo must be >= 0");
        }
        if (pageSize <= 0 || pageSize > 100) {
            throw new IllegalArgumentException("pageSize must be between 1 and 100");
        }

        // Default valid fields (you can customize)
        List<String> validSortFields = List.of("id", "name", "email");
        if (!validSortFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        if (!sortDir.equalsIgnoreCase("asc") && !sortDir.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("sortDir must be 'asc' or 'desc'");
        }

        // Sort object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> page = userRepository.findAll(pageable);

        List<UserDto> users = page.getContent()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRoles().stream()
                                .map(Enum::name)
                                .collect(Collectors.toSet())
                ))
                .toList();

        return UserResponseDto.builder()
                .users(users)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .nextPage(page.hasNext() ? page.getNumber() + 1 : null)
                .previousPage(page.hasPrevious() ? page.getNumber() - 1 : null)
                .build();
    }

    // Update user details
    public UserUpdateResponse updateUser(Long userId, UpdateUserRequest updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        if (updateRequest.getName() != null && !updateRequest.getName().isEmpty()) {
            user.setName(updateRequest.getName());
        }

        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword())); // encode password
        }

        userRepository.save(user);
        return mapper.map(user,UserUpdateResponse.class);
    }

}
