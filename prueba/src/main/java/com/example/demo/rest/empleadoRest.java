package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.empleadoDao;
import com.example.demo.entitys.empleado;



@RestController
@RequestMapping("empleado")
public class empleadoRest {
	
	@Autowired
	private empleadoDao empleadoDao;

	
	 @GetMapping
	 public ResponseEntity<List<empleado>> getEmpleado() {
		 List<empleado> emp = empleadoDao.findAll();
		 return ResponseEntity.ok(emp);

	 }
	 
	 @RequestMapping(value = "{empleadoId}") //  /empleado/{empleadoId}   ->  /empleado/1
	 public ResponseEntity<empleado> getEmpleadoById(@PathVariable("empleadoId") Integer empleadoId) {
		 java.util.Optional<empleado> opcionalEmpleado = empleadoDao.findById(empleadoId);
		 if (opcionalEmpleado.isPresent()) {
			 return ResponseEntity.ok(opcionalEmpleado.get());
		 }
		 else {
			 return ResponseEntity.noContent().build();
			 
		 }
		 

	 }
	 
	 @PostMapping
	 public ResponseEntity<empleado> createEmpleado(@RequestBody empleado emp){
		 empleado newEmpleado = empleadoDao.save(emp);
		 return ResponseEntity.ok(newEmpleado);
	 }
	 
	 
	 @DeleteMapping(value = "{empleadoId}") 
	 public ResponseEntity<Void> deleteEmpleado(@PathVariable("empleadoId") Integer empleadoId){
		 empleadoDao.deleteById(empleadoId);
		 return ResponseEntity.ok(null);
	 }
	 
	 @PutMapping
	 public ResponseEntity<empleado> updateEmpleado(@RequestBody empleado emp){
		 java.util.Optional<empleado> opcionalEmpleado = empleadoDao.findById(emp.getId());
		 if (opcionalEmpleado.isPresent()) {
			 empleado updateEmpleado = opcionalEmpleado.get();
					 updateEmpleado.setCargo(emp.getCargo());
			         updateEmpleado.setDni(emp.getDni());
			         updateEmpleado.setFecha(emp.getFecha());
			         updateEmpleado.setNombre(emp.getNombre());
			         empleadoDao.save(updateEmpleado);
			 return ResponseEntity.ok(updateEmpleado);
		 }
		 else {
			 return ResponseEntity.notFound().build();
			 
		 }
	 }
}
