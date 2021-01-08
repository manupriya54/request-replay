name := "request-replay-scala"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "io.spray" %%  "spray-json" % "1.3.6",
  "org.scalatest" %% "scalatest" % "3.0.9" % "test",
  "org.scalactic" %% "scalactic" % "3.0.9",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
