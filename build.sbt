lazy val commonSettings = Seq(
  organization := "com.github.nabezokodaikon",
  version := "0.0.1",
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint"
  ),
  shellPrompt := { state =>
    val branch = if (file(".git").exists) {
      "git branch".lines_!.find { _.head == '*' }.map { _.drop(1) }.getOrElse("")
    } else ""
    Project.extract(state).currentRef.project + branch + " > "
  }
)

lazy val root = (project.in(file(".")))
  .settings(commonSettings: _*)
  .settings(
    name := "play-submit",
    resolvers ++= {
      Seq(
      )
    },
    libraryDependencies ++= {
      Seq(
        // Test
        "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
        "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
      )
    }
  )
  .enablePlugins(PlayScala)

// controller に class を使用できるようにする。
routesGenerator := InjectedRoutesGenerator
