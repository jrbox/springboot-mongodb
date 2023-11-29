package com.jrbox.springbootmongodb.service.interfaces;

import com.jrbox.springbootmongodb.document.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAllStudents();
    Student findStudentById(String id);
    Student insertStudent(Student student);
}
