import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../models/admin';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseURL = "http://localhost:8080/admin/";
  username!: string;
  resp!: number;
  constructor(private router: Router,
    private httpClient: HttpClient) { }

  authenticateAdmin(admin: Admin): boolean {
    this.isAdminLoginCorrect(admin);
    if(this.resp > 0)
      return true;
    else
      return false;
  }

  isAdminLoginCorrect(admin: Admin){
    return this.httpClient.post(`${this.baseURL}/login`, admin).subscribe(data => {
      this.resp = data as number;
      if(this.resp < 0){
        alert("Enter correct credentials");
        return false;
      }
      this.username = admin.username as string;
      console.log(this.username);
      sessionStorage.setItem('username', this.username);
      sessionStorage.setItem('userType', 'admin');
      return true;
    }, error => console.log(error));   
  }

  isAdminLoggedIn() {
    let user = sessionStorage.getItem('username');
    let userType = sessionStorage.getItem('userType');
    return (!(user === null)&&(userType === 'admin'));
  }

  saveAdmin(admin: Admin){
    this.httpClient.post(`${this.baseURL}/save`, admin).subscribe(data => {
      if(data == 0){
        alert("Admin Registered successfully");
        this.router.navigate(['']);
      } else if(data == 1)
        alert("Email already exists");
      else
        alert("Username already exists");
    })
  }

  logout() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('userType');
    this.router.navigate(['login']);
  }
}
