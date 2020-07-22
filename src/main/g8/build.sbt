resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.jcenterRepo
)

inThisBuild(
  List(
    scalaVersion := "2.13.3",
    crossScalaVersions := Seq("2.12.11", "2.13.3"),
    organization := "crew.zio",
    homepage := Some(url("https://github.com/zio-crew/<project>")),
    startYear := Some(2020),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "<nickName>",
        "<givenName>",
        "<email>",
        url("https://github.com/<developer>")
      )
    ),
    scmInfo := Some(
      ScmInfo(url("https://github.com/zio-crew/<project>"), "scm:git@github.com:zio-crew/<project>.git")
    )
  )
)

lazy val commonSettings = Seq(
// Refine scalac params from tpolecat
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  ),
  name := "<projectName"
)

lazy val zioDeps = libraryDependencies ++= Seq(
  "dev.zio" %% "zio"              % Version.zio,
  "dev.zio" %% "zio-test"         % Version.zio % "test",
  "dev.zio" %% "zio-test-sbt"     % Version.zio % "test",
  "dev.zio" %% "zio-interop-cats" % Version.zioInteropCats
)

lazy val root = (project in file("."))
  .settings(
    maxErrors := 3,
    commonSettings,
    pubSettings,
    zioDeps,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

lazy val pubSettings = Seq(
  bintrayRepository := "<repoName>",
  publishMavenStyle := true,
  bintrayOrganization := Some("zio-crew")
)

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

scalafixDependencies in ThisBuild += "com.nequissimus" %% "sort-imports" % "0.5.4"
