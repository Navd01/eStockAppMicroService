package com.Naveed.eStockApp.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Naveed.eStockApp.Beans.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> getByCompanyCodeAndCreateAtBetween(String companyCode , LocalDate startDate, LocalDate endDate);

}
