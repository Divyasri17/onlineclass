package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {

	
	Teacher findByEmailAndPassword(
            String email,
            String password);

    Teacher findByEmail(String email);

}
