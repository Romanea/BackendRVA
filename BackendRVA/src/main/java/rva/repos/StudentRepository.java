package rva.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
