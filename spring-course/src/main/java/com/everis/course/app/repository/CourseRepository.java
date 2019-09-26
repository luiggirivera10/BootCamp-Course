package com.everis.course.app.repository;

import com.everis.course.app.model.Course;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

  /**
 * findByName.
 */
  Flux<Course> findByName(String name);
 
  /**
 * Solo para TEST.
 */
  @Query("{ 'name': ?0 }")
  Mono<Course> findName(String name);
}
