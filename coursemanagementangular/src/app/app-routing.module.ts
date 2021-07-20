import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { CourseListComponent } from './course-list/course-list.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { ManageParticipantsComponent } from './manage-participants/manage-participants.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './services/auth-guard.service';
import { RegisterComponent } from './register/register.component';
import { ParticipantRegisterComponent } from './participant-register/participant-register.component';
import { ParticipantAuthGuardService } from './services/participant-auth-guard.service';
import { MyCoursesComponent } from './my-courses/my-courses.component';

const routes: Routes = [
  { path: '', component: CourseListComponent },
  { path: 'participant/register', component: ParticipantRegisterComponent},
  { path: 'login', component: LoginComponent },
  { path: 'admin/register', component: RegisterComponent, canActivate:[AuthGuardService] },
  { path: 'courseList', component: CourseListComponent },
  { path: 'createCourse', component: CreateCourseComponent, canActivate:[AuthGuardService] },
  { path: 'updateCourse/:id', component: UpdateCourseComponent, canActivate:[AuthGuardService] },
  { path: 'courseDetails/:id', component: CourseDetailsComponent},
  { path: 'participants/manage', component: ManageParticipantsComponent, canActivate:[AuthGuardService] },
  { path: 'participant/courses/:username', component: MyCoursesComponent, canActivate:[ParticipantAuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
