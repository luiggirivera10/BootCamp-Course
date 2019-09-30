package com.everis.course.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Model.
 * @author lriveras
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "courses")
public class Course {
  /**
   * .
   */
  @Id
 private String id;
  /**
   * .
   */
  @NotEmpty(message = "'name' No debe ser vacio!")
 private String name;
  /**
   * .
   */
  @NotEmpty(message = "'state' No debe ser vacio!")
 private String state;
  /**
 * .
 */
  @NotEmpty(message = "'cap_max' No debe ser vacio!")
 private String capmax;
  /**
 * .
 */
  @NotEmpty(message = "'cap_min' No debe ser vacio!")
 private String capmin;
  /**
 * .
 */
  @NotEmpty(message = "'duration' No debe ser vacio!")
 private String duration;
  /**
   * .
   */
  @NotEmpty(message = "'teacherID' No debe ser vacio!")
  @Size(min = 8, max = 8,message = "'teacherID' debe tener 8 caracteres")
 private String teacherID;
  /**
   * .
   */
  @NotEmpty(message = "'listenerID' No debe ser vacio!")
  @Size(min = 8, max = 8,message = "'listenerID' debe tener 8 caracteres")
 private String listenerID;
 

  /**
   * .
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date createdAt = new Date();
  /**
   * .
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date modifiedAt = new Date();  
}
