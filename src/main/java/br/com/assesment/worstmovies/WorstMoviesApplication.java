package br.com.assesment.worstmovies;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorstMoviesApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorstMoviesApplication.class, args);
    }
}
