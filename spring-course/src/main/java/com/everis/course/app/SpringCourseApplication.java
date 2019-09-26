package com.everis.course.app;

import com.everis.course.app.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * .
 * @author lriveras
 *
 */
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableDiscoveryClient
public class SpringCourseApplication {
  /**
   * .
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringCourseApplication.class, args);
  }

}
