package com.wizpanda.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizpanda.app.dao.StudentRepo;
import com.wizpanda.app.pojo.Student;

@Service
public class StudentService {
	@Autowired
	public StudentRepo studentRepo;
	
	public Student addStudent(Student studentObj) {
		return this.studentRepo.save(studentObj);
	}
	
	public List<Student> getAllStudents(){
		return this.studentRepo.findAll();
	}
	
	public Student authenticateStudent(String email, String password) {
		return this.studentRepo.findByEmailAndPassword(email, password);
	}
	
}
