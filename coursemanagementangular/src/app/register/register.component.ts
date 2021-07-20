import { Component, OnInit } from '@angular/core';
import { Admin } from '../models/admin';
import { AuthenticationService } from '../services/admin.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  admin: Admin = new Admin();
  confirmPassword!: String;

  constructor(private registerService: AuthenticationService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.admin.password != this.confirmPassword)
      alert("Password should match");
    else{
      this.registerService.saveAdmin(this.admin);
    }
  }

}
