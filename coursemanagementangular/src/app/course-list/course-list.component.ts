import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  courses: Course[] = [];

  constructor(private courseService: CourseService, private router: Router) { }

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
}