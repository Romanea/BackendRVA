package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Grupa;
import rva.jpa.Student;
import rva.repos.GrupaRepository;
import rva.repos.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private GrupaRepository grupaRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("studenti")
	public Collection<Student> getGrupe() {
		return studentRepo.findAll();
	}
	
	@GetMapping("studenti/{id}")
	public Student getStudent(@PathVariable("id") Integer id) {
		return studentRepo.getOne(id);
	}
	
	@CrossOrigin
	@GetMapping("studentiGrupe/{id}")
	public Collection<Student> getStudentiGrupe(@PathVariable("id") int id){
		Grupa g = grupaRepo.getOne(id);
		return studentRepo.findByGrupa(g);
	}
	
	@CrossOrigin
	@DeleteMapping("studenti/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if(!studentRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		studentRepo.deleteById(id);
		if(id == -100 && !studentRepo.existsById(id)) writeTestData();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("studenti")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		if(studentRepo.existsById(student.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		studentRepo.save(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	@CrossOrigin
	@PutMapping("studenti")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if(!studentRepo.existsById(student.getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		studentRepo.save(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@Transactional
	private void writeTestData() {
		jdbcTemplate.execute("INSERT INTO \"student\" (\"id\", \"ime\", \"prezime\", \"broj_indeksa\", \"grupa\", \"projekat\")\n" + 
				"VALUES (-100, 'Tester', 'Tester', 'TE1/2016', 3, 1);");
	}
}
