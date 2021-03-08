import { Router } from '@angular/router';
import { StudentService } from './../student.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(
    private studentService : StudentService,
    private router : Router
  ) { }

  ngOnInit(): void {
  }

  myForm=new FormGroup(
    {
      email:new FormControl('',[Validators.required,Validators.email]),
      password:new FormControl('',[Validators.required,Validators.minLength(8)]),
      mobileNo:new FormControl('',[Validators.required,Validators.minLength(10),Validators.maxLength(10)]),
      name:new FormControl('',[Validators.required])
    });

    get email(){
      return this.myForm.get('email');
    }
    
    get password(){
      return this.myForm.get('password');
    }

    get mobileNo(){
      return this.myForm.get('mobileNo');
    }

    get name(){
      return this.myForm.get('name');
    }

  submitForm(){
    const body = {
      email : this.email.value,
      password : this.password.value,
      mobile : this.mobileNo.value,
      name : this.name.value
    };
    console.log(body);
    this.studentService.signup(body).subscribe(response=>{
      if(response['status']=="success"){
        window.alert("You have been succesfully registered !");
        this.router.navigate(["/login"])
      }
      else{
        window.alert("Some error occured! Please try again !");
      }
    });
  }

  resetForm(){
    this.myForm.reset();
  }

}
