package com.vc.staging.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jettison.json.JSONException;

import com.vc.staging.util.Vastuconnect; 

public class Sample {

	private static final String CONNECT_USERID = "vastu-api@gopik.com";
	private static final String CONNECT_PASSWD = "pass@123";
	private static final String API_URL_1= "https://test-marketplace.vastucorp.com/api/v1/login";
	private static final String API_URL_2= "https://test-marketplace.vastucorp.com/api/v1/leads";
	private static final String API_URL_3= "https://test-marketplace.vastucorp.com/api/v1/case-status"; 
	public static void main(String[] args) throws IOException, JSONException {
		
		Vastuconnect tokenString = new Vastuconnect();
		
		String stage1_token= tokenString.VastuStageOne(CONNECT_USERID , CONNECT_PASSWD, API_URL_1);
		System.out.println("token from stage1"+ stage1_token);
		if (stage1_token != null)
		{ 
			int result_2= tokenString.VastuStageTwo(stage1_token, API_URL_2);
			if(result_2 == 200) {
				String case_Ref =tokenString.VastuStageThree(API_URL_3);
			}
		}
	}
}
