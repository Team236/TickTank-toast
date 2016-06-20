package frc.team236.ticktank;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.IterativeModule;

public class RobotModule extends IterativeModule {

    public static Logger logger;

    public Joystick leftStick, rightStick;
    public ArrayList<SpeedController> leftMotors, rightMotors;
    public Settings settings;

    public boolean isSet = false;

    double speed = 0;
    int direction = 1;
    double interval = .01;

    @Override
    public String getModuleName() {
        return "TickTank";
    }

    @Override
    public String getModuleVersion() {
        return "1.0.0";
    }

    public Settings getDefaults() {
        Settings defaults = new Settings();
        defaults.setLeftStick(new Joystick(0));
        defaults.setRightStick(new Joystick(1));
        defaults.setCount(2);
        defaults.setControllerType(ControllerType.VICTOR);
        return defaults;
    }

    public void setLeftStick(Joystick stick) {
        this.leftStick = stick;
    }

    public void setRightStick(Joystick stick) {
        this.rightStick = stick;
    }

    public void setLeftSpeed(double speed) {
        for (SpeedController motor : leftMotors) {
            motor.set(speed);
        }
    }

    public void setRightSpeed(double speed) {
        for (SpeedController motor : rightMotors) {
            motor.set(speed);
        }
    }

    public void setSpeeds(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void importSettings(Settings _settings) {
        this.settings = _settings;
    }

    @Override
    public void robotInit() {
        logger = new Logger("TickTank", Logger.ATTR_DEFAULT);

        leftMotors = new ArrayList<SpeedController>();
        rightMotors = new ArrayList<SpeedController>();

        if (settings == null) {
            // This is really bad and will probably break everything.
            logger.warn("Settings not set. Using defaults.");
            settings = getDefaults();
        }

        for (int i = 0; i < settings.motorCount; i++) {
            // Alternate ports left and right. Left should be even numbers
            // and right should be odd. This is just the easiest way to
            // distribute the ports.
            int leftPort = i * 2;
            int rightPort = (i * 2) + 1;

            switch (settings.controllerType) {
                case VICTOR:
                    leftMotors.add(new Victor(leftPort));
                    rightMotors.add(new Victor(rightPort));
                    break;

                case TALON:
                    leftMotors.add(new Talon(leftPort));
                    rightMotors.add(new Talon(rightPort));
                    break;

                case JAGUAR:
                    leftMotors.add(new Jaguar(leftPort));
                    rightMotors.add(new Jaguar(rightPort));
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
