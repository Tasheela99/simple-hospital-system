package com.hospital.system;

import com.hospital.system.service.UserRoleService;
import com.hospital.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HospitalApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        userRoleService.initializeUserRoles();
        userService.initializeUser();
    }
}
