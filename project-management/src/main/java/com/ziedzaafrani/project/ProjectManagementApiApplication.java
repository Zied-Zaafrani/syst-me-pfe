package com.ziedzaafrani.project;

import com.ziedzaafrani.project.role.Role;
import com.ziedzaafrani.project.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "com.ziedzaafrani.project")
public class ProjectManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findBynomRole("USER").isEmpty()) {
				roleRepository.save(Role.builder().nomRole("USER").build());
			}
			if (roleRepository.findBynomRole("ADMIN").isEmpty()) {
				roleRepository.save(Role.builder().nomRole("ADMIN").build());
			}
		};
	}

}
