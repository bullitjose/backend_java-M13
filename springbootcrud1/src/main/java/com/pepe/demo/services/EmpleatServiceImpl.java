package com.pepe.demo.services;


import com.pepe.demo.model.Empleat;
import com.pepe.demo.model.Empleat.Feina;
import com.pepe.demo.repositories.EmpleatRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleatServiceImpl implements EmpleatService {
    EmpleatRepository empleatRepository;

    public EmpleatServiceImpl(EmpleatRepository empleatRepository) {
        this.empleatRepository = empleatRepository;
    }

    @Override
    public List<Empleat> getEmpleats(){
        List<Empleat> empleats = new ArrayList<>();
        empleatRepository.findAll().forEach(empleats::add);
        return empleats;
    }

    @Override
    public Empleat getEmpleatById(Long id){
        return empleatRepository.findById(id).get();
    }

    @Override
    public Empleat insert(Empleat empleat) {
        return empleatRepository.save(empleat);
    }

    @Override
    public void updateEmpleat(Long id, Empleat empleat){
        Empleat todoFromDb = empleatRepository.findById(id).get();
        System.out.println(todoFromDb.toString());
        todoFromDb.setNom(empleat.getNom());
        todoFromDb.setFeina(empleat.getFeina());
        todoFromDb.setSou(empleat.getSou());
        empleatRepository.save(todoFromDb);
    }

    @Override
    public void deleteEmpleat(Long todoId) {
        empleatRepository.deleteById(todoId);
    }

    @Override
	public List<Empleat> findByNom(String nom){
		List<Empleat> empleats = new ArrayList<>();
        empleatRepository.findByNom(nom).forEach(empleats::add);
		return empleats;
	}
	   @Override
	public List<Empleat> findByFeina(Feina feina){
		List<Empleat> empleats = new ArrayList<>();
        empleatRepository.findByFeina(feina).forEach(empleats::add);
		return empleats;
		
	}
    
}
