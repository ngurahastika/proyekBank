package com.example.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.model.Pelanggan;
import com.example.model.Transaksi;
import com.example.repository.PelangganRepository;
import com.example.repository.TransaksiRepository;

@Service
public class TransaksiService {

	@Autowired
	TransaksiRepository transaksiRepo;
	@Autowired
	PelangganRepository pelangganRepo;

	public List<Transaksi> getAllTransaksiByNameFile(String namaFile) {
		return transaksiRepo.getByNamaFile(namaFile);
	}

	public List<Transaksi> importToDb(List<MultipartFile> multipartfiles) {

		if (!multipartfiles.isEmpty()) {
			List<Transaksi> transactions = new ArrayList<>();

			multipartfiles.forEach(multipartfile -> {
				try {
					XSSFWorkbook workBook = new XSSFWorkbook(multipartfile.getInputStream());
					Transaksi transaction;
					String namaFile = multipartfile.getOriginalFilename();
					String rekPengirim = null;
					Pelanggan pelanggan = null;
					XSSFSheet sheet = workBook.getSheetAt(0);
					// looping through each row
					for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0); rowIndex++) {
						// current row
						XSSFRow row = sheet.getRow(rowIndex);
						// skip the first row because it is a header row
						if (rowIndex == 1) {
							rekPengirim = String.valueOf(getValue(row.getCell(0)));
							pelanggan = pelangganRepo.getByNoRekening(rekPengirim);
							}
						if (rowIndex < 3) {
							continue;
						}
						String rekPenerima = String.valueOf(getValue(row.getCell(0)));
						String kodeBank = String.valueOf(getValue(row.getCell(1)));
						String bankPenerima = String.valueOf(row.getCell(2));
						double jumlahTransfer = Double.parseDouble(row.getCell(3).toString());
						double kurangiSaldo = pelanggan.getSaldo() - jumlahTransfer;

						pelanggan.setSaldo(kurangiSaldo);

						if (jumlahTransfer != 0) {
							pelangganRepo.save(pelanggan);

						}
						transaction = Transaksi.builder().norekPengirim(rekPengirim).norekPenerima(rekPenerima)
								.kodeBankPenerima(kodeBank).namaBankPenerima(bankPenerima)
								.jumlahTransfer(jumlahTransfer).createadd(LocalDateTime.now()).namaFile(namaFile)
								.build();
						transactions.add(transaction);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			if (!transactions.isEmpty()) {
				// save to database

				return transaksiRepo.saveAll(transactions);
			}
		}
		return null;

	}

	private Object getValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case ERROR:
			return cell.getErrorCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return null;
		case _NONE:
			return null;
		default:
			break;
		}
		return null;
	}

	public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
		int numOfNonEmptyCells = 0;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			// System.out.println(row);
			if (row != null) {
				XSSFCell cell = row.getCell(columnIndex);
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					numOfNonEmptyCells++;
				}
			}
		}
		return numOfNonEmptyCells;
	}
	
	public List<Transaksi> groupByNamaFile() {
		return transaksiRepo.grupByNamaFile();
		
	}

	public List<Transaksi> getAll() {
		return transaksiRepo.findAll();
	}
}
