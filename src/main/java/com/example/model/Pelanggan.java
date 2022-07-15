package com.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Pelanggan")
public class Pelanggan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String namaBank;
	private String idBank;
	private String noRekening;
	private String nama;
	private String jenisKelamin;
	private double saldo;


}
