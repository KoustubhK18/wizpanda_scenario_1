import { Router } from '@angular/router';
import { StudentService } from './../student.service';
import { Component, OnInit } from '@angular/core';
import {FormGroup,FormControl,Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private studentService : StudentService,
    private router : Router
  ) { }

  ngOnInit(): void {
  }

  myForm=new FormGroup(
    {
      email:new FormControl('',[Validators.required,Validators.email]),
      password:new FormControl('',[Validators.required,Validators.minLength(8)])
    });

    get email(){
      return this.myForm.get('email');
    }
    
    get password(){
      return this.myForm.get('password');
    }

  submitForm(){
    const body = {
      email : this.email.value,
      password : this.password.value
    };
    console.log(body);
    this.studentService.login(body).subscribe(response=>{
      if(response['status']=="success"){
        window.alert("Welcome "+response['data']['name']+" !");
      }
      else{
        window.alert("Invalid credentials! Please try again !");
      }
    });
  }

  resetForm(){
    this.myForm.reset();
  }
}
