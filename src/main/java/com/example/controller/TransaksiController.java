package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Transaksi;
import com.example.service.TransaksiService;

@Controller
@RequestMapping("/api/transaksi")
public class TransaksiController {
	@Autowired
	TransaksiService transaksiService;

	@PostMapping("/upload")
	public ResponseEntity uploadFile(@RequestParam(required = true) List<MultipartFile> file) {
		return new ResponseEntity(transaksiService.importToDb(file), HttpStatus.CREATED);
	}

	@GetMapping("/ambil")
	public ResponseEntity<Transaksi> getAllByNamaFile(@RequestParam(name = "namaFile") String namaFile) {
		return new ResponseEntity(transaksiService.getAllTransaksiByNameFile(namaFile), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Transaksi> getAll() {
		return new ResponseEntity(transaksiService.getAll(), HttpStatus.OK);

	}

	@GetMapping("/grup")
	public ResponseEntity<Transaksi> getAllByGrupNamaFile() {
		return new ResponseEntity(transaksiService.groupByNamaFile(), HttpStatus.OK);
	}

}