package com.aurionpro.employee.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;
import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.entity.Course;
import com.aurionpro.employee.entity.Instructor;
import com.aurionpro.employee.exception.InstructorApiException;
import com.aurionpro.employee.repository.CourseRepository;
import com.aurionpro.employee.repository.InstructorRepository;


@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	public InstructorRepository instructorRepository;
	
	@Autowired
	public CourseRepository courseRepository;
	
	private ModelMapper modelMapper;
	
	public InstructorServiceImpl() {
		super();
		this.modelMapper = new ModelMapper();
	}

	@Override
	public InstructorResponseDto addInstructor(InstructorRequestDto requestDto)
	{
		Instructor dbInstructor = instructorRepository.save(requestDtoToInstructorMapper(requestDto));
		
		return instructorToResponseDtoMapper(dbInstructor);
	}
	
	public Instructor requestDtoToInstructorMapper(InstructorRequestDto requestDto)
	{
		Instructor instructor = new Instructor();
		instructor.setName(requestDto.getName());
		instructor.setExperience(requestDto.getExperience());
		instructor.setQualification(requestDto.getQualification());
		return instructor;
	}
	
	public InstructorResponseDto instructorToResponseDtoMapper(Instructor instructor)
	{
		InstructorResponseDto responseDto = new InstructorResponseDto();
		
		responseDto.setInstructorId(instructor.getInstructorId());
		responseDto.setName(instructor.getName());
		responseDto.setExperience(instructor.getExperience());
		responseDto.setQualification(instructor.getQualification());
		
		return responseDto;
	}
	
	
	// getting all courses
	
	@Override
	public PageResponse<InstructorResponseDto> getAllInstructors(int pageNumber, int pageSize) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Instructor> instructors = null;
		
		instructors = instructorRepository.findAll(pageable);
		
		List<Instructor> coursesList = instructors.getContent();
		List<InstructorResponseDto> instructorDto = new ArrayList<>();
		for(Instructor course : coursesList) {
			instructorDto.add(instructorToResponseDtoMapper(course));
		}
		
		PageResponse<InstructorResponseDto> pageResponse = new PageResponse<>();
		pageResponse.setContent(instructorDto);
		pageResponse.setLast(instructors.isLast());
		pageResponse.setPageSize(instructors.getSize());
		pageResponse.setTotalPages(instructors.getTotalPages());
		pageResponse.setTotalElements(instructors.getTotalElements());
		return pageResponse;
	}

	@Override
	public InstructorResponseDto getInstructorById(int id) 
	{
		Optional<Instructor> instructorDb = instructorRepository.findById(id);
		
		if(instructorDb.isEmpty()) {
			throw new InstructorApiException("Instructor by this id does not exist");
		}
		
		InstructorResponseDto responseDto = instructorToResponseDtoMapper(instructorDb.get());
		return responseDto;
	}

	@Override
	public InstructorResponseDto assignCourses(int instructorId, List<Integer> courseIds) 
	{
		
		Optional<Instructor> dbInstructor = instructorRepository.findById(instructorId);
		
		if(dbInstructor.isEmpty())
			throw new RuntimeException("Instructor with instructor id -"+instructorId + " does not exist");
		
		Instructor instructor = dbInstructor.get();
		
		List<Course> courses = new ArrayList<>();
		
		for(Integer courseId : courseIds)
		{
			Course course = courseRepository.findById(courseId)
					.orElseThrow(()-> new RuntimeException("Course with id - " + courseId + " does not exist"));
			
			course.setInstructor(instructor);
			Course dbCourse = courseRepository.save(course);
			courses.add(dbCourse);
					
		}
		
		instructor.setCourses(courses);
		
		return modelMapper.map(instructorRepository.save(instructor),InstructorResponseDto.class);
		
	}

	@Override
	public List<CourseResponseDto> getCourseByInstructor(int instructorId) 
	{
		Optional<Instructor> instructorDb = instructorRepository.findById(instructorId);
		
		if(instructorDb.isEmpty()) {
			throw new InstructorApiException("Instructor by this id does not exist");
		}
		
		Instructor instructor = instructorDb.get();
		
		List<CourseResponseDto> courseDto = new ArrayList<>();
		
		for(Course course : instructor.getCourses())
		{	
			courseDto.add(modelMapper.map(course, CourseResponseDto.class));
		}
		
		
		return courseDto;
	}
	 
	

}
