package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import com.typesafe.plugin.RedisPlugin;
import play.cache.Cache;
import redis.clients.jedis.*;

public class ApplicationJava extends Controller {
  
  //default play redis plugin (synchronous) - also working with scala (with jedis wrapper in sedis)
  public static Result query(String q) {
    JedisPool p = Play.application().plugin(RedisPlugin.class).jedisPool();
    Jedis j = p.getResource();
    String r = j.get(q);
    p.returnResource(j);
    return ok(r);
  }
  
}