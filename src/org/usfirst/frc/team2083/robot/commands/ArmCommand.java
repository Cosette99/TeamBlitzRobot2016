


package org.usfirst.frc.team2083.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2083.robot.Robot;
import org.usfirst.frc.team2083.robot.RobotMap;

public class  ArmCommand extends CommandBase {
	public static Joystick xbox;
	public static double speedMultiplier = .5;
	//public double position = 0;

	public ArmCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(armBar);
		armBar.setVoltage(0);
		//position = fourBar.getController().getAnalogInPosition();
	}

	public void enableControl() {
		armBar.enableControl();
	}

	public void disableControl() {
		armBar.disableControl();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double Y;
		Y = -xbox.getRawAxis(5);			//Set Y to the value of the XBox's 5th axis (The vertical axises of the right analog stick)
		if (Math.abs(Y) < 0.1) Y = 0;		//Create a deadzone of .1
		Y = Y*Math.abs(Y)*speedMultiplier;	//Multiply Y by itself and then by speedMultiplier
								//position += 2 * Y;
								//double curpos = armBar.getController().getAnalogInPosition();
								//System.out.println("Y = " + Y + ", rY = " + rY + ", curpos = " + curpos);
		armBar.setVoltage(Y);
 

		
//		int numAxes = xbox.getAxisCount();
//		double[] axisValues = new double[numAxes];
//		String toPrint = "numAxes = " + numAxes;
//		for (int i=0; i < numAxes; i++) {
//			axisValues[i] = xbox.getRawAxis(i);
//			toPrint += ", value " + i + " = " + axisValues[i];
//		}
		//		System.out.println("LT = " + LT + ", RT = " + RT + ", val = " + val );
//		System.out.println(toPrint);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}	
