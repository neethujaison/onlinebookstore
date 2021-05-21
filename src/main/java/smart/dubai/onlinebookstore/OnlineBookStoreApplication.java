package smart.dubai.onlinebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title= "OnlineBookStore API",version="1.0",description ="BookStore Checkout Application"))
public class OnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStoreApplication.class, args);
	}

}
