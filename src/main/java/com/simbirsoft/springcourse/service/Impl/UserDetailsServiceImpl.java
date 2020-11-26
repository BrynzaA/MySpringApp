package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.UserDetailsDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.model.User;
import com.simbirsoft.springcourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username);

        if (isEmpty(user)) {
            throw new ResourceNotFoundException("User not found.");
        }

        return new UserDetailsDto(user);
    }
}