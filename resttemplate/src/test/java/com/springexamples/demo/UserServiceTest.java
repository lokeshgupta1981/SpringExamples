package com.springexamples.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.springexamples.demo.service.UserService;
import com.springexamples.demo.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@RestClientTest(UserServiceImpl.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class UserServiceTest 
{
	@Autowired
	private MockRestServiceServer server;

	@Autowired
	private UserService client;

	@Test
	public void testServiceCall() 
	{
		this.server.expect(requestTo("http://localhost:8080/users"))
					.andRespond(withSuccess("<users></users>", MediaType.TEXT_PLAIN)); 
		
		String userServiceResponse = client.testUserService();
		
		assertThat(userServiceResponse).isEqualTo("<users></users>");
	}
}
