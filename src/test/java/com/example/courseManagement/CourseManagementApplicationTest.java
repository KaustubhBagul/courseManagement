package com.example.courseManagement;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentMatchers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Creator;
import com.accolite.courseManagement.entities.Skill;
import com.accolite.courseManagement.exception.NoContentException;
import com.accolite.courseManagement.models.Course;
import com.accolite.courseManagement.models.Mail;
import com.accolite.courseManagement.repositories.CourseEntityRepository;
import com.accolite.courseManagement.repositories.CreatorRepository;
import com.accolite.courseManagement.repositories.ParticipantRepository;
import com.accolite.courseManagement.repositories.SkillRepository;
import com.accolite.courseManagement.service.CourseService;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class CourseManagementApplicationTest {

    @Mock
	private CourseEntityRepository courseEntityRepository;
	
	@Mock
	private ParticipantRepository participantRepository;
	
	@Mock
	private CreatorRepository creatorRepository;
	
	@Mock
	private SkillRepository skillRepository;
	
	@Mock
	private Mail myMail;
    
	@Autowired
	CourseService courseService;
	
    @Test
    @DisplayName("Should Retrieve Course by Id")
    public void shouldFindCourseById() {
    	
		List<Creator> creatorList = new ArrayList<>();
		List<Skill> skillList = new ArrayList<>();
		
		CourseEntity courseEntity = new CourseEntity(123L, "first course", "no prerequisite", "monday", 
				"feedback", "Bangalore", skillList, creatorList);
		Course expectedCourseResponse = new Course(123L, "first course", "no prerequisite", "monday", 
				"feedback", "Bangalore", skillList, creatorList);
		  
		Mockito.when(courseEntityRepository.findById(123L)).thenReturn(Optional.of(courseEntity));
		  
		Course actualCourseResponse = null;
		
		try {
			actualCourseResponse = courseService.getCourseById(123L);
		} catch (NoContentException e) {
			e.printStackTrace();
		}
		  
		Assertions.assertThat(actualCourseResponse.getId()).isEqualTo(expectedCourseResponse.getId());
		Assertions.assertThat(actualCourseResponse.getDescription()).isEqualTo(expectedCourseResponse.getDescription());
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
			.thenReturn(courseService.mapObjectToEntity(courseRequest));
		
        courseService.createCourse(courseRequest);

        Mockito.verify(courseEntityRepository, Mockito.times(1)).save(ArgumentMatchers.any(CourseEntity.class));
    }
}
