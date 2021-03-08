package com.wizpanda.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizpanda.app.pojo.Student;
import com.wizpanda.app.service.StudentService;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@RequestBody Student request,HttpServletRequest httpRequest){
		Student studentObj = new Student(request.getName(),request.getEmail(),request.getMobile(),request.getPassword());
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			//Adding student to database
			Student resp = service.addStudent(studentObj);
			
			//Adding student object and status to response entity
			returnObj.put("status","success");
			returnObj.put("data",resp);
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			//Adding error details and status to response entity
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> authenticateStudent(@RequestBody Student request,HttpServletRequest httpRequest){
		//Student studentObj = new Student(request.getName(),request.getEmail(),request.getMobile(),request.getPassword());
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			//Authenticating student
			Student resp = service.authenticateStudent(request.getEmail(), request.getPassword());
			if(resp!=null) {
				//Adding student object and status to response entity
				returnObj.put("status","success");
				returnObj.put("data",resp);
			}
			else {
				//Adding no user found and status to response entity
				returnObj.put("status","invalid");
				returnObj.put("data","user not found");
			}
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			//Adding error details and status to response entity
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllStudents(HttpServletRequest httpRequest){
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			//Fetching all students from database
			List<Student> resp = service.getAllStudents();
			if(resp!=null) {
				//Adding student list and status to response entity
				returnObj.put("status","success");
				returnObj.put("data",resp);
			}
			else {
				//Adding empty list and status to response entity
				returnObj.put("status","empty");
				returnObj.put("data","no user");
			}
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			//Adding error details and status to response entityd
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
