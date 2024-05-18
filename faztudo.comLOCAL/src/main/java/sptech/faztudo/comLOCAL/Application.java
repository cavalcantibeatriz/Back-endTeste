package sptech.faztudo.comLOCAL;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.sql.Connection;

@SpringBootApplication
public class  Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


	}

}
