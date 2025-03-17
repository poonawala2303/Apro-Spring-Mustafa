package com.aurionpro.employee.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.entity.Course;
import com.aurionpro.employee.exception.CourseApiException;
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

	@Override
	public PageResponse<CourseResponseDto> getAllCourses(int pageSize, int pageNumber) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Course> courses = null;
		
		courses = courseRepository.findAll(pageable);
		
		List<Course> coursesList = courses.getContent();
		List<CourseResponseDto> courseDto = new ArrayList<>();
		for(Course course : coursesList) {
			courseDto.add(instructorToResponseDtoMapper(course));
		}
		
		PageResponse<CourseResponseDto> pageResponse = new PageResponse<>();
		pageResponse.setContent(courseDto);
		pageResponse.setLast(courses.isLast());
		pageResponse.setPageSize(courses.getSize());
		pageResponse.setTotalPages(courses.getTotalPages());
		pageResponse.setTotalElements(courses.getTotalElements());
		return pageResponse;
	}

	@Override
	public CourseResponseDto getCourseById(int id) {
		Optional<Course> courseDb = courseRepository.findById(id);
		
		if(courseDb.isEmpty()) {
			throw new CourseApiException("Course by this id does not exist");
		}
		
		CourseResponseDto responseDto = instructorToResponseDtoMapper(courseDb.get());
		return responseDto;	
	}

	@Override
	public PageResponse<CourseResponseDto> getCourseByFeesOrderAsc(int pageSize, int pageNumber) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Course> courses = null;
		
		courses = courseRepository.findAll(pageable);
		
//		List<Course> coursesList = courses.getContent();
		List<CourseResponseDto> courseDto = new ArrayList<>();
		
		for(Course course: courses)
		{
			courseDto.add(instructorToResponseDtoMapper(course));
		}
		
		courseDto.sort(Comparator.comparingDouble((a)->a.getFees()));
		PageResponse<CourseResponseDto> pageResponse = new PageResponse<>();
		pageResponse.setContent(courseDto);
		pageResponse.setLast(courses.isLast());
		pageResponse.setPageSize(courses.getSize());
		pageResponse.setTotalPages(courses.getTotalPages());
		pageResponse.setTotalElements(courses.getTotalElements());
		return pageResponse;
		
	}

}
