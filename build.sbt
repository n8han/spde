
name := "Spde"

organization := "us.technically.spde"

version := "0.3.1"

scalaVersion := "2.9.2"

libraryDependencies += "org.processing" % "core" % "1.5.1"

libraryDependencies += "net.databinder" %% "dispatch-futures" % "0.8.8"

resolvers += "Databinder Repository" at "http://sybila.fi.muni.cz/public/maven"

scalacOptions += "-deprecation"

