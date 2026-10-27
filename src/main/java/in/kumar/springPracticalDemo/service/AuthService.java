package in.kumar.springPracticalDemo.service;

import in.kumar.springPracticalDemo.dto.UserRegisterRequestDto;
import in.kumar.springPracticalDemo.dto.UserRegisterResponseDto;
import in.kumar.springPracticalDemo.entity.Role;
import in.kumar.springPracticalDemo.entity.User;
import in.kumar.springPracticalDemo.repository.RoleRepository;
import in.kumar.springPracticalDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserRegisterResponseDto register(
            UserRegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());

        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.getRoles().add(role);

        userRepository.save(user);

        UserRegisterResponseDto responseDto =
                new UserRegisterResponseDto();

        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User Saved Successfully");

        return responseDto;
    }
    public Boolean login(UserRegisterRequestDto requestDto){
        Optional<User> userOptional =
                userRepository.findByUsername(
                        requestDto.getUsername());

        User user = userOptional.get();
        String encodedPassword = user.getPassword();

        return passwordEncoder.matches(requestDto.getPassword(),
                encodedPassword);
    }
}
