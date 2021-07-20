import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Participant } from '../models/participant';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-manage-participants',
  templateUrl: './manage-participants.component.html',
  styleUrls: ['./manage-participants.component.css']
})
export class ManageParticipantsComponent implements OnInit {

  participantList: Participant[] = [];
  participant: Participant = new Participant();

  constructor(private participantService: ParticipantService, private router: Router) { }

  ngOnInit(): void {
    this.participantService.getParticipantsList().subscribe(data => {
      this.participantList = data;
    }, error => console.log(error));
  }

  refreshPage(){
    window.location.reload();
  }

  deleteParticipant(id: number){
    this.participantService.deleteParticipant(id).subscribe(data => {
      console.log(data);
      this.refreshPage();
    }, error => console.log(error));
  }
}
