package com.jrbox.springbootmongodb.mapper;

import com.jrbox.springbootmongodb.document.Student;
import com.jrbox.springbootmongodb.dto.StudentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    Student toStudent(StudentDto studentDto);

    StudentDto fromStudent(Student student);
}
