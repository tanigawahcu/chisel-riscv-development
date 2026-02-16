// See README.md for license details.

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.tanigawahcu"

//val chiselVersion = "7.7.0"
val chiselVersion = "6.5.0"

lazy val root = (project in file("."))
  .settings(
    name := "chisel-riscv-development",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
//      "org.scalatest" %% "scalatest" % "3.2.19" % "test",
      "edu.berkeley.cs" %% "chiseltest" % "6.0.0" % "test"
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations",
    ),
    addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % chiselVersion cross CrossVersion.full),
  )
