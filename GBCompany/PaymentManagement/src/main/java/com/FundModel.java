package com;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FundModel{
	
	public String fundCode;
	public String fundType;
	public String amount;
	
	private String fundDetails;
	
	public FundModel(String fundDetails){
		
		this.fundDetails = fundDetails;
	}
	
	
	public void jsonConversion() {
		
		
		 JsonObject fundObject = new JsonParser().parse(fundDetails).getAsJsonObject(); 
		 setFundCode(fundObject.get("fundCode").getAsString());
		 setFundType(fundObject.get("fundType").getAsString());
		 setAmount(fundObject.get("amount").getAsString());
		
	}
	public FundModel(String fundCode, String fundType, String amount) {
		super();
		this.fundCode = fundCode;
		this.fundType = fundType;
		this.amount = amount;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType){
		this.fundType = fundType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}