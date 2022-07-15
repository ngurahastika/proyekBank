package com.example.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaksi")
public class Transaksi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaksi;
	private String norekPengirim;
	private String norekPenerima;
	private String kodeBankPenerima;
	private String namaBankPenerima;
	private double jumlahTransfer;
	private LocalDateTime createadd;
	private String namaFile;
	
}
