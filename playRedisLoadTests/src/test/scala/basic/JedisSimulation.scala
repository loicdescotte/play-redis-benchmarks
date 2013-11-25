package basic

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import bootstrap._

class JedisSimulation extends Simulation {

	val httpConf = httpConfig
		.baseURL("http://" + Conf.host + ":" + Conf.port)
		.disableFollowRedirect

	val headers = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Keep-Alive" -> "115",
		"X-Requested-With" -> "XMLHttpRequest")

	val productsScn = scenario("redis")
		.repeat(20){
		exec(
			http("request_jedis")
				.get("/jedisQuery?q=toto")
				.headers(headers)
				.check(status.is(200)))
		}	
	
	setUp(
		productsScn.users(500).ramp(10).protocolConfig(httpConf)
	)
}

