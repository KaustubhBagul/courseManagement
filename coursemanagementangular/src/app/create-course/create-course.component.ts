import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { Creator } from '../creator';
import { Skill } from '../skill';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.css']
})
export class CreateCourseComponent implements OnInit {

  course: Course = new Course();
  skill!: String;
  creator!: String;

  constructor(private courseService: CourseService, private router: Router) { }

  ngOnInit(): void {
  }

  saveCourse(){
    this.courseService.createCourse(this.course).subscribe(data => {
      console.log(data);
    })
  }

  onSubmit(): void{
    this.saveCourse();
    this.router.navigate(['/courseList']);
  }

  addSkill(){
    this.course.skills.push(new Skill(1, this.skill));
    this.skill = "";
  }
  addCreator(){
    this.course.creator.push(new Creator(1, this.creator));
    this.creator = "";
  }

}
