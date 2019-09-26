package com.everis.course.app.service.impl;

import com.everis.course.app.model.Course;
import com.everis.course.app.repository.CourseRepository;
import com.everis.course.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseServiceImpl implements CourseService {

  /**
   * Injected courseRepository.
   */
  @Autowired
  private CourseRepository courseRepository;

  /**
   * Implement findAll.
   */
  @Override
  public Flux<Course> findAll() {
    return courseRepository.findAll();
  }

  /**
   * Implement findById.
   */
  @Override
  public Mono<Course> findById(String id) {
    return courseRepository.findById(id);
  }

  /**
   * Implement save.
   */
  @Override
  public Mono<Course> save(Course course) {
    return courseRepository.save(course);
  }

  /**
   * Implement delete.
   */
  @Override
  public Mono<Void> delete(Course course) {
    return courseRepository.delete(course);
  }

  /**
   * Implement findByName.
   */
  @Override
  public Flux<Course> findByName(String name) {
    return courseRepository.findByName(name);
  }

  /**
   * Implement obtenerporname.
   */
  @Override
  public Mono<Course> obtenerPorName(String name) {
    return courseRepository.findName(name);
  }

}
