INSTRUCTIONS TO RUN

Running the following commands will generate MetroPaymentInfo.log containing the output
1. Test containing trips for 2 days
./gradlew test --tests  com.sahaj.metroPaymentSystem.model.TigerCardTest.getTwoDayFare
2. Test containing trips for 2 weeks
./gradlew test --tests  com.sahaj.metroPaymentSystem.model.TigerCardTest.getMultiWeekFare
Running all the units tests together will not append all the unit test logs to MetroPaymentInfo.log file. 
So I recommend running each unit test one by one like above and then check the MetroPaymentInfo.log file.
There are unit tests for other classes too apart from what I have mentioned above.

OTHER WAYS TO RUN

"./gradlew run" can also be used to run the app. Feel free to define the inputs in MetroPaymentApp.java file before running
the command. I have already added some sample inputs in the same file.

HOW INPUT WORKS

The input can be added to the trip object. 
Here is an example to create an input which happens on a Monday 10:20 AM from Zone One to Zone Two
Trip trip = Trip.addTrip("Monday", 10, 20, 1, 2, false);
The boolean value is used to identify if its a new week or not. If you want to add inputs for a new week in an input containing trips for multiweek, make sure
to set the isNewWeek boolean value to true for the first record of the new week's input. Once you add a trip, add it to a List and pass it to the TigerCard class's
getInstance method along FareCalculator. The main function contains code on how to do this. Referring to it will be more helpful


TOOLS USED
1. Java
2. Gradle
3. JUnits for unit tests and also to test final output 
