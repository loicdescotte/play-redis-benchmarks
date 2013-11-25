name := "perf-app"

version := "1.0-SNAPSHOT"

resolvers += "etaty" at "https://github.com/etaty/rediscala-mvn/raw/master/releases/"

resolvers += "Typesafe Releases" at "http://typesafe.artifactoryonline.com/typesafe"

resolvers += "pk11 repo" at "http://pk11-scratch.googlecode.com/svn/trunk"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.etaty.rediscala" %% "rediscala" % "1.3",
  "com.typesafe" %% "play-plugins-redis" % "2.1.1"
)     

play.Project.playScalaSettings
