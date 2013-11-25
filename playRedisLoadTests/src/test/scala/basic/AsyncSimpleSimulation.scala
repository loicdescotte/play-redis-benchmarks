package basic

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import bootstrap._

class AsyncSimpleSimulation extends Simulation {

	val httpConf = httpConfig
		.baseURL("http://" + Conf.host + ":" + Conf.port)
		.disableFollowRedirect

	val headers = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Keep-Alive" -> "115",
		"X-Requested-With" -> "XMLHttpRequest")

	val contextScn = scenario("asyncSimple")
		.repeat(100){
		exec(
			http("request_async_simple")
				.get("/asyncQuery?q=toto")
				.headers(headers)
				.check(status.is(200)))
		}
	
	
	setUp(		
		contextScn.users(500).ramp(10).protocolConfig(httpConf)
	)
}

