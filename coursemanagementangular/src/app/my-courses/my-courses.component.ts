import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../models/course';
import { CourseService } from '../services/course.service';
import { ParticipantService } from '../services/participant.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {
  
  courses: Course[] = [];
  username!: string;
  
  constructor(private courseService: CourseService, private router: Router,
    private participantService: ParticipantService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.participantService.getMyCourses(this.username).subscribe(data => {
      this.courses = data;
    }, error => console.log(error));
  }

  courseDetails(id: number){
    this.router.navigate(['courseDetails', id]);
  }

  unsubscribeCourse(courseId: number){
    this.participantService.unsubscribeCourse(this.username, courseId).subscribe(data => {
      console.log(data);
      this.refreshPage();
    })
  }
  
  refreshPage(){
    window.location.reload();
  }

  isParticipantLoggedIn(){
    return this.participantService.isParticipantLoggedIn();
  }
}
