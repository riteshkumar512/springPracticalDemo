package in.kumar.springPracticalDemo.service;

import in.kumar.springPracticalDemo.entity.CustomUserDetails;
import in.kumar.springPracticalDemo.entity.User;
import in.kumar.springPracticalDemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "User not found"
                ));

            return new CustomUserDetails(user);

//        return new org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername());
    }
}
