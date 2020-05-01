name := "cs474_courseproject"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq (
  //logger
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "net.liftweb" %% "lift-json" % "3.4.1",

  //http request library that did work
  "org.apache.httpcomponents" % "httpclient" % "4.5.12",
  "com.typesafe" % "config" % "1.4.0",
  //json parser
  "io.spray" %%  "spray-json" % "1.3.5",
  //unit testing
  "junit" % "junit" % "4.8.1" % "test",
  "org.scalatest" %% "scalatest" % "3.3.0-SNAP2" % Test
)