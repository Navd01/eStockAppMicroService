package com.naveed.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Company {
	
	private Long id;
	
	private String companyCode;
	
	
	private String companyName;
	
	
	private String companyCEO;
	
	
	private long companyTurnover;
	
	
	private String companyWebsite;
	
	
	private String stockExchange;
	
	private Double latestStockPrice;
	

}
