# Tick Tank

A simple tank drive module for Toast. The goal is to be very modular and
extensible, so the module is compatible with any tank drive robot.

### Integrating this module
To set up this module in your development environment, follow these steps:

1. Clone the repository
2. Follow the instructions for building TickTock and PID and move the compiled 
jars (from /build/libs) to /modules/ in the TickTank repository.
2. Run `gradlew eclipse` for Eclipse, or `gradlew idea` for IntelliJ (Linux/Mac
users should use `./gradlew` instead of `gradlew`.)
3. Edit the `build.gradle` file to reflect your desired configuration (e.g.
changing the team number)

To build this module, simply run `gradlew build`.
To deploy this module to your Robot, simply run `gradlew deploy`.
If you haven't already, you can deploy
[Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew
toastDeploy`.

### Adding to your robot

Check out [2016-Robot-Toast](https://github.com/Team236/2016-Robot-Toast) to see
an example.

1. Create a new Settings object and instantiate it
2. Assign the public fields of the Settings object as they apply to your robot.
3. Use the `TickTank(Settings)` constructor to create a new tank subsystem 
using your `Settings` object
