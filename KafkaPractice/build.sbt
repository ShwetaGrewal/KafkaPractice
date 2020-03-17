name := "KafkaPractice"

version := "1.0"

val kafkaVersion = "2.1.1"
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
"org.apache.kafka" % "kafka-clients" % kafkaVersion,
"org.apache.commons" % "commons-lang3" % "3.9",
"io.confluent" % "kafka-avro-serializer" % "5.0.0"
)

resolvers ++= Seq(
  "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/",
  "Maven Repository" at "https://mvnrepository.com/artifact/org.apache.kafka/kafka",
  "Apache Repository" at "https://mvnrepository.com/artifact/org.apache.commons/commons-lang3",
  "Confluent Repository" at "https://packages.confluent.io/maven/"
)

//retrieveManaged := true

