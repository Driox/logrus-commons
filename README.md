# Logrus common code

You can see more information about Logrus on logr.us

## Requirements

Java 1.8 or later.

## Installation

### Sbt users

Add this dependency to your project's POM:

```scala
lazy val root = Project("root", file("."))
                    .dependsOn(logrusCommonsProject)

lazy val logrusCommonsProject = RootProject(uri("https://github.com/Driox/logrus-commons.git"))

```

and then you may use it

```scala
import us.logr.utils._

val s = us.logr.utils.StringUtils.deAccent("hello José")
println(s) // should print "hello Jose"
```

### Others

You'll need to manually install the following JARs:

* Download the JAR from <path to jar>


## Documentation

TODO

## Testing

You must have Sbt installed. To run the tests:

    sbt test

You can run particular tests by using `testOnly pkg.ClassName -- -z update`. Make sure you use the fully qualified class name to differentiate between
unit and functional tests. For example:

    sbt "testOnly pkg.ClassName -- -z update"
