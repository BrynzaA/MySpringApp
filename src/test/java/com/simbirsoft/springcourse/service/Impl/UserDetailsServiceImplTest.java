package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.model.User;
import com.simbirsoft.springcourse.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername_ShouldReturn_UserDetails() {
        User user = new User();
        user.setId(1001L);
        user.setUsername("John");
        user.setPassword("123");

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        UserDetails resultUser = userDetailsService.loadUserByUsername("John");

        Assertions.assertNotNull(resultUser);

    }

    @Test
    void loadUserByUsername_ShouldFail_ByBlankUsername() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {userDetailsService.loadUserByUsername(null);
        });

    }
}