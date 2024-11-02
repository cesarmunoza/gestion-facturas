package com.api.gestion.security;

import com.api.gestion.dao.UserDao;
import com.api.gestion.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomerDetailService implements UserDetailsService {

    private UserDao userDao;
    private User userDetail;

    public CustomerDetailService(UserDao userDao, User userDetail){
        this.userDao = userDao;
        this.userDetail = userDetail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Buscando usuario: {}", username);
        userDetail = userDao.findByEmail(username);

        if (userDetail != null) {
            return new org.springframework.security.core.userdetails.User(
                    userDetail.getEmail(),
                    userDetail.getPassword(),
                    Collections.emptyList()
            );
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
    }

    public User getUserDetail(){
        return userDetail;
    }
}
