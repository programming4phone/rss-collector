package com.programming4phone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.programming4phone.rss.collector.entity.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NwitimesServiceIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")   
	int port;

	@Ignore
	@Test
	public void test() {
		
		successRss("nwitimes-latest");
		successRss("nwitimes-lake");
		successRss("nwitimes-porter");
		successRss("nwitimes-illinois");
		successRss("nwitimes-indiana");
		successRss("nwitimes-national");
		successRss("nwitimes-world");
	
	}
	
	private void successRss(String feedId){
		ResponseEntity<List<Article>> entity; 
		List<Article> articles;
		
		entity =
		        restTemplate.exchange("http://localhost:" + Integer.toString(port) + "/rssAsJson/" + feedId,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Article>>() {});
		
		assertTrue(entity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(entity.getHeaders().containsKey("Access-Control-Allow-Origin"));
		articles = entity.getBody();
		assertNotNull(articles);
	}

}
