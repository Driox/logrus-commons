name := """logrus-commons"""

version := "1.0.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest"   % "2.2.4" % "test" withSources(),
  "org.slf4j"      %  "slf4j-api"   % "1.7.21"
)

// sbt and compiler option
scalacOptions ++= Seq(
	"-deprecation",
	"-feature",
	"-unchecked",
	"-Xfatal-warnings",
	"-Xlint",
	"-Ywarn-dead-code",
	"-Ywarn-unused",
	"-Ywarn-unused-import"
)

// ~~~~~~~~~~~~~~~~~
//Scalariform config

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(scalariform.formatter.preferences.AlignSingleLineCaseStatements, true)
  .setPreference(scalariform.formatter.preferences.AlignParameters, true)
  .setPreference(scalariform.formatter.preferences.DoubleIndentClassDeclaration, true)
  .setPreference(scalariform.formatter.preferences.PreserveDanglingCloseParenthesis, true)

