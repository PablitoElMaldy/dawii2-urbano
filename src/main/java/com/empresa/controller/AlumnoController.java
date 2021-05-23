package com.empresa.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Alumno>> listaAlumno(){
		 log.info("listaaadoo");
		List<Alumno> lst = service.listaAlumno();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		log.info(obj);
		Alumno objsalida = service.insertaActualizaAlumno(obj);
		if(objsalida!=null) {
			return new ResponseEntity<>(objsalida, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		log.info(">>>> actualiza " + obj.getIdAlumno());
		Optional<Alumno> optAlumno = service.obtienePorId(obj.getIdAlumno());
		if (optAlumno.isPresent()) {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if (objSalida != null) {
				return ResponseEntity.ok(objSalida);
			}else {
				return ResponseEntity.badRequest().build();
			}	
		}else {
			log.info(">>>> actualiza no existe el id : " + obj.getIdAlumno());
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){

		log.info(">>>> elimina " + idAlumno);

		Optional<Alumno> optAlumno = service.obtienePorId(idAlumno);

		if (optAlumno.isPresent()) {

			service.eliminaAlumno(idAlumno);

			return ResponseEntity.ok(optAlumno.get());

		}else {

			log.info(">>>> elimina no existe el id : " + idAlumno);

			return ResponseEntity.badRequest().build();

		}

	}
	
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<List<Alumno>> listaAlumno(@PathVariable("dni") String dni){
		 log.info("listaaadoo");
		List<Alumno> obj = service.listaPorDni(dni);
		if(obj.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {		
			return new ResponseEntity<>(obj, HttpStatus.OK);
		}
	}

}
