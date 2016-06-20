package frc.team236.ticktank;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.openrio.toast.lib.registry.Registrar;

public class TickTank extends Subsystem {
    public Joystick leftStick, rightStick;
    public ArrayList<SpeedController> leftMotors, rightMotors;
    public Settings config;

    /**
     * Generates a TickTank using the specified Settings object.
     *
     * @param _settings
     * The settings that define the TickTank object
     */
    public TickTank(Settings _settings) {
        this.config = _settings;
        int pwm = 0; //TODO Allow user to define available pwm ports

        leftMotors = makeMotors(config.motorCount,
                pwm,
                config.controllerType);

        rightMotors = makeMotors(config.motorCount,
                pwm+config.motorCount,
                config.controllerType);

        leftStick = config.leftStick;
        rightStick = config.rightStick;
    }

    /**
     * Generates a TickTank with the default values. DO NOT USE THIS
     * CONSTRUCTOR. You should always specify the parameters of the tank drive,
     * even if the defaults match your use case. This constructor exists purely
     * to avoid errors if you mess up.
     */
    public TickTank() {
        this(getDefaults());
    }

    @Override
    public void initDefaultCommand() {
    }

    private static Settings getDefaults() {
        Settings defaults = new Settings();
        defaults.setLeftStick(new Joystick(0));
        defaults.setRightStick(new Joystick(1));
        defaults.setCount(2);
        defaults.setControllerType(ControllerType.VICTOR);
        return defaults;
    }


    /**
     * Generate an array of motors.
     *
     * @param count
     * The number of motors on each side
     * @param pwmStartPort
     * The PWM port to begin at. Will occupy the next `count` ports.
     * @param type
     * The type of SpeedController to use
     * @return
     * An `ArrayList` of `count` motors assigned to the correct PWM ports
     * and type.
     */
    private static ArrayList<SpeedController> makeMotors(
            int count,
            int pwmStartPort,
            ControllerType type) {
        ArrayList<SpeedController> motors =
            new ArrayList<SpeedController>(count);

        for (int port = pwmStartPort;
                port < pwmStartPort + count;
                port++) {

            switch (type) {
                case VICTOR:
                    motors.add(Registrar.victor(port));
                    break;

                case TALON:
                    motors.add(Registrar.talon(port));
                    break;

                case JAGUAR:
                    motors.add(Registrar.jaguar(port));
                    break;
            }
        }

        return motors;
    }

    public void setLeftStick(Joystick stick) {
        this.leftStick = stick;
    }

    public void setRightStick(Joystick stick) {
        this.rightStick = stick;
    }

    public void setLeftSpeed(double speed) {
        if (config.leftInv) {
            speed *= -1;
        }

        for (SpeedController motor : leftMotors) {
            motor.set(speed);
        }
    }

    public void setRightSpeed(double speed) {
        if (config.rightInv) {
            speed *= -1;
        }

        for (SpeedController motor : rightMotors) {
            motor.set(speed);
        }
    }

    public void setSpeeds(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }
}
