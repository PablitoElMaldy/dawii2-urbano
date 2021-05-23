package com.empresa.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/rest/alumnos")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Alumno>> listaAlumno(){
		 log.info("listaaadoo");
		List<Alumno> lst = service.listaAlumno();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		log.info(obj);
		Alumno objsalida = service.insertaActualizaAlumno(obj);
		if(objsalida!=null) {
			return new ResponseEntity<>(objsalida, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
