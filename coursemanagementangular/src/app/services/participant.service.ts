import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Course } from '../models/course';
import { Participant } from '../models/participant';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  private baseURL = "http://localhost:8080/participant";
  username!: string;
  resp!: number;

  constructor(private router: Router,
    private httpClient: HttpClient) { }

  getParticipantsList(): Observable<Participant[]>{
    return this.httpClient.get<Participant[]>(`${this.baseURL}`);
  }

  saveParticipant(participant: Participant){
    this.httpClient.post(`${this.baseURL}/save`, participant).subscribe(data => {
      if(data == 0){
        alert("Participant Registered successfully");
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
    this.router.navigate(['courseList']);
  }
  
  deleteParticipant(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
  
  isParticipantLoggedIn() {
    let user = sessionStorage.getItem('username');
    let userType = sessionStorage.getItem('userType');
    return (!(user === null)&&(userType === 'participant'));
  }

  authenticateParticipant(participant: Participant): boolean {
    if(sessionStorage.getItem('userType') != null){
      alert("Logout First");
      return false;
    }
    this.isParticipantLoginCorrect(participant);
    if(this.resp > 0)
      return true;
    else
      return false;
  }

  isParticipantLoginCorrect(participant: Participant){
    return this.httpClient.post(`${this.baseURL}/login`, participant).subscribe(data => {
      this.resp = data as number;
      if(this.resp < 0){
        alert("Enter correct credentials");
        return false;
      }
      this.username = participant.username as string;
      console.log(this.username);
      sessionStorage.setItem('username', this.username);
      sessionStorage.setItem('userType', 'participant');
      return true;
    }, error => console.log(error));   
  }

  getMyCourses(username: String): Observable<Course[]>{
    console.log(username);
    return this.httpClient.get<Course[]>(`${this.baseURL}/courses/${username}`);
  }

  subscribeCourse(username: String, courseId: number){
    this.httpClient.get(`${this.baseURL}/subscribe/${username}/${courseId}`).subscribe(data => {
      if(data > 0){
        alert("Course Subscribed Successfully");
      } else{
        alert("Course already subscribed");
      }
    })
  }

  unsubscribeCourse(username: string, courseId: number): Observable<Object>{
    return this.httpClient.get(`${this.baseURL}/unsubscribe/${username}/${courseId}`);
  }
}
