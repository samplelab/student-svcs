package com.tsk.studentservice.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.tsk.studentservice.model.Course;
import com.tsk.studentservice.model.Student;



@Component
public class StudentService {

	private static List<Student> students = new ArrayList<>();

	static {
		//Initialize Data
		Course course1 = new Course(1, "Spring", "10Steps");
		
		  Course course2 = new Course(2, "Spring MVC", "10 Examples"); 
		  
		  Course course3 = new Course(3, "Spring Boot",
		  "6K Students"); 
		  
		  Course course4 = new Course(4, "Maven", "Most popular maven course on internet!");
		  
		  Student satish = new Student(1, "Satish Kumar",
		  "developer, Programmer and Architect", new ArrayList<>(Arrays .asList(course1,
		  course2, course3, course4)));
		  
		  Student alok = new Student(2, "Alok Kumar",
		  "developer, Programmer and Architect", new ArrayList<>(Arrays .asList(course1,
		  course2, course3, course4)));
		  
		  students.add(satish); 
		  students.add(alok);
		 
	}

	public List<Student> retrieveAllStudents() {
		return students;
	}

	public Student retrieveStudent(Integer studentId) {
		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	public List<Course> retrieveCourses(Integer studentId) {
		Student student = retrieveStudent(studentId);
		
		if(studentId.equals(Integer.valueOf(1))){
			throw new RuntimeException("Exception with Student1");
		}

		if (student == null) {
			return null;
		}

		return student.getCourses();
	}

	public Course retrieveCourse(Integer studentId, Integer courseId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		for (Course course : student.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Course addCourse(Integer studentId, Course course) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}
		Random rand = new Random();
		Integer randomId = Integer.valueOf(rand.nextInt(100));

		course.setId(randomId);
		student.getCourses().add(course);

		return course;
	}
}