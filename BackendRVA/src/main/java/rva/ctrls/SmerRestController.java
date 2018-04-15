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

import rva.jpa.Smer;
import rva.repos.SmerRepository;

@RestController
public class SmerRestController {
	
	@Autowired
	private SmerRepository smerRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("smerovi")
	public Collection<Smer> getSmerovi() {
		return smerRepo.findAll();
	}
	
	@GetMapping("smerovi/{id}")
	public Smer getSmer(@PathVariable("id") Integer id) {
		return smerRepo.getOne(id);
	}
	
	@Transactional
	@DeleteMapping("smerovi/{id}")
	public ResponseEntity<Smer> deleteSmer(@PathVariable("id") Integer id) {
		if(!smerRepo.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		jdbcTemplate.execute("delete from grupa where smer = "+id);
		smerRepo.deleteById(id);
		if(id == -100 && !smerRepo.existsById(id)) writeTestData();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("smerovi")
	public ResponseEntity<Smer> addSmer(@RequestBody Smer smer) {
		if(smerRepo.existsById(smer.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		smerRepo.save(smer);
		return new ResponseEntity<Smer>(smer, HttpStatus.OK);
	}
	
	@PutMapping("smerovi")
	public ResponseEntity<Smer> updateSmer(@RequestBody Smer smer) {
		if(!smerRepo.existsById(smer.getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		smerRepo.save(smer);
		return new ResponseEntity<Smer>(smer, HttpStatus.OK);
	}
	
	@Transactional
	private void writeTestData() {
		jdbcTemplate.execute("INSERT INTO \"smer\" (\"id\", \"naziv\", \"oznaka\")\n" + 
				"VALUES (-100, 'SOAP TEST ', 'ST');");
	}
}
