package com.pepe.demo.controllers;

import com.pepe.demo.exception.BadResourceException;
import com.pepe.demo.exception.ResourceAlreadyExistsException;
import com.pepe.demo.exception.ResourceNotFoundException;
import com.pepe.demo.model.Empleat;
import com.pepe.demo.model.Empleat.Feina;
import com.pepe.demo.services.EmpleatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author monga
 *
 */
/**
 * @author monga
 *
 */
@RestController
@RequestMapping("api/v1/empleat")
public class EmpleatController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	EmpleatService empleatService;

	public EmpleatController(EmpleatService empleatService) {
		this.empleatService = empleatService;
	}

	/*
	 * The function receives a GET request, processes it and gives back a list of
	 * Empleat as a response.
	 */
	/*
	 * Al programa Postman, seleccionar GET i http://localhost:8080/api/v1/empleat/
	 */
	@GetMapping
	public ResponseEntity<List<Empleat>> getAllEmpleats() {
		List<Empleat> empleats;
		try {
			empleats = empleatService.getEmpleats();
		} catch (ResourceNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(empleats, HttpStatus.OK);

	}

	/*
	 * The function receives a GET request with id in the url path, processes it and
	 * returns a Empleat with the specified Id
	 */
	@GetMapping("/{empleatId}")
	public ResponseEntity<Empleat> getEmpleat(@PathVariable Long empleatId) {
		try {
			return new ResponseEntity<>(empleatService.getEmpleatById(empleatId), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/*
	 * 
	 * The function receives a POST request, processes it, creates a new Empleat and
	 * saves it to the database and returns a resource link to the created empleat.
	 */
	/*
	 * Per Postman: Seleccionar POST, http://localhost:8080/api/v1/empleat/ , i com
	 * a cos o body seleccionant raw en format JSON les dades del empleat: { "nom":
	 * "pepe", "feina": "MANAGER" }
	 */

	@PostMapping
	public ResponseEntity<Empleat> saveEmpleat(@RequestBody Empleat empleat) {
		try {
			Empleat empleat1 = empleatService.insert(empleat);

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("empleat", "/api/v1/empleat/" + empleat1.getId().toString());
			return new ResponseEntity<>(empleat1, httpHeaders, HttpStatus.CREATED);
		} catch (ResourceAlreadyExistsException ex) {
			// log exception first, then return Conflict (409)
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			// log exception first, then return Bad Request (400)
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	/*
	 * The function receives a PUT request, updates the Empleat with the specified
	 * Id and returns the updated Empleat
	 */
	@PutMapping({ "/{empleatId}" })
	public ResponseEntity<Empleat> updateEmpleat(@PathVariable("empleatId") Long empleatId,
			@RequestBody Empleat empleat) {
		try {
			empleatService.updateEmpleat(empleatId, empleat);
			return new ResponseEntity<>(empleatService.getEmpleatById(empleatId), HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			// log exception first, then return Not Found (404)
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			// log exception first, then return Bad Request (400)
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * The function receives a DELETE request, deletes the Empleat with the
	 * specified Id.
	 */
	/*
	 * Per Postman: Seleccionar DELETE, http://localhost:8080/api/v1/empleat/1 , en
	 * aquest cas {empleatId} és 1, elimina el empleat amb aquest id
	 */
	@DeleteMapping({ "/{empleatId}" })
	public ResponseEntity<Empleat> deleteEmpleat(@PathVariable("empleatId") Long empleatId) {
		try {
			empleatService.deleteEmpleat(empleatId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * The function receives a GET request with name in the url path, processes it
	 * and returns a Empleat with the specified name
	 */
	/*
	 * Per Postman: Seleccionar GET,
	 * http://localhost:8080/api/v1/empleat/findByNom/jose , en aquest cas
	 * {empleatId} és jose,torna totes les ocurrencies amb nom igual a jose.
	 */
	@GetMapping({ "/findByNom/{nom}" })

	public ResponseEntity<List<Empleat>> getEmpleatByNom(@PathVariable String nom) {
		try {
			return new ResponseEntity<List<Empleat>>(empleatService.findByNom(nom), HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * The function receives a GET request with name in the url path, processes it
	 * and returns a Empleat with the specified name
	 */
	/*
	 * Example per programa Postman:Seleccionar Get i
	 * http://localhost:8080/api/v1/empleat/findByFeina/MANAGER en aquest cas torna
	 * totes les ocurrencies amb Feina igual a MANAGER
	 */
	@GetMapping({ "/findByFeina/{feina}" })

	public ResponseEntity<List<Empleat>> getEmpleatByFeina(@PathVariable Feina feina) {
		try {
			return new ResponseEntity<List<Empleat>>(empleatService.findByFeina(feina), HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

}
