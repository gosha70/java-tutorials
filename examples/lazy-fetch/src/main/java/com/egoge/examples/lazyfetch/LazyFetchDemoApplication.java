package com.egoge.examples.lazyfetch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
    "com.egoge.examples.lazyfetch",
    "com.egoge.json",
    "com.egoge.sdlc"
})
@EntityScan(basePackages = "com.egoge.sdlc.entity")
public class LazyFetchDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(LazyFetchDemoApplication.class, args);
  }
}
