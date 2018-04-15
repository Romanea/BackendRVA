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


import rva.jpa.Grupa;
import rva.repos.GrupaRepository;

@RestController
public class GrupaRestController {

	@Autowired
	private GrupaRepository grupaRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("grupe")
	public Collection<Grupa> getGrupe() {
		return grupaRepo.findAll();
	}
	
	@GetMapping("grupe/{id}")
	public Grupa getGrupa(@PathVariable("id") Integer id) {
		return grupaRepo.getOne(id);
	}
	
	@DeleteMapping("grupe/{id}")
	public ResponseEntity<Grupa> deleteGrupa(@PathVariable("id") Integer id) {
		if(!grupaRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		grupaRepo.deleteById(id);
		if(id == -100 && !grupaRepo.existsById(id)) writeTestData();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("grupe")
	public ResponseEntity<Grupa> addGrupa(@RequestBody Grupa grupa) {
		if(grupaRepo.existsById(grupa.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		grupaRepo.save(grupa);
		return new ResponseEntity<Grupa>(grupa, HttpStatus.OK);
	}
	
	@PutMapping("grupe")
	public ResponseEntity<Grupa> updateGrupa(@RequestBody Grupa grupa) {
		if(!grupaRepo.existsById(grupa.getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		grupaRepo.save(grupa);
		return new ResponseEntity<Grupa>(grupa, HttpStatus.OK);
	}
	
	@Transactional
	private void writeTestData() {
		jdbcTemplate.execute("INSERT INTO \"grupa\" (\"id\", \"oznaka\", \"smer\")\n" + 
				"VALUES (-100, 'ST2', 2);");
	}
}
