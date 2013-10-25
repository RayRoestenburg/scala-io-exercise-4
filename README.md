Exercise 4
==========

In this exercise we continue with the end result of [exercise 3](http://github/RayRoestenburg/scala-io-exercise-3).

In the previous exercise we ended up with a ReverseActor that delegated to an AsyncReverseFunction.

So far we have hardcoded values like the host and port to listen to, and of course actors could need some settings that should not be hardcoded.
 
###Objective

The objective of this exercise is to learn how to create a **Settings extension** which is accessible from any actor in the actor system.


###What is already prepared

The end result of exercise 3 is the starting point of this exercise. 
On top of that another child actor has been added to the Receptionist.

###The Exercise

Add Init message which the ReverseActor sends to itself to start off initialization.

Add NotInitialized message to indicate the ReverseActor is not ready yet.

From the ReverseActor constructor (or preStart) send Init to self.

The receive method should be set to the uninitialized Receive function.
Create an uninitialized Receive method.
Send back NotInitialized on a Reverse message in the uninitialized state / receive function.
Load the AsyncReverseFunction using the ReverserFactory on receiving Init. Once the Future completes successfully call become to transition to initialized state. pass through the AsyncReverseFunction to the initialized Receive function instead of using a var.

Implement the initialized Receive function, call the AsyncReverseFunction, on completion of the Future send back to a **captured sender** (do not close over sender but create a val which contains the sender at the time of receiving the Reverse message). 





