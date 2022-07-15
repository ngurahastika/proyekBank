package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dto.TransaksiRes;
import com.example.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {

	List<Transaksi> getByNamaFile(String namaFile);

	@Query(value = "select * from transaksi group by  nama_file",nativeQuery = true)
	List<Transaksi> grupByNamaFile();
}
