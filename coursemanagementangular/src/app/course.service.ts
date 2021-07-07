import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from './course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private baseURL = "http://localhost:8080/course/";

  constructor(private httpClient: HttpClient) { }

  getCoursesList(): Observable<Course[]>{
    return this.httpClient.get<Course[]>(`${this.baseURL}`);
  }
  createCourse(course: Course): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, course);
  }
  deleteCourse(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
  updateCourse(id: number, course: Course): Observable<Object>{
    return this.httpClient.put<Course>(`${this.baseURL}/${id}`, course);
  }
  getCourseById(id: number): Observable<Course>{
    return this.httpClient.get<Course>(`${this.baseURL}/${id}`);
  }
  getCourseByLocation(location: String): Observable<Course[]>{
    return this.httpClient.get<Course[]>(`${this.baseURL}/loc/${location}`);
  }
  getCourseBySkill(skill: String): Observable<Course[]>{
    return this.httpClient.get<Course[]>(`${this.baseURL}/skill/${skill}`);
  }
}
