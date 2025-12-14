ThisBuild / scalaVersion := "2.12.19"

// Runtime-only dependency (this is important)
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.9.1" % Runtime  