package com.vc.staging.dto;

import com.vc.staging.util.ValuCirclesConstants;

/**
 * 
 * @author Priyaraj - To show the Employer names in the dropdown in frontend financial section page - 23-02-2023
 *
 */
public class EmployerCategoryDTO {
	private String employerName;
	private String employerCin;
	/*private int employerId;
	private int activeStatus;
	private int score;
	private int weightPct;
	private int referenceCategoryId;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private int industrySegmentId;
	private String industrySegment;	
	private String classification;*/	
	
	@Override
	public String toString(){
		String conString="";
		try {
			conString=ValuCirclesConstants.mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conString;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerCin() {
		return employerCin;
	}

	public void setEmployerCin(String employerCin) {
		this.employerCin = employerCin;
	}
	
}
