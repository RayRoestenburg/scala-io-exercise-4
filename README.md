Exercise 4
==========

In this exercise we continue with the end result of [exercise 3](http://github/RayRoestenburg/scala-io-exercise-3).
So far we've hardcoded a couple of things like the host and port of the HTTP listener. In this exercise we want to use the HOCON configuration format for configuring the application.

We're adding another actor for our string manipulation business for supporting a version of [Leetspeak](http://en.wikipedia.org/wiki/Leet).
This actor needs some configuration values. The L33tActor 'leetifies' incoming text in a different style according to two configuration values:

    uppercase (true/false) 
    use-shift (true/false)

As en example, in this dialect of Leet **akka** would end up as **4KK4** with uppercase set to *true* and use-shift set to *false*. With use-shift set to true it would end up as **$KK$**.   
We're going to use a Settings extension and the application.conf file to make the L33tActor configurable.

###Objective

The objective of this exercise is to learn how to create your own **Settings Akka extension** which is accessible from any actor in the actor system. a Settings can be referenced anywhere by using:

    val settings = Settings(context.system)  

From an Actor or:

    val settings = Settings(system)
    
From anywhere where you have access to the actor system.    

###What is already prepared

The end result of exercise 3 is the starting point of this exercise. A Settings class and object are already provided which have to be filled in for the l33t configuratin section.
A l33t route is added to the Receptionist, which creates the L33tAactor.  
The L33tActor needs to be modified to use the configuration.

###The Exercise

- Add your own l33t configuration to the application.conf file (uppercase and use-shift)
- Create a val for the settings in L33tActor
- Get 'uppercase' boolean from the Settings L33t object in the L33tActor
- Get 'useShift' boolean from the Settings L33t object in the L33tActor
- Create a L33t object that contains the uppercase and useShift values in the Settings class
- Read the uppercase and useshift from your configuration in the Settings class
- Review the Settings Extension on the particulars of building an Akka Extension
- Review the test for L33tActor on how to modify the configuration in a test

