package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.GetUserDTO;
import lk.sliit.itpm.demo.dto.TokenResponse;
import lk.sliit.itpm.demo.dto.UserDTO;
import lk.sliit.itpm.demo.repository.UserRepository;
import lk.sliit.itpm.demo.service.UserService;
import lk.sliit.itpm.demo.util.JWTService;
import lk.sliit.itpm.demo.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, JWTService jwtService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    @Override
    public ResponseEntity<User> registerUser(UserDTO user) {
        User userDoc = modelMapper.map(user, User.class);
        userDoc.setRole(Role.USER);
        User save = userRepository.save(userDoc);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @Override
    public ResponseEntity<User> registerAdmin(UserDTO admin) {
        return null;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }

    @Override
    public TokenResponse generateToken(String email, String password) {

        //TODO: Validate the user's OTP
            Optional<User> user = userRepository.findByEmailAndPassword(email, password);
            if (user.isPresent()) {
                return TokenResponse.builder().accessToken(jwtService.generateToken(user.get()))
                        .refreshToken(jwtService.generateRefreshToken(user.get()))
                        .userDetails(user.get())
                        .build();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
    }

    @Override
    public List<GetUserDTO> getUsers(String userType) {
        return List.of();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUSer(String id) {

    }

    @Override
    public User updateUser(String id, UserDTO user) {
        return null;
    }

    @Override
    public void requestOTP(String email, String password) {

    }
}
