package com.hospital.system.service.impl;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.UserDTO;
import com.hospital.system.dto.requestdto.RequestUserDTO;
import com.hospital.system.dto.requestdto.RequestUserSaveDto;
import com.hospital.system.dto.responsedto.ResponseUserDataDTO;
import com.hospital.system.enity.Doctor;
import com.hospital.system.enity.User;
import com.hospital.system.enity.UserRole;
import com.hospital.system.jwt.JwtConfig;
import com.hospital.system.repo.UserRepo;
import com.hospital.system.repo.UserRoleRepo;
import com.hospital.system.service.UserService;
import com.hospital.system.util.Generator;
import com.hospital.system.util.mapper.UserMapper;
import com.hospital.system.util.mapper.UserRoleMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final Generator generator;
    private final UserRoleRepo userRoleRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserRoleMapper userRoleMapper;

    public UserServiceImpl(UserRepo userRepo, Generator generator, UserRoleRepo userRoleRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey,UserRoleMapper userRoleMapper) {
        this.userRepo = userRepo;
        this.generator = generator;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userRoleMapper = userRoleMapper;
    }


    @Override
    public void initializeUser() throws IOException {
        String email = "tasheela.info@gmail.com";
        User user = userRepo.findByUsername(email);
        if (user == null) {
            String password = "12345";
            String userLastId = userRepo.findLastId("HMS-U-",
                    7);

            String userId = "HMS-U-1";

            if (null != userLastId) {
                int i = (Integer.parseInt(userLastId.split("HMS-U-")[1])) + 1;
                userId = "HMS-U-" + i;
            }


            UserRole userRole = userRoleRepo.findUserRoleByRoleName("ADMIN");
            UserDTO userDTO = new UserDTO(
                    userId,
                    true,
                    email,
                    "Tasheela",
                    "Jayawickrama",
                    true,
                    true,
                    true,
                    true,
                    passwordEncoder.encode(password),
                    new Date(),
                    email,
                    userRoleMapper.toUserRoleDto(userRole)
            );

            if (!userRepo.existsById(userDTO.getPropertyId())) {
                userRepo.save(userMapper.toUser(userDTO));
            }

        }

    }

    @Override
    public ResponseUserDataDTO getAllUserData(String token) {
        String realToken = token.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(realToken);
        String username = claimsJws.getBody().getSubject();
        User selectedUser = userRepo.findByUsername(username);
        System.out.println(selectedUser);

        if (selectedUser == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return new ResponseUserDataDTO(
                selectedUser.getPropertyId(),
                selectedUser.getEmail(),
                selectedUser.getFirstName(),
                selectedUser.getLastName(),
                selectedUser.getUserRole().getRoleId(),
                selectedUser.getUserRole().getRoleName()

        );
    }

    @Override
    public void createUser(RequestUserSaveDto userDTO) {
        String userLastId = userRepo.findLastId("HMS-U-",
                7);

        String userId = "HMS-U-1";

        if (null != userLastId) {
            int i = (Integer.parseInt(userLastId.split("HMS-U-")[1])) + 1;
            userId = "HMS-U-" + i;
        }
        UserRole userRole = userRoleRepo.findUserRoleByRoleName("CUSTOMER");
        UserDTO newUserDTO = new UserDTO(
                userId,
                true,
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                true,
                true,
                true,
                true,
                passwordEncoder.encode(userDTO.getPassword()),
                new Date(),
                userDTO.getEmail(),
                userRoleMapper.toUserRoleDto(userRole)

        );
        if (userRole != null) {
            if (!userRepo.existsById(newUserDTO.getPropertyId())) {
                userRepo.save(userMapper.toUser(newUserDTO));
            }
        } else {
            throw new IllegalStateException("User role was not found!");
        }
    }

    @Override
    public boolean verifyUser(String type, String token) {
        String realToken = token.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(realToken);
        String username = claimsJws.getBody().getSubject();
        User selectedUser = userRepo.findByUsername(username);
        if (null!=selectedUser){
            throw new RuntimeException("User Not Found");
        }
        if (selectedUser.getUserRole().getRoleName().equals(type)){
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();

        if (!users.isEmpty()) {
            List<UserDTO> userDtoList = new ArrayList<>();

            for (User user : users) {

                UserDTO userDto = new UserDTO(
                        user.getPropertyId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName()
                );

                userDtoList.add(userDto);
            }

            return userDtoList;
        } else {
            throw new RuntimeException("No Users Found");
        }
    }

}
