package com.mkyong.service;

import com.mkyong.Repository.RepositoryUser;
import com.mkyong.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private RepositoryUser userRepository;


    public UserDetailsServiceImpl(RepositoryUser userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " dose not exist");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.isAdmin()){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}