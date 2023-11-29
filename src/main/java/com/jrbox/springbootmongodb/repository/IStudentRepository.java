package com.jrbox.springbootmongodb.repository;

import com.jrbox.springbootmongodb.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends MongoRepository<Student, String> {

}
