package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Pelanggan;
import com.example.repository.PelangganRepository;

@Service
public class PelangganService {

	@Autowired
	PelangganRepository pelangganRepo;
	
	public Pelanggan getByNorek(String noRek) {
		return pelangganRepo.getByNoRekening(noRek);
		
	} 
	
	public Pelanggan create(Pelanggan pelanggan) {
		return pelangganRepo.save(pelanggan);
		
	}
}
