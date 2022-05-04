package com.backend.lavender.database;

import com.backend.lavender.repositories.CustomerRepository;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=123456 \
-e MYSQL_USER=hoangnd \
-e MYSQL_PASSWORD=123456 \
-e MYSQL_DATABASE=test_db \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u hoangnd -p

* */
@Configuration
public class Database {
    //logger
    // private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(CustomerRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
            }
        };
    }
}
