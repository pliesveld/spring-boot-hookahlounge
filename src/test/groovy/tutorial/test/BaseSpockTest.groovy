package tutorial.test

import io.restassured.RestAssured
import org.junit.Ignore
import tutorial.HookahloungeApplication

import static io.restassured.RestAssured.given
import static org.junit.Assert.*
import io.restassured.RestAssured
import static io.restassured.RestAssured.config
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import javax.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

import spock.lang.*


@SpringBootTest(classes = HookahloungeApplication.class, webEnvironment = RANDOM_PORT)
class BaseSpockTest extends Specification {
 
	@LocalServerPort
	protected int sport

	def setup() throws Exception {
		setupRestAssuredSpec()
	}	
	
	def setupRestAssuredSpec() throws Exception {
//		RestAssured.baseURI = "http://${SERVER}/"
		RestAssured.port = sport
		config = config().redirect(config.getRedirectConfig().followRedirects(true).and().maxRedirects(0))
				.logConfig(config.getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails().enablePrettyPrinting(true))
				.httpClient(config.getHttpClientConfig().reuseHttpClientInstance())
				.sslConfig(config.getSSLConfig().allowAllHostnames())

	}

    def "when context is loaded then random port is defined"() {
        expect: "LocalServerPort is greater than 0"
       	sport > 0
    }
}