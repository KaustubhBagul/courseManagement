package com.example.courseManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.accolite.courseManagement.controller.*;
import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.models.*;
import com.accolite.courseManagement.repositories.*;
import com.example.models.CourseModels;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CourseController.class})
class BackendApplicationTests {

	CourseModels testModelMethod = new CourseModels();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CourseEntityRepository courseEntityRepository;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
    List<Course> courseList = new ArrayList<>();
    Course c1, c2;

    @Before
    public void setup(){
        short vacant = 7;

        c1 = new Course();
        c1.setId(1L);
        c1.setPrerequisite("Java");
        c1.setLastupdated("Tuesday");
        c1.setFeedback("Great for basics");
        c1.setDescription("should try for ai");
        c1.setLocation("Nagpur");

        c2 = new Course();
        c2.setId(2L);
        c2.setPrerequisite("C++");
        c2.setLastupdated("Monday");
        c2.setFeedback("Great for basics of ml");
        c2.setDescription("should try for ml");
        c2.setLocation("Nashik");
    }

}
