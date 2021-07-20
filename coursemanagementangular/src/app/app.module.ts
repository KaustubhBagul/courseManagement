import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseListComponent } from './course-list/course-list.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { ManageParticipantsComponent } from './manage-participants/manage-participants.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { RegisterComponent } from './register/register.component';
import { ParticipantRegisterComponent } from './participant-register/participant-register.component';
import { MyCoursesComponent } from './my-courses/my-courses.component';

@NgModule({
  declarations: [
    AppComponent,
    CourseListComponent,
    CreateCourseComponent,
    UpdateCourseComponent,
    CourseDetailsComponent,
    ManageParticipantsComponent,
    LoginComponent,
    HeaderComponent,
    RegisterComponent,
    ParticipantRegisterComponent,
    MyCoursesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
