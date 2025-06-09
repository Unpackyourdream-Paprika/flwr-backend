package com.flwr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlwrApplication {

  public static void main(String[] args) {
    // SpringApplication.run(FlwrApplication.class, args);

    SpringApplication app = new SpringApplication(FlwrApplication.class);
    app.run(args);
  }

}
