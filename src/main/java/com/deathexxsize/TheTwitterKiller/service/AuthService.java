package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.mapper.UserMapper;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String register(RegisterDTO registerDTO) {
        User user = userMapper.toUser(registerDTO);
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        userRepo.save(user);
        return getToken(user.getUsername(), registerDTO.getPassword());
    }

    public String verify(com.deathexxsize.TheTwitterKiller.dto.authDTO.LoginDTO loginDTO) {
        return getToken(loginDTO.getUsername(), loginDTO.getPassword());
    }

    private String getToken(String username, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password
        ));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
            return "fail";
        }
    }

}
