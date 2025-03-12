package com.aurionpro.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;
import com.aurionpro.employee.entity.Instructor;
import com.aurionpro.employee.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	public InstructorRepository instructorRepository;
	
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

	

}
