package com.pepe.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pepe.demo.model.Empleat;
import com.pepe.demo.model.Empleat.Feina;

@Repository
public interface EmpleatRepository extends CrudRepository<Empleat, Long> {

	//Iterable<Todo> findByNom(String nom);
	List<Empleat> findByNom(String nom);

	//Iterable<Todo> findByFeina(Feina feina);

	List<Empleat> findByFeina(Feina feina);
 }
