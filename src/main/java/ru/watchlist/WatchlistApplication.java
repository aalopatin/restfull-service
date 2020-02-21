package ru.watchlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.watchlist.config.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class WatchlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlistApplication.class, args);
	}

}
