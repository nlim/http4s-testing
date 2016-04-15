name := "http4s-testing"

version := "0.0.1"

scalaVersion := "2.11.2"

parallelExecution in Test := false

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies += "org.tpolecat" %% "doobie-core" % "0.2.3"

libraryDependencies += "org.tpolecat" %% "doobie-contrib-hikari" % "0.2.3"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.20"

libraryDependencies += "io.argonaut" %% "argonaut" % "6.1-M4"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"

libraryDependencies += "org.http4s" %% "http4s-dsl" % "0.14.0-SNAPSHOT"

libraryDependencies += "org.http4s" %% "http4s-blaze-server" % "0.14.0-SNAPSHOT"

libraryDependencies += "org.http4s" %% "http4s-blaze-client" % "0.14.0-SNAPSHOT"

libraryDependencies += "org.http4s" %% "http4s-argonaut" % "0.14.0-SNAPSHOT"

autoCompilerPlugins := true

scalacOptions += "-feature"
