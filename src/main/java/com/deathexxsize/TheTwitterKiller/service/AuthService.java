package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.*;
import com.deathexxsize.TheTwitterKiller.exception.InvalidVerificationCodeException;
import com.deathexxsize.TheTwitterKiller.exception.PasswordsDontMatchException;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.exception.VerificationCodeTimeoutException;
import com.deathexxsize.TheTwitterKiller.mapper.UserMapper;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepo;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final RedisTemplate<String, VerificationData> template;
    private ValueOperations<String, VerificationData> valueOps;

    @PostConstruct
    public void init() {
        valueOps = template.opsForValue();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public JwtResponse register(RegisterRequest registerRequest) {
        User user = userMapper.toUser(registerRequest);
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        userRepo.save(user);
        return new JwtResponse(getToken(user.getUsername(), registerRequest.getPassword()));
    }

    public JwtResponse verify(com.deathexxsize.TheTwitterKiller.dto.authDTOs.LoginRequest loginRequest) {
        return new JwtResponse(getToken(loginRequest.username(), loginRequest.password()));
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

    public ForgotPasswordResponse forgotPassword(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        SecureRandom random = new SecureRandom();
        int code = random.nextInt(100000, 999999);

        String token = UUID.randomUUID().toString();

        VerificationData data = new VerificationData(username, code);

        emailService.verificationCode(user.getEmail(),
                "Пожалуйста пройдите верификацию",
                "Ваш код [ " + code + " ] верификации. Никому не показывайте");

        valueOps.set(token, data, 15, TimeUnit.MINUTES);

         ForgotPasswordResponse response = new ForgotPasswordResponse(
                 "Verification code has been sent",
                         token
                 );

        return response;
    }

    public String resetPassword(ResetPasswordRequest request) {
        Long ttl = template.getExpire(request.token(), TimeUnit.SECONDS);
        if (ttl == null || ttl <= 0) {
            throw new VerificationCodeTimeoutException("The time for verification has expired. Please try again.");
        }

        VerificationData data = (VerificationData) valueOps.get(request.token());

        if (!(data.code() == request.code())) {
            throw new InvalidVerificationCodeException("Incorrect verification code");
        }

        if (!request.password().equals(request.confirmPassword())) {
            throw new PasswordsDontMatchException("The passwords you entered don't match");
        }

        User user = userRepo.findByUsername(data.username())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(encoder.encode(request.confirmPassword()));

        template.delete(request.token());

        return "Your password has been successfully updated";
    }
}
