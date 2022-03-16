package com.example.kallekirjakauppa;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.kallekirjakauppa.domain.Book;
import com.example.kallekirjakauppa.domain.BookRepository;
import com.example.kallekirjakauppa.domain.Category;
import com.example.kallekirjakauppa.domain.CategoryRepository;
import com.example.kallekirjakauppa.domain.User;
import com.example.kallekirjakauppa.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class KallekirjakauppaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KallekirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KallekirjakauppaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kirjaKauppademo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Romance"));
			
			repository.save(new Book("Jari Tervo", "Batman", "1122233", 1220, crepository.findByName("Horror").get(0)));
			repository.save(new Book("Kari Hotakainen", "Spiderman", "33444222", 1540, crepository.findByName("Thriller").get(0)));	
			
			User user1 = new User("Kalle", "$2a$12$RtqW9oeA3y2BSZZ5CQ0iCujydkjtlfav9hvTnP35aVgSUu42rzD3O", "USER");
			User user2 = new User("Kalleadmin", "$2a$12$exJKMn2THTAfnlPtjjXz2.LvEqyYzQYmh5touXyYhlRUNiih683Iq", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
