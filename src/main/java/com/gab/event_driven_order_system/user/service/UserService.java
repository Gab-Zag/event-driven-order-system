package com.gab.event_driven_order_system.user.service;

import com.gab.event_driven_order_system.configuration.infra.TokenService;
import com.gab.event_driven_order_system.user.dto.getme.GetMeDTO;
import com.gab.event_driven_order_system.user.dto.getme.GetMeReturnDTO;
import com.gab.event_driven_order_system.user.dto.login.LoginResponseDTO;
import com.gab.event_driven_order_system.user.dto.login.UserLoginDTO;
import com.gab.event_driven_order_system.user.dto.register.UserRegisterDTO;
import com.gab.event_driven_order_system.user.entity.statistic.Statistic;
import com.gab.event_driven_order_system.user.entity.user.User;
import com.gab.event_driven_order_system.user.repository.StatisticRepository;
import com.gab.event_driven_order_system.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private final StatisticRepository statisticRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[a-z]+$");

    public UserService(UserRepository userRepository, StatisticRepository statisticRepository, AuthenticationManager authenticationManager, TokenService tokenService){
        this.userRepository = userRepository;
        this.statisticRepository = statisticRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String registerUser(UserRegisterDTO data){
        emailVerification(data.email());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(),data.email(),encryptedPassword, Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        Statistic statistic = new Statistic();
        statistic.setUserId(user);
        statistic.setTotalOrders(0);
        statistic.setLastOrder(Timestamp.valueOf(LocalDateTime.now()));
        statisticRepository.save(statistic);
        return "Usuário registrado com sucesso";
    }

    public LoginResponseDTO loginUser(UserLoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public GetMeReturnDTO getMe(GetMeDTO data){
        User user =  userRepository.findById(data.id()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        Statistic statistic = statisticRepository.getReferenceById(user.getId());
        return new GetMeReturnDTO(user.getEmail(), user.getId(), user.getName(), statistic.getTotalOrders(), statistic.getLastOrder());
    }

    protected void emailVerification(String email){
        if(email == null || !EMAIL_PATTERN.matcher(email).matches() || userRepository.findByEmail(email) != null){
            throw new RuntimeException("Email Invalido");
        }
    }
}
