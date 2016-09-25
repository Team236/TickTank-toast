package frc.team236.ticktank.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team236.ticktank.TickTank;

public class ShiftUp extends Command {

	TickTank tank;
	
	public ShiftUp(TickTank _tank) {
		this.tank = _tank;
	}

	@Override
	protected void initialize() {
		tank.shiftUp();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
