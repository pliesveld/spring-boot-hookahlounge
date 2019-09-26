package tutorial.test

import groovy.json.JsonOutput
import io.restassured.RestAssured
import io.restassured.http.ContentType

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import tutorial.HookahloungeApplication

import io.restassured.RestAssured
import tutorial.domain.Order

import static io.restassured.RestAssured.config
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

import static org.junit.Assert.*
import static io.restassured.RestAssured.*




import org.junit.Test

class SimpleSpockTest extends BaseSpockTest {

	def "when context is loaded then random port is defined 9"() {
		expect: "LocalServerPort is greater than 0"
		sport > 0
	}

	def "test /api/all"() {
		given:
		def request = given()

		when:
			def response = request.with().get("/api/all")
		then:
			response.then().log().ifError().statusCode(200)
	}

	def "test /api/hooka"() {
		given:
			def request = given()
			def order = new Order()
				order.setFlavor("strawberry")
				order.setHoses(2)
				order.setHeadSize("jumbo")
			def payload = JsonOutput.toJson(order)

		when:
			def response = request.with().contentType(ContentType.JSON).body(payload).post("/api/hooka")
		then:
			response.then().log().ifError().statusCode(200)
	}


}
