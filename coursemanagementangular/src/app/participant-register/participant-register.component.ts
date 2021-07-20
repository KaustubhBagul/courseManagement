import { Component, OnInit } from '@angular/core';
import { Participant } from '../models/participant';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-participant-register',
  templateUrl: './participant-register.component.html',
  styleUrls: ['./participant-register.component.css']
})
export class ParticipantRegisterComponent implements OnInit {

  participant: Participant = new Participant();
  confirmPassword!: String;

  constructor(private participantService: ParticipantService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.participant.password != this.confirmPassword)
      alert("Password should match");
    else{
      this.participantService.saveParticipant(this.participant);
    }
  }
}
