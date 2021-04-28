package com.Naveed.eStockApp.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Naveed.eStockApp.Beans.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Override
	List<Company> findAll();
	
	public Company findByCompanyCode(String companyCode);
		
	
}
