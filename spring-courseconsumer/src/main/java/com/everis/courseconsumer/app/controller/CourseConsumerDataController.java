package com.everis.courseconsumer.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.everis.courseconsumer.app.service.CourseConsumerService;

@RestController
public class CourseConsumerDataController {

  /**
   * Injected CourseConsumerService.
   */
  CourseConsumerService courseService;

  /**
   * .
   */
  @GetMapping(value="/getCourseInfo")
  public String getCursos() {
    System.out.println("Making a call to tha course application");
    return courseService.callCourseApplication();
  }
}
