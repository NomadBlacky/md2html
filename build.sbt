ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.github.NomadBlacky"
ThisBuild / organizationName := "NomadBlacky"

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin, GraalVMNativeImagePlugin)
  .settings(
    name := "md2html",
    libraryDependencies ++= Seq(
      "com.atlassian.commonmark" % "commonmark" % "0.12.1",
      "com.github.scopt" %% "scopt" % "4.0.0-RC2",
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    ),
    buildInfoKeys := Seq[BuildInfoKey](name, version),
    buildInfoPackage := "com.github.nomadblacky.md2html",
    assemblyJarName := s"${name.value}.jar",
    graalVMNativeImageOptions += "--initialize-at-build-time=scala"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
