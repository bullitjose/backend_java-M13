package com.pepe.demo.services;

import java.util.List;

import com.pepe.demo.exception.BadResourceException;
import com.pepe.demo.exception.ResourceAlreadyExistsException;
import com.pepe.demo.exception.ResourceNotFoundException;
import com.pepe.demo.model.Empleat;
import com.pepe.demo.model.Empleat.Feina;

public interface EmpleatService {
    List<Empleat> getEmpleats() throws ResourceNotFoundException;

    Empleat getEmpleatById(Long id) throws ResourceNotFoundException;

    Empleat insert(Empleat empleat) throws ResourceAlreadyExistsException, BadResourceException;

    void updateEmpleat(Long id, Empleat empleat) throws ResourceNotFoundException, BadResourceException;

    void deleteEmpleat(Long empleatId)throws ResourceNotFoundException;
    List<Empleat> findByNom(String nom)throws ResourceNotFoundException;
    List<Empleat> findByFeina(Feina feina)throws ResourceNotFoundException;

	

    




}
