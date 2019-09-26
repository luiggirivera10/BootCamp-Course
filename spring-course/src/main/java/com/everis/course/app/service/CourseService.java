package com.everis.course.app.service;

import com.everis.course.app.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
public interface CourseService {
  /**
 * Metodo FindAll.
 */
  Flux<Course> findAll();

  /**
 * Metodo FindById.
 */
  Mono<Course> findById(String id);

  /**
 * Metodo save.
 */
  Mono<Course> save(Course course);

  /**
 * Metodo delete.
 */
  Mono<Void> delete(Course course);

  /**
 * findByName.
 */
  Flux<Course> findByName(String name);

  /**
 * Solo para TEST.
 */
  Mono<Course> obtenerPorName(String name);
}
