package com.everis.course.app.controllerTest;

import com.everis.course.app.model.Course;
import com.everis.course.app.repository.CourseRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CourseControllerTest {

  /**
   * Injected AppContext.
   */
  @Autowired
  private ApplicationContext applicationContext;

  /**
   * .
   */
  @Autowired
  private CourseRepository courseRepository;
  private WebTestClient client;
  private List<Course> expectedCourses;

  /**
   * .
   */
  @BeforeEach
  void setUp() {
    client = WebTestClient
      .bindToApplicationContext(applicationContext)
      .configureClient()
      .baseUrl("/api/v1.0")
      .build();

    Flux<Course> initData = courseRepository.deleteAll()
        .thenMany(Flux.just(
        Course.builder().id("1").name("Robotica").state("Abierto").duration("24Hrs").capmax("25")
        .capmin("12").teacherID("10000001").listenerID("10000002").studentID("10000003").build(),
        Course.builder().id("2").name("Fisica").state("Abierto").duration("26Hrs").capmax("28")
        .capmin("15").teacherID("20000001").listenerID("20000002").studentID("20000003").build())
        .flatMap(courseRepository::save))
        .thenMany(courseRepository.findAll());

    expectedCourses = initData.collectList().block();
  }

  /**
   * Test FindAll.
   */
  @Test
  void findAll() {
    client.get().uri("/courses").exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Save.
   */
  @Test
  void save() {
    Course expectedTeach = expectedCourses.get(0);
    client.post().uri("/courses").body(Mono.just(expectedTeach), Course.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test findByID.
   */
  @Test
  void findById() {

    String id = "1";
    client.get().uri("/courses/{id}", id).exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Update.
   */
  @Test
  void update() {

    Course expectedCourse = expectedCourses.get(0);

    client.put().uri("/courses/{id}", expectedCourse.getId())
    .body(Mono.just(expectedCourse), Course.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test Delete.
   */
  @Test
  void delete() {
    Course teachDelete = expectedCourses.get(0);
    client.delete().uri("/courses/{id}", teachDelete.getId()).exchange()
      .expectStatus().isOk();
  }

  /**
   * Test FindByName.
   */
  @Test
  void findByName() {
    String name = "Fisica";
    client.get().uri("/courses/name/{name}", name).exchange()
      .expectStatus().isOk();
  }
}
