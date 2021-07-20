import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/admin.service';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username: string | null = sessionStorage.getItem('username');

  constructor(private adminService: AuthenticationService,
    private participantService: ParticipantService) { }

  ngOnInit(): void {
  }

  isAdminLoggedIn(): boolean{
    return this.adminService.isAdminLoggedIn();
  }
  isParticipantLoggedIn(): boolean{
    return this.participantService.isParticipantLoggedIn();
  }

  logout(){
    if(sessionStorage.getItem('userType') === null)
      alert("You haven't Logged in yet.");
    else if(sessionStorage.getItem('userType') === 'admin')
      this.adminService.logout();
    else
      this.participantService.logout();
  }

}
