package com.aurionpro.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.entity.Course;
import com.aurionpro.employee.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	public CourseRepository courseRepository;
	
	@Override
	public CourseResponseDto addCourse(CourseRequestDto courseRequest) 
	{
		Course dbCourse = courseRepository.save(requestDtoToCourseMapper(courseRequest));
		
		return instructorToResponseDtoMapper(dbCourse);
	}
	
	public Course requestDtoToCourseMapper(CourseRequestDto requestDto)
	{
		Course course = new Course();
		course.setCourseName(requestDto.getCourseName());
		course.setDuration(requestDto.getDuration());
		course.setFees(requestDto.getFees());
		
		return course;
	}
	
	public CourseResponseDto instructorToResponseDtoMapper(Course course)
	{
		CourseResponseDto responseDto = new CourseResponseDto();
		
		responseDto.setCourseId(course.getCourseId());
		responseDto.setCourseName(course.getCourseName());
		responseDto.setDuration(course.getDuration());
		responseDto.setFees(course.getFees());
		
		return responseDto;
	}

}
