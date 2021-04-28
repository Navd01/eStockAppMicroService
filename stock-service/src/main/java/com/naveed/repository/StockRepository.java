package com.naveed.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveed.beans.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> getByCompanyCodeAndCreateAtBetween(String companyCode , LocalDate startDate, LocalDate endDate);
	
	List<Stock> getByCompanyCode(String companyCode);
	 void deleteByCompanyCode(String companyCode);

}
