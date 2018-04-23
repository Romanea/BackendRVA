package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Projekat;
import rva.repos.ProjekatRepository;

@RestController
public class ProjekatRestController {

	@Autowired
	private ProjekatRepository projekatRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("projekti")
	public Collection<Projekat> getGrupe() {
		return projekatRepo.findAll();
	}
	
	@GetMapping("projekti/{id}")
	public Projekat getProjekat(@PathVariable("id") Integer id) {
		return projekatRepo.getOne(id);
	}
	
	@DeleteMapping("projekti/{id}")
	public ResponseEntity<Projekat> deleteProjekat(@PathVariable("id") Integer id) {
		if(!projekatRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		projekatRepo.deleteById(id);
		if(id == -100 && !projekatRepo.existsById(id)) writeTestData();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("projekti")
	public ResponseEntity<Projekat> addProjekat(@RequestBody Projekat projekat) {
		if(projekatRepo.existsById(projekat.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		projekatRepo.save(projekat);
		return new ResponseEntity<Projekat>(projekat, HttpStatus.OK);
	}
	
	@PutMapping("projekti")
	public ResponseEntity<Projekat> updateProjekat(@RequestBody Projekat projekat) {
		if(!projekatRepo.existsById(projekat.getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		projekatRepo.save(projekat);
		return new ResponseEntity<Projekat>(projekat, HttpStatus.OK);
	}
	
	@Transactional
	private void writeTestData() {
		jdbcTemplate.execute("INSERT INTO \"projekat\" (\"id\", \"naziv\", \"oznaka\", \"opis\")\n" + 
				"VALUES (-100, 'Test Soap UI', 'SUI-T', 'TEST SOAP UI');");
	}
}
