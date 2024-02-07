package api.coindesk.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CoinDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinDeskApplication.class, args);
	}

}
