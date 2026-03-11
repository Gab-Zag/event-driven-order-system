package com.gab.event_driven_order_system.user.service;

import com.gab.event_driven_order_system.user.dto.getme.getMeDTO;
import com.gab.event_driven_order_system.user.dto.login.UserLoginDTO;
import com.gab.event_driven_order_system.user.dto.register.UserRegisterDTO;
import com.gab.event_driven_order_system.user.entity.statistic.Statistic;
import com.gab.event_driven_order_system.user.entity.user.User;
import com.gab.event_driven_order_system.user.repository.StatisticRepository;
import com.gab.event_driven_order_system.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StatisticRepository statisticRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[a-z]+$");

    public UserService(UserRepository userRepository, StatisticRepository statisticRepository){
        this.userRepository = userRepository;
        this.statisticRepository = statisticRepository;
    }

    public String registerUser(UserRegisterDTO data){
        emailVerification(data.email());
        User user = new User();
        user.setEmail(data.email());
        user.setName(data.name());
        user.setPassword(data.password());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        Statistic statistic = new Statistic();
        statistic.setUserId(user);
        statistic.setTotalOrders(0);
        statistic.setLastOrder(Timestamp.valueOf(LocalDateTime.now()));
        statisticRepository.save(statistic);
        return "Usuário registrado com sucesso";
    }

    public String loginUser(UserLoginDTO data){
        User user = userRepository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Usario nao encontrado"));
        if(!user.getPassword().equals(data.password())){
            throw new RuntimeException("Senha incorreta");
        }
        return "Login realizado com sucesso";
    }

    public User getMe(getMeDTO data){
        return userRepository.findById(data.id()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
    }

    protected void emailVerification(String email){
        if(email == null || !EMAIL_PATTERN.matcher(email).matches() || userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email Invalido");
        }
    }
}
