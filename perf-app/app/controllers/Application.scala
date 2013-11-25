package controllers

import play.api._
import play.api.mvc._
import redis.RedisClient
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.collection.Seq
import play.api.Play.current
import com.typesafe.plugin.RedisPlugin
import play.api.libs.concurrent.Akka

object Application extends Controller {
  
  implicit val akkaSystem = akka.actor.ActorSystem()

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def query(q: String) = Action {
  	Ok("param : " + q)
  }

  def asyncQuery(q: String) = Action.async {
    implicit val context = Akka.system.dispatchers.lookup("non-blocking-pool")
    val asyncResult = Future {"param : " + q}
    asyncResult.map(result =>   
        Ok(result)
    )
  }

  val redis = RedisClient()

  def asyncRedisQuery(q: String) = Action.async {
    implicit val context = Akka.system.dispatchers.lookup("non-blocking-pool")  	
    redis.get(q).map(result => Ok(result.map(_.utf8String).getOrElse("not found")))
  }

  def blockingRedisQuery(q: String) = Action { 
    val result = Await.result(redis.get(q), 10 seconds).map(_.utf8String).getOrElse("not found")
  	Ok(result) 
  }
  
}