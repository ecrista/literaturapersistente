package com.aluralatam.retoliteratura.literaturapersistente;

import com.aluralatam.retoliteratura.literaturapersistente.principal.Principal;
import com.aluralatam.retoliteratura.literaturapersistente.repository.AutorRepositorio;
import com.aluralatam.retoliteratura.literaturapersistente.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturapersistenteApplication implements CommandLineRunner {
	@Autowired
	private LibroRepositorio libroRepository;
	@Autowired
	private AutorRepositorio autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraturapersistenteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.MostrarMenu();
	}

}
