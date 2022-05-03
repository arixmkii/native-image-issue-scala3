
name := "Failed Native Image Scala 3 Kind Projector"

ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .aggregate(failedNative)

lazy val failedNative = (project in file("failed-native"))
  .settings(
    name := "failed-native",
    organization := "io.github.arixmkii",
    version := "0.0.1-SNAPSHOT",
    crossScalaVersions := Seq("3.1.2", "2.13.8"),
    scalacOptions := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) => Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-Ykind-projector")
        case _ => Seq("-unchecked", "-deprecation", "-feature", "-Xsource:3")
      }
    },
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) => Seq()
        case _ => Seq(compilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full))
      }
    }
  )
  .settings(
    nativeImageOptions ++= List("--no-fallback"),
    nativeImageVersion := "22.1.0",
    nativeImageJvm := "graalvm-java17",
  )
  .enablePlugins(NativeImagePlugin)
