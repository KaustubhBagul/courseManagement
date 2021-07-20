import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { CourseService } from '../services/course.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/admin.service';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  courses: Course[] = [];
  username: string | null = sessionStorage.getItem('username');

  constructor(private courseService: CourseService, private router: Router,
    private adminService: AuthenticationService,
    private participantService: ParticipantService) { }

  ngOnInit(): void {
    this.getCourses();
  }

  private getCourses(){
    this.courseService.getCoursesList().subscribe(data => {
      this.courses = data;
    })
  }

  updateCourse(id: number){
    this.router.navigate(['updateCourse', id]);
  }

  courseDetails(id: number){
    this.router.navigate(['courseDetails', id]);
  }

  deleteCourse(id: number){
    this.courseService.deleteCourse(id).subscribe(data => {
      console.log(data);
      this.getCourses();
    })
  }

  searchSkill(skill: String){
    this.courseService.getCourseBySkill(skill).subscribe(data => {
      this.courses = data;
    }, error => console.log(error));
  }

  searchLocation(location: String){
    this.courseService.getCourseByLocation(location).subscribe(data => {
      this.courses = data;
    }, error => console.log(error));
  }

  subscribeCourse(courseId: number){
    if(this.username != null){
      this.participantService.subscribeCourse(this.username as String, courseId);
    }
  }

  isAdminLoggedIn(){
    return this.adminService.isAdminLoggedIn();
  }
  isParticipantLoggedIn(){
    return this.participantService.isParticipantLoggedIn();
  }
}