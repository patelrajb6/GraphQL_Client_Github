name := "cs474_courseproject"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq (
  //logger
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  //requests library (DID NOT WORK...)
  "com.lihaoyi" %% "requests" % "0.5.1",
  //http request library that did work
  "org.apache.httpcomponents" % "httpclient" % "4.5.12",
  //json parser
  "io.spray" %%  "spray-json" % "1.3.5"
)