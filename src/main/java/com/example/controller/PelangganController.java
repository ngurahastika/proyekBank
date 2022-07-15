package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Pelanggan;
import com.example.service.PelangganService;

@Controller
@RequestMapping("api/nasabah")
public class PelangganController {
	
	@Autowired
	PelangganService pelangganService;
	
	@PostMapping
	public ResponseEntity<Pelanggan> create(@RequestBody Pelanggan pelanggan){
		return new ResponseEntity<Pelanggan>(pelangganService.create(pelanggan),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Pelanggan> getByNoRek(@RequestParam(name = "noRekening") String noRekening ){
		return new ResponseEntity<Pelanggan>(pelangganService.getByNorek(noRekening),HttpStatus.OK);
	}
}
