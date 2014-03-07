
Testing Serialization of Lambdas
--------------------------------

The following contains two projects that demonstrate how serialization of
Java lambda functions does or doesn't work.  The hypothesis here was that 
the bytecode for a lambda function is serialized or generated dynamically
with the lambda.  Hence, a lambda function would not require bytecode to be
downloaded dynamically.  To test this hypothesis...

1 - Run the 'com.trasuk.lambda.LambdaGenerationTest' in the
SerializedLambdaGenerator project.  This test will create a lambda function, 
make sure it works, and then serialize a copy of it to 'lambda.ser' in the 
project's root folder.  
2 - Copy that 'lambda.ser' file into the root of the SerializedLambdaUser project.  
3 - Run 'com.trasuk.lambda.LambdaUsageTest' in the SerializedLambdaUser
project.  
4 - Note that we get a ClassNotFoundException:  

  Testcase: testLambdaUsage(com.trasuk.lambda.LambdaUsageTest):	Caused an ERROR
  com.trasuk.lambda.LambdaGenerationTest
  java.lang.ClassNotFoundException: com.trasuk.lambda.LambdaGenerationTest
	at java.net.URLClassLoader$1.run(URLClassLoader.java:372)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:360)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:340)
	at java.io.ObjectInputStream.resolveClass(ObjectInputStream.java:626)
	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1613)
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1518)
	at java.io.ObjectInputStream.readClass(ObjectInputStream.java:1484)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1334)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1993)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1918)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1801)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1351)
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:371)
	at com.trasuk.lambda.LambdaUsageTest.testLambdaUsage(LambdaUsageTest.java:25)

Conclusion
----------

Lambda functions are serializable, however the serialized object
actually refers
to the class in which the lambda is defined.  As such, that class 
needs to be in the classpath (or available through a codebase annotation)
in the client.

This result is hinted at in [Brian Goetz' presentation](http://www.slideshare.net/jaxlondon2012/lambda-a-peek-under-the-hood-brian-goetz)
on page 16, in which the lambda's code appears to have been compiled into 
a static method on the class (he talks about 'desugaring' the lambda).
