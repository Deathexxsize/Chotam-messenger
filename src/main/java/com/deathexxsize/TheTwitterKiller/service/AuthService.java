package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.LoginDTO;
import com.deathexxsize.TheTwitterKiller.dto.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.enums.Role;
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
        return "success";
    }

    public String verify(LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()
        ));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDTO.getUsername());
        } else {
            return "fail";
        }
    }

}
