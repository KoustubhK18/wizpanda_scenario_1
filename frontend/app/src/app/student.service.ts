import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private httpClient : HttpClient,
    private router : Router
  ) { }

  login(body){
    return this.httpClient.post("http://localhost:5000/student/auth",body);
  }

  signup(body){
    return this.httpClient.post("http://localhost:5000/student/add",body);
  }

  getAllStudents(){
    return this.httpClient.get("http://localhost:5000/student/getAll");
  }

}
