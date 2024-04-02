# Serialization issue reproducer

## What is this?
This is a simple maven project that illustrates that a certain object graph cannot be deserialized with 8 < JDK version.

It constructs the graph, serializes it to a file then immediately tries to read it back.

Note: This is a stripped down part of a large enterprise application's code base. Javadoc was stripped off and class names may no longer match the original ones.

## How to run in?
Set JAVA_HOME to the location of the JDK you want to use and run `mvn clean test`.
With Java versions above 8 the result will be that `edu.gozke.SerializationTest.deserializationFailsIfAirportIsTheRoot()` test method fails with the below stack trace:

```
java.lang.NullPointerException
        at edu.gozke.BaseCodeObject.hashCode(BaseCodeObject.java:57)
        at java.base/java.util.HashMap.hash(HashMap.java:340)
        at java.base/java.util.HashMap.put(HashMap.java:608)
        at java.base/java.util.HashSet.readObject(HashSet.java:343)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at java.base/java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1046)
        at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2357)
        at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228)
        at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687)
        at java.base/java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2496)
        at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2390)
        at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228)
        at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687)
        at java.base/java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2496)
        at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2390)
        at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228)
        at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687)
        at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:489)
        at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:447)
        at java.base/java.util.TreeMap.buildFromSorted(TreeMap.java:2563)
        at java.base/java.util.TreeMap.buildFromSorted(TreeMap.java:2503)
        at java.base/java.util.TreeMap.readObject(TreeMap.java:2450)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at java.base/java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1046)
        at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2357)
        at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228)
        at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687)
        at java.base/java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2496)
        at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2390)
        at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228)
        at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687)
        at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:489)
        at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:447)
        at edu.gozke.SerializationTest.readObject(SerializationTest.java:47)
        at edu.gozke.SerializationTest.deserializationFailsIfAirportIsTheRoot(SerializationTest.java:26)
```

## How was it tested?
Executed on Windows [Version 10.0.19045.4170].

Could not reproduce the problem with these:
* jdk1.8.0_251 (1.8.0_251-b08) (Oracle)
* Openjdk 1.8.0_342 Temurin build (build 1.8.0_342-b07) 

Managed to reproduce the problem with these:
* jdk-11.0.21 (11.0.21+9-LTS-193) (Oracle)
* OpenJDK 11.0.18 Temurin (build 11.0.18+10)
* OpenJDK 17.07 Temurin (build 17.0.7+7)
* OpenJDK 9.0.4 Adoptium (9.0.4+11)

## Other notes
A few things could be interesting about the object graph:
 * It is cyclic
 * The error does not occur if the starting point of the graph is not the Airport object
 * The `hashCode()` method has a custom implementation which only takes into account one field and does not handle `null` values.
 
