package com.naveed.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveed.beans.Company;
import com.naveed.repository.CompanyRepository;
import com.naveed.service.MapValidationErrorService;
import com.naveed.exceptions.CompanyCodeException;
import com.naveed.intercomm.StockClient;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	StockClient stockClient;

	@PostMapping("/register")
	public ResponseEntity<?> newRegistration(@Valid @RequestBody Company registration , BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        Company company = new Company();
		try {
		registration.setCompanyCode(registration.getCompanyCode().toUpperCase());
		 company = companyRepo.save(registration);
		
		}catch(Exception e) {
			Long id = registration.getId();
			Optional<Company> findByIdCompany = companyRepo.findById(id);
			if(findByIdCompany.isPresent()) {
				Company dbCompany = findByIdCompany.get();
				if(dbCompany != null) {
					dbCompany.setLatestStockPrice(registration.getLatestStockPrice());
					companyRepo.save(dbCompany);
					
				}else {
					throw new CompanyCodeException("Project Id Already Exists");
				}
				
			}else {
			throw new CompanyCodeException("Project Id Already Exists");
			}
		}
		return new ResponseEntity<Company>(company, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/info/{companyCode}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable String companyCode){
		
		Company company = companyRepo.findByCompanyCode(companyCode);
		
		if(company == null) {
			throw new CompanyCodeException("Company with code " + companyCode + " doesnt exist" );
		}
		
		return new ResponseEntity<Company>(company , HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public List<Company> getAllCompanies(){
		
		List<Company> companyList =  companyRepo.findAll();
		return companyList;
	}
	

	
	@DeleteMapping("/delete/{companyCode}")
	public ResponseEntity<?> deleteCompany(@PathVariable String companyCode){
		Company company = companyRepo.findByCompanyCode(companyCode);
		
		if(company == null) {
			throw new CompanyCodeException("Company with code " + companyCode + " doesnt exist" );
		}
		stockClient.deleteStock(companyCode);
		companyRepo.delete(company);
		
		return new ResponseEntity<String>("Company with Code: '"+companyCode+"' was deleted", HttpStatus.OK);
		
	}
}
