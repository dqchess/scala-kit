import sbt._
import Keys._


object BuildSettings {

  val buildName = "scala-kit"
  val buildOrganization = "io.prismic"
  val buildVersion = "1.2.12-THIB"
  val buildScalaVersion = "2.12.3"

  val buildSettings = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    // crossScalaVersions := Seq("2.10.4", "2.11.1"),
    scalacOptions := Seq("-deprecation", "-unchecked", "-feature"),
    publishTo := Some(Resolver.file("file",  new File(sys.props.getOrElse("publishTo", "")))),
    pomExtra := {
      <url>https://github.com/prismicio/scala-kit</url>
        <licenses>
          <license>
            <name>Apache 2</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
          </license>
        </licenses>
        <scm>
          <connection>scm:git:github.com/prismicio/scala-kit.git</connection>
          <developerConnection>scm:git:git@github.com:prismicio/scala-kit.git</developerConnection>
          <url>github.com/prismicio/scala-kit.git</url>
        </scm>
        <developers>
          <developer>
            <name>Prismic.io Team</name>
            <email>contact@prismic.io</email>
            <organization>Prismic.io</organization>
            <organizationUrl>http://prismic.io</organizationUrl>
          </developer>
        </developers>
    }
  )
}

object KitBuild extends Build {

  lazy val ScalaKit = Project(
    BuildSettings.buildName, file("."),
    settings = BuildSettings.buildSettings ++ Seq(
      scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
      scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value + "/root-doc.txt"),

      resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
      resolvers += "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
      libraryDependencies ++= Seq(
        // "com.typesafe.play" %% "play-iteratees" % "2.4.2",
        "com.typesafe.play" %% "play-json" % "2.6.3",
        "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.4",
        "com.typesafe.play" %% "play-ws-standalone-json" % "1.0.4",
        "org.apache.commons" % "commons-collections4" % "4.1",
        "org.specs2" %% "specs2-core" % "3.9.2" % "test"
      )
    )
  )
}
