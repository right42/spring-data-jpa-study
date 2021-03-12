package me.right42.springdatajpa;

import me.right42.springdatajpa.repository.DefaultMyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryImplementationPostfix = "Default",
        repositoryBaseClass = DefaultMyRepository.class
)
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);

    }

}
