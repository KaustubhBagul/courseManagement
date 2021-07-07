import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { Creator } from '../creator';
import { Skill } from '../skill';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  course: Course = new Course();
  id!: number;
  skill!: String;
  creator!: String;
  courseSkills!: Skill[];
  courseCreators!: Creator[];

  constructor(private courseService: CourseService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.courseService.getCourseById(this.id).subscribe(data => {
      this.course = data;
      this.courseSkills = this.course.skills;
      this.courseCreators = this.course.creator;
    }, error => console.groupCollapsed(error));
  }

  onSubmit(){
    this.courseService.updateCourse(this.id, this.course).subscribe(data => {
      this.goToCourseList();
      console.log(data);
    }, error => console.log(error));
  }

  goToCourseList(){
    this.router.navigate(['/courseList']);
  }

  deleteSkill(id: number, skillName: String){
    const index = this.courseSkills.indexOf(new Skill(id, skillName));
    this.course.skills.splice(index, 1);
  }

  deleteCreator(id: number, creatorName: String){
    const index = this.courseSkills.indexOf(new Creator(id, creatorName));
    this.course.creator.splice(index, 1);
  }

  addSkill(){
    this.course.skills.push(new Skill(0, this.skill));
    this.skill = "";
  }
  addCreator(){
    this.course.creator.push(new Creator(0, this.creator));
    this.creator = "";
  }
}
