# KafkaPractice
This repository shows simple scala kafka example code for beginners

It showcases 
1. producers
2. consumers
3. custom serdes
4. various callback functionalities

It is built on kafka-2.1.1 and scala 2.11. sbt files are included.


### Package it using command inside folder KafkaPractice:
``` sh
sbt package
```
### Run producers as:

``` sh
java -cp "kafkapractice_2.11-1.0.jar:${KAFKA_HOME}/libs/*" Main.run producer first
```
Note that: Replace first with random/user to run other producers

### Run consumers as:

``` sh
java -cp "kafkapractice_2.11-1.0.jar:${KAFKA_HOME}/libs/*" Main.run consumer first
```
Note that: Replace first with random/user  to run other consumers
