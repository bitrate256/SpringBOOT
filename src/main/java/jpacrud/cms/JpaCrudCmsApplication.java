package jpacrud.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing
public class JpaCrudCmsApplication {
	@Bean
	public PasswordEncoder passwordEncoder(){return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(JpaCrudCmsApplication.class, args);
	}

}
