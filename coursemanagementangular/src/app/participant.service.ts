import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Participant } from './participant';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  private baseURL = "http://localhost:8080/participant/";

  constructor(private httpClient: HttpClient) { }

  getParticipantsList(): Observable<Participant[]>{
    return this.httpClient.get<Participant[]>(`${this.baseURL}`);
  }
  createParticipant(participant: Participant): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, participant);
  }
  deleteParticipant(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
