import { TestBed } from '@angular/core/testing';

import { ParticipantAuthGuardService } from './participant-auth-guard.service';

describe('ParticipantAuthGuardService', () => {
  let service: ParticipantAuthGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticipantAuthGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
