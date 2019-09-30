package com.everis.course.app.serviceTest;

import static org.mockito.Mockito.when;

import com.everis.course.app.model.Course;
import com.everis.course.app.repository.CourseRepository;
import com.everis.course.app.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * .
 * @author lriveras
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CourseServiceImplTestService {

  /**
 * Injec ParenRepository.
 */
  @Mock
  private CourseRepository courseRepository;
  
  /**
   * Inject CourseServiceImpl.
   */
  @InjectMocks
  private CourseServiceImpl courseService;

  /**
 * TestService findAll.
 */
  @Test
  public void findAllTest() {
    final Course course = new Course();
    course.setId("102");
    course.setName("Matematica");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");
    when(courseService.findAll()).thenReturn(Flux.just(course));
    final Flux<Course> actua = courseService.findAll();
    assertResults(actua, course);
  }

  /**
   * findById-Exist.
   */
  @Test
  public void findById_exist_ServiceImplTest() {
    final Course course = new Course();
    course.setId("103");
    course.setName("MatematicaII");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");
    when(courseRepository.findById(course.getId())).thenReturn(Mono.just(course));
    final Mono<Course> actual = courseRepository.findById(course.getId());
    assertResults(actual, course);
  }

  /**
   * findById-not-Exist.
   */
  @Test
  public void findById_not_exist_ServiceImplTest() {
    final Course course = new Course();
    course.setId("104");
    course.setName("MatematicaIII");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");
    when(courseRepository.findById(course.getId())).thenReturn(Mono.empty());
    final Mono<Course> actual = courseRepository.findById(course.getId());
    assertResults(actual);
  }

  /**
   * save.
   */
  @Test
  public void saveServiceImplTest() {
    final Course course = new Course();
    course.setId("105");
    course.setName("MatematicaIV");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");
    when(courseRepository.save(course)).thenReturn(Mono.just(course));
    final Mono<Course> actual = courseService.save(course);
    assertResults(actual, course);
  }
  
  /**
   * deleteTest.
   */
  @Test
  public void deleteServiceImplTest() {
    final Course course = new Course();
    course.setId("106");
    course.setName("MatematicaVI");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");    
    when(courseRepository.delete(course)).thenReturn(Mono.empty());
  }

  /**
   * findByName.
   */
  @Test
  public void findByName_ServiceImplTest() {
    final Course course = new Course();
    course.setId("107");
    course.setName("MatematicaVII");
    course.setState("abierto");
    course.setCapmax("25");
    course.setCapmin("12");
    course.setDuration("24Hrs");
    course.setTeacherID("12312311");
    course.setListenerID("12312313");
    final String name = "dekweowe";
    when(courseRepository.findByName(name)).thenReturn(Flux.just(course));
    final Flux<Course> actual = courseService.findByName(name);
    assertResults(actual,course);
  }

  /**
 * assertResults.
 */
  private void assertResults(final Publisher<Course> publisher, Course... expectedCourses) {
    StepVerifier
        .create(publisher)
        .expectNext(expectedCourses)
        .verifyComplete();
  }
}
