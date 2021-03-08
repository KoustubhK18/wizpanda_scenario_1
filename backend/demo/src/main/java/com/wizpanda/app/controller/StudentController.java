package com.wizpanda.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizpanda.app.pojo.Student;
import com.wizpanda.app.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping
	public ResponseEntity<?> addStudent(@RequestBody Student request,HttpServletRequest httpRequest){
		Student studentObj = new Student(request.getName(),request.getEmail(),request.getMobile(),request.getPassword());
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			Student resp = service.addStudent(studentObj);
			returnObj.put("status","success");
			returnObj.put("data",resp);
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/auth")
	public ResponseEntity<?> authenticateStudent(@RequestBody Student request,HttpServletRequest httpRequest){
		//Student studentObj = new Student(request.getName(),request.getEmail(),request.getMobile(),request.getPassword());
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			Student resp = service.authenticateStudent(request.getEmail(), request.getPassword());
			if(resp!=null) {
				returnObj.put("status","success");
				returnObj.put("data",resp);
			}
			else {
				returnObj.put("status","invalid");
				returnObj.put("data","user not found");
			}
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllStudents(HttpServletRequest httpRequest){
		//Student studentObj = new Student(request.getName(),request.getEmail(),request.getMobile(),request.getPassword());
		Map<String,Object> returnObj = new HashMap<String,Object>();
		try {
			List<Student> resp = service.getAllStudents();
			if(resp!=null) {
				returnObj.put("status","success");
				returnObj.put("data",resp);
			}
			else {
				returnObj.put("status","empty");
				returnObj.put("data","no user");
			}
			return new ResponseEntity<>(returnObj,HttpStatus.OK);
		}
		catch(Exception ex) {
			returnObj.put("status","error");
			returnObj.put("data",ex.getMessage());
			return new ResponseEntity<>(returnObj,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
