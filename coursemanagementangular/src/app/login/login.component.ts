import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../models/admin';
import { Participant } from '../models/participant';
import { AuthenticationService } from '../services/admin.service';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  admin: Admin = new Admin();
  participant: Participant = new Participant();

  constructor(private router: Router,
    private loginservice: AuthenticationService,
    private participantService: ParticipantService) { }

  ngOnInit(): void {
  }

  onAdminSubmit() {
    if (this.loginservice.authenticateAdmin(this.admin)) {
      this.router.navigate(['courseList']);
    }
  }

  onParticipantSubmit() {
    if (this.participantService.authenticateParticipant(this.participant)) {
      this.router.navigate(['courseList']);
    }
  }
}
