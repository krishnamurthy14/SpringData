package com.kgfsl.springdata1.controller;

import java.util.Arrays;
import java.util.List;

import com.kgfsl.springdata1.model.Student;
import com.kgfsl.springdata1.repository.StudentRepository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
  @InjectMocks
  private StudentController studentController;
  @Mock
  private StudentRepository studentRepository;

  public static List<Student> expectedStudents;

  
  @Test
  public void findStudentsTest() {
    // Assign
    Student student1 = new Student(1L, "KGiSL");
    Student student2 = new Student(1L, "KGfSL");

    expectedStudents = Arrays.asList(student1, student2);
    when(studentRepository.findAll()).thenReturn(expectedStudents);
    // Action
    List<Student> actualStudents = studentController.findStudents();

    // Assert-
    assertNotNull(actualStudents);
    assertEquals(2, actualStudents.size());
    assertEquals(expectedStudents, actualStudents);
  }

  @Test
  public void findoneTest() {
    // Assign
    Long studentId=1L;
    Student student1 = new Student(1L, "KGiSL");
   
    when(studentRepository.findOne(studentId)).thenReturn(student1);

    // Action
    Student student = studentController.findone(studentId);

    // Assert-
    assertNotNull(student);
    assertEquals(studentId, student.getId());
    assertEquals("KGiSL", student.getName());
  }

  @Test
  public void addStudentTest() {
    
    Student student1 = new Student();
    student1.setName("KGiSL");
    Student student2 = new Student(1L, "KGiSL");
    when(studentRepository.saveAndFlush(student1)).thenReturn(student2);

      

    System.out.println("??????????????"+studentController.addStudent(student1));  
    Student AR = studentController.addStudent(student1);
    System.out.println("%%%%%%%%%%%%%%AR"+AR);
    
    
    assertEquals("KGiSL", AR.getName());
  assertEquals(student2,AR);  
  }
  @Test  
  public void updateStudentTest()
  {
    Long studentId=1L;
    Student student1 = new Student(1L, "KGiSL");
    Student student2 = new Student(1L, "KGfSL");

    when(studentRepository.saveAndFlush(student2)).thenReturn(student2);
    Student student = studentController.updateStudent(student2, studentId);

    assertNotNull(student);
    assertEquals(student, student2);
  }
  
  
  @Test
  public void deleteStudentTest() {
    Long studentId=2L;
    // Student student1= new Student(1L, "KGiSL");
    // Student student2= new Student(2L, "KGfSL");

    studentController.deleteStudent(studentId);
    verify(studentRepository).delete(studentId);
    
    // Action
    
    // Assert-
  //  assertEquals(1,expectedStudents.size() );
    
  }
 

}