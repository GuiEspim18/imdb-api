package com.api.imdbApi;

import com.api.imdbApi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class ImdbApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ImdbApiApplication.class, args);
	}

	@Override
	public void run (String... args) throws InvocationTargetException, IllegalAccessException {
		Principal.show();
	}

}
