import { Router } from '@angular/router';
import { StudentService } from './../student.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  constructor(
    private studentService : StudentService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllStudents();
  }

  studentList;

  getAllStudents(){
    this.studentService.getAllStudents().subscribe(response=>{
      if(response['status'] == "success"){
        this.studentList = response['data'];
      }
      else{
        window.alert("Student list is empty!");
      }
    })
  }
}
