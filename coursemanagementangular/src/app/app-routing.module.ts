import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { CourseListComponent } from './course-list/course-list.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { DeleteCourseComponent } from './delete-course/delete-course.component';
import { ManageParticipantsComponent } from './manage-participants/manage-participants.component';
import { UpdateCourseComponent } from './update-course/update-course.component';

const routes: Routes = [
  { path: '', component: CourseListComponent },
  { path: 'courseList', component: CourseListComponent },
  { path: 'createCourse', component: CreateCourseComponent},
  { path: 'updateCourse/:id', component: UpdateCourseComponent},
  { path: 'courseDetails/:id', component: CourseDetailsComponent},
  { path: 'participants', component: ManageParticipantsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
