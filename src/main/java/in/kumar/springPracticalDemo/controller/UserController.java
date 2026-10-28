package in.kumar.springPracticalDemo.controller;

import in.kumar.springPracticalDemo.dto.UserRegisterRequestDto;
import in.kumar.springPracticalDemo.dto.UserRegisterResponseDto;
import in.kumar.springPracticalDemo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    // private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public String sayHello(Authentication authentication){
//        System.out.println(passwordEncoder.encode("secret123"));
//        System.out.println(passwordEncoder.encode("secret123"));
//        System.out.println(passwordEncoder.matches("secret123",
//                "$2a$10$F9hdqDCokGrU1tpApJX9zuWggeMayhxaJbcMvAXMuFepn23Ts8oqS"));
        return "Hello , You are logged in as : " + authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto registerRequestDto){
         UserRegisterResponseDto responseDto =
                 authService.register(registerRequestDto);
         return ResponseEntity.ok(responseDto);

    }
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(
            @RequestBody UserRegisterRequestDto registerRequestDto){
        Boolean loggedIn =
                authService.login(registerRequestDto);
        return ResponseEntity.ok(loggedIn);

    }

//    @GetMapping("/token")
//    public CsrfToken getToken(CsrfToken csrfToken){
//        return csrfToken;
//    }
}
