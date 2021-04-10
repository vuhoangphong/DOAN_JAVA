package web.badminton.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.badminton.constant.Constant;
import web.badminton.repository.RoleRepository;
import web.badminton.repository.UserRepository;
import web.badminton.vo.Role;
import web.badminton.vo.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Autowired
    RoleRepository roleRepository;

    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        User userInformation = userService.getUser(username);
        session.setAttribute(Constant.USER, userInformation);
        List<String> result = roleRepository.getListRole(username);
        List<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
        for (String item : result) {
            GrantedAuthority authority = new SimpleGrantedAuthority(item);
            authoritys.add(authority);
        }
        
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassWord(),authoritys);
       
        return userDetails;
    }
}