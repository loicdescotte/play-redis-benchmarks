package basic

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AsyncSimpleSimulation extends Simulation {

	val httpConf = http
		.baseURL("http://" + Conf.host + ":" + Conf.port)
		.disableFollowRedirect

	val headers = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Keep-Alive" -> "115",
		"X-Requested-With" -> "XMLHttpRequest")

	val contextScn = scenario("asyncSimple")
		.repeat(20){
		exec(
			http("request_async_simple")
				.get("/asyncQuery?q=toto")
				.headers(headers)
				.check(status.is(200)))
		}
	
	
	setUp(		
		contextScn.inject(atOnceUsers(500)).protocols(httpConf)
	)
}

