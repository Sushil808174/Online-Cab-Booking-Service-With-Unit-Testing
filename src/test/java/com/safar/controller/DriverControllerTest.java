package com.safar.controller;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safar.entity.Driver;
import com.safar.service.DriverService;

@WebMvcTest(controllers = DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DriverControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService userService;
    private Driver usersRequest;
    private Driver usersResponse;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    public void init(){
    	 usersRequest = new Driver();
         usersRequest.setDriverName("Susheel");
         usersRequest.setEmail("sushil@gmail.com");
         Mockito.when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
         usersRequest.setPassword(passwordEncoder.encode("12345"));
         usersRequest.setMobileNo("9876543210");
         usersRequest.setAddress("Hamirpur");
         usersRequest.setLicenceNo("lp123456");
         usersRequest.setNewLocation("Kanpur");

         usersResponse = new Driver();
         usersResponse.setDriverId(10);
         usersResponse.setDriverName("Susheel");
         usersResponse.setEmail("sushil@gmail.com");
         usersResponse.setPassword("12345");
         usersResponse.setMobileNo("9876543210");
         usersResponse.setAddress("Hamirpur");
         usersResponse.setLicenceNo("lp123456");
         usersResponse.setNewLocation("Kanpur");
    }

    @Test
    @DisplayName("Customer can be registered")
    public void testInsertDriverHandler_whenValidDetailsProvided_returnInsertedDriver() throws Exception{
//        Arrange
        Mockito.when(userService.insertDriver(any(Driver.class))).thenReturn(usersResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/driver")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(usersRequest));


//        act

      MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
      String responseBodyAsString = mvcResult.getResponse().getContentAsString();
      Driver createdUser = new ObjectMapper().readValue(responseBodyAsString,Driver.class);

//      assert
        assertEquals(createdUser.getDriverName(),usersResponse.getDriverName(),"Created Driver name is incorrect");
        assertNotNull(createdUser.getDriverId(),"created driver id should not be empty");
    }
}