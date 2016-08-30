package frc.team236.ticktank.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team236.ticktank.DriveSide;
import frc.team236.ticktank.TickTank;
import frc.team236.ticktank.motionProfile.Profile;
import frc.team236.ticktank.motionProfile.ProfileFollower;

/**
 *
 */
public class FollowProfile extends Command {

	Profile leftProfile, rightProfile;
	ProfileFollower leftFollower, rightFollower;
	DriveSide leftSide, rightSide;
	boolean isInverted;
	TickTank tank;

	public FollowProfile(TickTank _tank, Profile bothSides, boolean isInverted) {
		this(_tank, bothSides, bothSides, isInverted);
	}

	public FollowProfile(TickTank _tank, Profile _left, Profile _right, boolean _isInverted) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(tank);

		this.leftProfile = _left;
		this.rightProfile = _right;

		this.leftSide = tank.left;
		this.rightSide = tank.right;

		this.isInverted = _isInverted;

		if (leftProfile == null || rightProfile == null) {
			System.out.println("Null profile(s)");
		} else {
			leftFollower = new ProfileFollower(leftProfile, leftSide, leftSide, tank.driveParams, isInverted);
			rightFollower = new ProfileFollower(rightProfile, rightSide, rightSide, tank.driveParams, isInverted);
		}
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		tank.zeroEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// TODO
		if (leftFollower.error < .1 && rightFollower.error < .1) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		leftFollower.isEnabled = false;
		rightFollower.isEnabled = false;
		tank.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		leftFollower.isEnabled = false;
		rightFollower.isEnabled = false;
	}
}
