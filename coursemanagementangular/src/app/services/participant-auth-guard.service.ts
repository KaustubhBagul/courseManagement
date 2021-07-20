import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { ParticipantService } from './participant.service';

@Injectable({
  providedIn: 'root'
})
export class ParticipantAuthGuardService {
  
  constructor(private router: Router,
    private participantService: ParticipantService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.participantService.isParticipantLoggedIn())
      return true;

    this.router.navigate(['login']);
    return false;
  }
}
