package frc.team236.ticktank.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team236.pid.PID;
import frc.team236.ticktank.Direction;
import frc.team236.ticktank.RobotModule;
import frc.team236.ticktank.TickTank;
import frc.team236.ticktock.Ticker;

public class Turn extends Command {

	private TickTank tank;

	private double setPoint; // The heading we are going to
	private double degrees;
	private double error; // The current distance we still have to go

	private PID controller;
	private Ticker ticker;

	public Turn(TickTank _tank, double _degrees, Direction _dir) {
		if (_dir == Direction.LEFT || _dir == Direction.CCW) {
			degrees = _degrees * -1;
		}
		this.tank = _tank;
		controller = new PID(tank, tank, tank.turnParams);
		ticker = new Ticker(controller, tank.turnParams.interval);

	}

	@Override
	protected void initialize() {
		if (tank.navx == null) {
			RobotModule.logger.severe("No gyro in use");
			end();
		}
		this.setPoint = tank.navx.getAngle() + degrees;

		controller.setSetpoint(setPoint);
		ticker.start();
	}

	@Override
	protected void execute() {
		error = setPoint - tank.navx.getAngle();
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(error) < 10);
	}

	@Override
	protected void end() {
		tank.stop();
		ticker.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
