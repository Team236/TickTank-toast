package frc.team236.ticktank;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Contains all of the configuration variables for the tank drive. You should
 * create an instance of this class in the main robot init, load it up with
 * objects using the set methods, then pass it on to the Drive module.
 */
public class Settings {
	// Poorly named class
	public Joystick leftStick, rightStick;
	public int motorCount;
	public ControllerType controllerType;
	public boolean leftInv, rightInv;
	public int leftEncoderA, leftEncoderB;
	public int rightEncoderA, rightEncoderB;

	public Settings() {
		leftInv = false;
		rightInv = false;
	}

	public void setLeftStick(Joystick stick) {
		this.leftStick = stick;
	}

	public void setRightStick(Joystick stick) {
		this.rightStick = stick;
	}

	public void setSticks(Joystick left, Joystick right) {
		this.setLeftStick(left);
		this.setRightStick(right);
	}

	public void setCount(int _count) {
		this.motorCount = _count;
	}

	public void invertLeftMotors() {
		leftInv = true;
	}

	public void invertRightMotors() {
		rightInv = true;
	}

	public void setLeftEncoderPort(int a, int b) {
		this.leftEncoderA = a;
		this.leftEncoderB = b;
	}

	public void setRightEncoderPort(int a, int b) {
		this.rightEncoderA = a;
		this.rightEncoderB = b;
	}

	/**
	 * Set the type of SpeedController being used by providing an example
	 * SpeedController.
	 *
	 * @param example
	 *            An instance of a SpeedController of the type to be used
	 */
	public void setControllerType(ControllerType type) {
		this.controllerType = type;
	}
}
