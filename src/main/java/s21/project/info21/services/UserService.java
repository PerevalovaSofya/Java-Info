package s21.project.info21.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import s21.project.info21.model.Role;
import s21.project.info21.model.User;
import s21.project.info21.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    public boolean createUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setActive(false);
        user.getRoles().add(Role.ROLE_USER);
        user.setActivationCode(UUID.randomUUID().toString());
        log.info("Saving new user" + user.getEmail());
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            System.out.println(user.getEmail());
            System.out.println(user.getActivationCode());
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to our app. Please visit next link to confirm your email: http://localhost:8080/activate/%s",
                     user.getUsername(), user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("net takogo usera blead  " + username);
        }
        return user;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActive(true);

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }
}
