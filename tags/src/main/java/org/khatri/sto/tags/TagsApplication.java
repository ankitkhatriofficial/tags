package org.khatri.sto.tags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TagsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagsApplication.class, args);
	}

}
