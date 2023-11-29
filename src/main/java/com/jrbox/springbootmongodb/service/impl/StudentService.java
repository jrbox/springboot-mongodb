package com.jrbox.springbootmongodb.service.impl;

import com.jrbox.springbootmongodb.document.Student;
import com.jrbox.springbootmongodb.domain.BusinessError;
import com.jrbox.springbootmongodb.exception.BusinessErrorException;
import com.jrbox.springbootmongodb.repository.IStudentRepository;
import com.jrbox.springbootmongodb.service.interfaces.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student insertStudent(Student student) {
        if (student.getAge() > 130) {
            throw new BusinessErrorException(BusinessError.E40002, student.getAge());
        }
        if (student.getAge() > 26) {
            throw new BusinessErrorException(BusinessError.E40001);
        }
        if (student.getAge() < 10) {
            throw new BusinessErrorException(BusinessError.E40003, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return studentRepository.insert(student);
    }
}
