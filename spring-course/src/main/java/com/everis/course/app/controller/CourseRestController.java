package com.everis.course.app.controller;

import com.everis.course.app.model.Course;
import com.everis.course.app.service.CourseService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@RestController
@RefreshScope
@RequestMapping("/api/v1.0")
public class CourseRestController {

  /**
   * Injected LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CourseRestController.class);

  /**
 * Injected service.
 */
  @Autowired
  private CourseService service;

  /**
   * . Service listar Course.
   */
  @GetMapping("/courses")
  public Mono<ResponseEntity<Flux<Course>>> findAll() {
    return Mono.just(

        ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
  }

  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/courses/{id}")
 public Mono<Course> findById(@PathVariable final String id) {
    final Flux<Course> courses = service.findAll();
    final Mono<Course> course = courses.filter(s -> s.getId().equals(id))
                        .next().doOnNext(cours -> LOG.info(cours.getName()));
    return course;
  }

  /**

   * Servicio para buscar por nombre devuelve una lista.

   */
  @GetMapping("/courses/name/{name}")
  public Flux<Course> findByName(@PathVariable ("name") final String name) {
    return service.findByName(name)
        .doOnNext(teach -> LOG.info(teach.getName()));
  }

  /**
 * .
 */
  @GetMapping("/courses/nombre/{name}")
  public Mono<ResponseEntity<Course>> getByName(@PathVariable ("name") final String name) {
    return service.obtenerPorName(name).doOnNext(teach -> LOG.info(teach.getName()))
              .map(teacher -> new ResponseEntity<>(teacher, HttpStatus.FOUND))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**
   * . Service save
   */

  @PostMapping("/courses")
  public Mono<ResponseEntity<Course>> newCourse(@Valid @RequestBody final Course course) {
    return service.save(course)
              .map(addCourse-> new ResponseEntity<>(addCourse, HttpStatus.CREATED))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
  }

  /**

   * Servicio para modificar.

   */
  @PutMapping("/courses/{id}")
    public Mono<ResponseEntity<Course>> updateTeacher(@PathVariable(value = "id") final String id,
                                                   @Valid @RequestBody final Course course) {
    return service.findById(id)
                .flatMap(existingCourse -> {
                  existingCourse.setName(course.getName());
                  existingCourse.setState(course.getState());
                  existingCourse.setCapmax(course.getCapmax());
                  existingCourse.setCapmin(course.getCapmin());
                  existingCourse.setDuration(course.getDuration());
                  existingCourse.setTeacherID(course.getTeacherID());
                  existingCourse.setStudentID(course.getStudentID());
                  existingCourse.setListenerID(course.getListenerID());
                  existingCourse.setModifiedAt(course.getModifiedAt());
                  return service.save(existingCourse);
                })
                .map(updateCourse -> new ResponseEntity<>(updateCourse, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicio para eliminar.

   */
  @DeleteMapping("/courses/{id}")
  public Mono<ResponseEntity<Void>> deleteCourse(@PathVariable(value = "id") final String id) {
    return service.findById(id)
    .flatMap(existingCourse ->
 service.delete(existingCourse)
 .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
 .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
