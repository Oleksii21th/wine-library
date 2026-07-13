package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;
import eu.babych.winelibrary.exception.UserAlreadyExistsRegistrationException;
import eu.babych.winelibrary.mapper.UserMapper;
import eu.babych.winelibrary.model.Role;
import eu.babych.winelibrary.model.User;
import eu.babych.winelibrary.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Role DEFAULT_ROLE = Role.CUSTOMER;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto registrationRequestDto) {
        String usersEmail = registrationRequestDto.email();

        if (userRepository.findByEmail(usersEmail).isPresent()) {
            throw new UserAlreadyExistsRegistrationException(usersEmail);
        }

        User user = userMapper.toModel(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(registrationRequestDto.password()));
        user.setRole(DEFAULT_ROLE);
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
