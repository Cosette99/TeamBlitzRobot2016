
package org.usfirst.frc2083.TeamBlitzRobot2016.commands;

import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc2083.TeamBlitzRobot2016.RobotMap;

/**
 *
 * @author bradmiller
 */
public class DriveCommand extends CommandBase {
    
    public static Joystick xbox;

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(leftDrive);
        requires(rightDrive);
    }
    
    public void enableControl() {
        leftDrive.enable();
        rightDrive.enable();
        leftDrive.enableControl();
        rightDrive.enableControl();
    }
    public void disableControl() {
        leftDrive.disable();
        rightDrive.disable();
        leftDrive.disableControl();
        rightDrive.disableControl();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double X = 0, Y = 0;							// 		<Begin code that runs during teleop> 	//

    	if (RobotMap.auto) {							//If the robot is in autonomous mode,
    		Y = RobotMap.autoY;							//then set the robot's Y (forward drive?) to autoY

    	} else {										//Otherwise,
    		X = xbox.getX();							//Get the controller's analog stick's X position and label that the variable X
    		//    	System.out.println("X = " + X);
    		Y = -xbox.getY(); //-xbox.getRawAxis(5);	//Get the negative of the controller's analog stick's Y position and label that the variable Y
    		//    	System.out.println("Y = " + Y);
    		if (Math.abs(X) < 0.1) X = 0;				//Create a deadzone (values which don't affect the motor) of .1 in the X value
    		if (Math.abs(Y) < 0.1) Y = 0;				//Same as above, but with the Y value replacing the X variable.
    		X = X*Math.abs(X);							//Multiply X by the absouloute value of X and set it X
    		Y = Y*Math.abs(Y)*.5;						//Multiply Y by the absoloute value of Y, then multiply by .5 and label it Y
    	}

    	System.out.println("Y = " + Y);					//Print "Y = " and the variable Y in the output
    	leftDrive.setSetpoint(Y*360+X*240);				//Set the leftDrive variable to Y * 360 and X * 240
        rightDrive.setSetpoint(Y*360-X*240);			//Do the same as the above, but with rightDrive instead of leftDrive
        
    }													// 		<End code that runs during teleop> 		//

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
