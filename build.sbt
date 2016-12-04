name := """logrus-commons"""

version := "1.0.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"  % "2.2.4" % "test" withSources()
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
	"-Ywarn-unused-import",
	"-Ywarn-value-discard" //when non-Unit expression results are unused
)

// ~~~~~~~~~~~~~~~~~
//Scalariform config

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(scalariform.formatter.preferences.AlignSingleLineCaseStatements, true)
  .setPreference(scalariform.formatter.preferences.AlignParameters, true)
  .setPreference(scalariform.formatter.preferences.DoubleIndentClassDeclaration, true)
  .setPreference(scalariform.formatter.preferences.PreserveDanglingCloseParenthesis, true)
