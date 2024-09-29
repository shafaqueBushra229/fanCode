package com.elaa.fancode;

import com.aventstack.extentreports.ExtentTest;
import com.elaa.fancode.common.GetMethod;
import com.elaa.fancode.report.ReportUtils;
import com.elaa.fancode.request.GetRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Runner {
    private ExtentTest test;
    private List<Integer> userIds;

    @BeforeClass
    public void setUpClass() {
        ReportUtils.initReport();
        
    }
    
    @Test(priority = 1)
    public void fetchUsersFromFanCodeCity() {
    	test = ReportUtils.createTest("Fancode City can be identified by lat between ( -40 to 5) and long between ( 5 to 100) in users api");
    	Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourceUsers());
    	List<Integer> userIds = responseBody.jsonPath().getList("findAll { user -> " +
                "def lat = Float.parseFloat(user.address.geo.lat); " +
                "def lng = Float.parseFloat(user.address.geo.lng); " +
                "lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100 }.id");

        this.userIds = userIds;
        test.info("Fetched users from FanCode city: " + userIds);
        Assert.assertFalse(userIds.isEmpty(), "No users found in FanCode city");
    }

    @Test(priority = 2)
    public void VerifyUsersAPI() {
    	test = ReportUtils.createTest("Verify users Response and Status Code");
    	int statusCode = GetMethod.ResponseStatusCode(GetRequest.BaseURI(),
				GetRequest.ResourceUsers());
		Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourceUsers());
		String responseHeader = responseBody.getHeader("Content-Type");
		
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		test.info("Status Code: " + statusCode);
		test.info("Response Header: " + responseHeader);
        test.info("Response Body is : \n <code>" + responseBody.asPrettyString()+ "</code>");
    }
    @Test(priority = 3)
    public void VerifyTodoAPI() {
    	test = ReportUtils.createTest("Verify Todo Response and Status Code");
    	int statusCode = GetMethod.ResponseStatusCode(GetRequest.BaseURI(),
				GetRequest.ResourceTodo());
    	Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourceTodo());
    	String responseHeader = responseBody.getHeader("Content-Type");
    	
    	Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
        test.info("Status Code: " + statusCode);
        test.info("Response Header: " + responseHeader);
        test.info("Response Body is : \n <code>" + responseBody.asPrettyString() + "</code>");
    }
    
    @Test(priority = 4)
    public void VerifyPostsAPI() {
    	test = ReportUtils.createTest("Verify Post Response and Status Code");
    	int statusCode = GetMethod.ResponseStatusCode(GetRequest.BaseURI(),
				GetRequest.ResourcePosts());
    	Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourcePosts());
    	String responseHeader = responseBody.getHeader("Content-Type");
    	
    	Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		
        test.info("Status Code: " + statusCode);
        test.info("Response Header: " + responseHeader);
        test.info("Response Body is : \n <code>" + responseBody.asPrettyString() + "</code>");
    }
    @Test(priority = 5)
    public void VerifyCommentsAPI() {
    	test = ReportUtils.createTest("Verify Comments Response and Status Code");
    	int statusCode = GetMethod.ResponseStatusCode(GetRequest.BaseURI(),
				GetRequest.ResourceComments());
    	Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourceComments());
    	
    	String responseHeader = responseBody.getHeader("Content-Type");
    	
    	Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		
        test.info("Status Code: " + statusCode);
        test.info("Response Header: " + responseHeader);
        test.info("Response Body is : \n <code>" + responseBody.asPrettyString() + "</code>");
    }
    @Test(priority = 6)
    public void VerifyPhotosAPI() {
    	test = ReportUtils.createTest("Verify Photos Response and Status Code");
    	int statusCode = GetMethod.ResponseStatusCode(GetRequest.BaseURI(),
				GetRequest.ResourcePhotos());
    	Response responseBody = GetMethod.ResponseBody(GetRequest.BaseURI(),
				GetRequest.ResourcePhotos());
    	
    	String responseHeader = responseBody.getHeader("Content-Type");
    	
    	Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		
        test.info("Status Code: " + statusCode);
        test.info("Response Header: " + responseHeader);
        test.info("Response Body is : \n <code>" + responseBody.asPrettyString() + "</code>");
    }
    

    @AfterClass
    public void tearDownClass() {
        ReportUtils.flushReport();
    }
}
