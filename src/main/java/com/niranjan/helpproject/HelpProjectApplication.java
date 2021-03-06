package com.niranjan.helpproject;

import com.niranjan.helpproject.Security.Model.Role;
import com.niranjan.helpproject.Security.Model.RoleName;
import com.niranjan.helpproject.Security.Model.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HelpProjectApplication {

    public static void main(String[] args) {
      SpringApplication.run(HelpProjectApplication.class, args);
    }

    @Component
    class Runner implements CommandLineRunner {
        @Autowired
        RoleRepository roleRepository;


        @Override
        public void run(String... args) throws Exception {
            Role role = new Role();
//            if(roleRepository.findByName(RoleName.ROLE_ADMIN)==null) {
//                role.setName(RoleName.ROLE_ADMIN);
//                roleRepository.save(role);
//            }

            }
        }

}
