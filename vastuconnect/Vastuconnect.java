package com.vc.staging.util;

import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vc.staging.dto.UserDetailForLenderDTO;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Vastuconnect {
	String ln_Sep= System.lineSeparator();
	private String token_value;
	String result =null;
	MediaType mediaType = MediaType.parse("application/json");
	private JSONArray case_refernce;
	public String VastuStageOne(String userId, String passwd, String apiUrl) throws IOException, JSONException {
		System.out.println("Inside Vastu connect -first rest Api");
		 Map<String, String> mapStage1 = new HashMap<>();
	        mapStage1.put("user_name", userId);
	        mapStage1.put("password", passwd);
	        ObjectMapper objectMapper_1 = new ObjectMapper();
	        JSONObject jsonResponse =null;
	        OkHttpClient client_1 = new OkHttpClient().newBuilder()
				  .build();
				
				//String media_string = "{\r\n    \"user_name\":\""+userId+"\",\r\n    \"password\":\""+passwd+"\"\r\n}";
				 String jsonString_1 = objectMapper_1.writeValueAsString(mapStage1);
				 
				System.out.println("meida string &&&&&&"+jsonString_1);
				RequestBody body = RequestBody.create(mediaType, jsonString_1);
				Request request = new Request.Builder()
				  .url(apiUrl)
				  .method("POST", body)
				  .addHeader("Accept", "application/json")
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = client_1.newCall(request).execute();
				int responseCode = response.code();
			//	System.out.println(response.body().toString());
				JSONObject jsonObject = new JSONObject(response.body().string());
				JSONObject data_object = jsonObject.getJSONObject("data");
				System.out.println("dataobject ---"+data_object);
				token_value = data_object.getString("token"); 

				System.out.println("token is ---"+token_value); 
				System.out.println("okHttpResponseOutput.." + token_value + response.code() + "classes"+ response.getClass());
				System.out.println("");
				if (responseCode == 200) {
					result ="SUCCESS";
				 } else {
					 token_value= null;
					 result ="FAIL";
					 
				}
			return result;
			}
	public int VastuStageTwo(String token, String apiUrl) throws IOException, JSONException {
		final List<UserDetailForLenderDTO> finalList = new ArrayList<>();
     JSONObject jsonDocs = new JSONObject();
     jsonDocs.put("type", "pan");
     jsonDocs.put("id", "abced1123f");
     jsonDocs.put("image", "\"/9j/4AAQSkZJRgABAQAAAQABAAD/4SmHRXhpZgAATU0AKgAAAAgABQEaAAUAAAABAAAASgEbAAUAAAABAAAAUgEoAAMAAAABAAIAAAITAAMAAAABAAEAAIdpAAQAAAABAAAAWgAAALQAAA");
     JSONObject jsonLeads = new JSONObject();
	        // Add some properties to the object
		 jsonLeads.put("first_name", "Robert");
		 jsonLeads.put("last_name", "Downey");
		 jsonLeads.put("gender", "Male");
		 jsonLeads.put("dob", "1985-07-21");
		 jsonLeads.put("mobile", "9876543210");
		 jsonLeads.put("dealer_code", "VD00000652");
		 jsonLeads.put("loan_amount", "20000");
		 jsonLeads.put("address", "NCPA Marg,Nariman Point,");
		 jsonLeads.put("city","mumbai");
		 jsonLeads.put("state", "maharashtra");
		 jsonLeads.put("product_code", "VL");
		 jsonLeads.put("vehicle_code", "MV0000335");
		 jsonLeads.put("pin", "400021");
		 jsonLeads.put("docs", new JSONArray("["+jsonDocs+"]"));
	       OkHttpClient client_2 = new OkHttpClient().newBuilder().build();
	        JSONArray arrInput_req = new JSONArray("["+jsonLeads+"]");
	    	System.out.println("token value is" +token_value);
	        System.out.println("arrInput_req to string "+arrInput_req.toString());	  
	        RequestBody body = RequestBody.create(mediaType, arrInput_req.toString());
	        Request request_2 = new Request.Builder()
					  .url(apiUrl)
					  .method("POST", body)
					  .addHeader("Accept", "application/json")
					  .addHeader("Content-Type", "application/json")
					  .addHeader("Authorization", "Bearer "+token_value)
					  .build();
					Response response_2 = client_2.newCall(request_2).execute();
					
					JSONObject jsonObject = new JSONObject(response_2.body().string());
					JSONObject data2_object = jsonObject.getJSONObject("data");
					System.out.println("dataobject ---"+data2_object);
					case_refernce = data2_object.getJSONArray("leads"); 
					System.out.println("case reference value is "+case_refernce.get(0));
	
				
		return response_2.code();
		
	}
	public String VastuStageThree(String apiUrl3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	}

