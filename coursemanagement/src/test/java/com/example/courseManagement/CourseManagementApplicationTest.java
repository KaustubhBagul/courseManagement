package com.example.courseManagement;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.*;

import com.accolite.courseManagement.entities.*;
import com.accolite.courseManagement.models.*;
import com.accolite.courseManagement.controller.*;
import com.accolite.courseManagement.repositories.*;
import com.accolite.courseManagement.service.CourseService;
import com.accolite.courseManagement.exception.NoContentException;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CourseController.class)
@Import(CourseService.class)
public class CourseManagementApplicationTest {

    @Mock
	private CourseEntityRepository courseEntityRepository;
	
	@Mock
	private ParticipantRepository participantRepository;
	
	@Mock
	private CreatorRepository creatorRepository;
	
	@Mock
	private SkillRepository skillRepository;
    
	@Autowired
    private WebTestClient webClient;
	
    @Test
    @DisplayName("Should Retrieve Course by Id")
    public void shouldFindCourseById() {
		
		CourseEntity courseEntity = new CourseEntity(123L, "first course", "no prerequisite", "monday", 
				"feedback", "Bangalore", null, null);
		Course expectedCourseResponse = new Course(123L, "first course", "no prerequisite", "monday", 
				"feedback", "Bangalore", null, null);
		  
		Mockito.when(courseEntityRepository.findById(123L)).thenReturn(Optional.of(courseEntity));
		
		webClient.get().uri("/course/{id}", "123L")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(CourseEntity.class);
     
	    Mockito.verify(courseEntityRepository, times(1)).findById(123L);
    }
    
    @Test
    @DisplayName("Should Save Course")
    public void shouldSavePosts() {

		List<Creator> creatorList = new ArrayList<>();
		List<Skill> skillList = new ArrayList<>();
		
		creatorList.add(new Creator(1, "kaustubh"));
		skillList.add(new Skill(1, "Java"));
		
		CourseEntity courseEntity = new CourseEntity(123L, "first course", "no prerequisite", 
				"monday", "feedback", "Bangalore", skillList, creatorList);

		Course courseRequest = new Course(123L, "first course", "no prerequisite", "monday", 
				"feedback", "Bangalore", skillList, creatorList);
		
		Mockito.when(courseEntityRepository.save(courseEntity))
			.thenReturn(courseEntity);
		
		webClient.post()
        .uri("/course")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(courseEntity))
        .exchange()
        .expectStatus().isCreated();

	    Mockito.verify(courseEntityRepository, times(1)).save(courseEntity);

    }
}
