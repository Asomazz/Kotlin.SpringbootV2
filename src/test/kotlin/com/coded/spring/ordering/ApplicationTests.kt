package com.coded.spring.ordering

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

//	@Autowired
//	lateinit var restTemplate: TestRestTemplate
//
//	@Test
//	fun helloWorld() {
//		val result = restTemplate.getForEntity("/hello", String::class.java)
//		assertEquals("Hello World",result.body)
//		assertEquals(HttpStatus.OK,result?.statusCode)
//	}


}
