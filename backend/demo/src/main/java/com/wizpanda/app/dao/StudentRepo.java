package com.wizpanda.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizpanda.app.pojo.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	Student findByEmailAndPassword(String email, String password);
	List<Student> findAll();
}
