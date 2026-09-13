package cephei.dev.auth_service.service;

import cephei.dev.auth_service.dto.AccountCreateDto;
import cephei.dev.auth_service.dto.AccountLoginDto;
import cephei.dev.auth_service.dto.UserSessionReadDto;
import cephei.dev.auth_service.entity.Credentials;
import cephei.dev.auth_service.entity.Role;
import cephei.dev.auth_service.mapper.CredentialsMapper;
import cephei.dev.auth_service.repository.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final CredentialsMapper credentialsMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CredentialsRepository credentialsRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void register(AccountCreateDto accountCreateDto) {
        Credentials credentials = credentialsMapper.toEntity(accountCreateDto);
        credentials.setRole(Role.USER);

        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentialsRepository.save(credentials);
    }

    public UserSessionReadDto verify(AccountLoginDto accountLoginDto) {
        Credentials credentials = credentialsRepository.findByUsername(accountLoginDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountLoginDto.username(), accountLoginDto.password()
                )
        );

        if(authentication.isAuthenticated()) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", credentials.getRole());

            String token = jwtService.generateToken(claims, accountLoginDto.username());

             return new UserSessionReadDto(
                     accountLoginDto.username(),
                     token
             );
        }

        return null;
    }
}
