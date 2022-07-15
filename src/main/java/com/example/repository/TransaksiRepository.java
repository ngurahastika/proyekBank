package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {

	List<Transaksi> getByNamaFile(String namaFile);
}
