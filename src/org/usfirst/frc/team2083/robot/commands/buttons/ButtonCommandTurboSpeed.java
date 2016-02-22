package org.usfirst.frc.team2083.robot.commands.buttons;

import org.usfirst.frc.team2083.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ButtonCommandTurboSpeed extends CommandGroup {
    
    public  ButtonCommandTurboSpeed() {    	
    	requires(CommandBase.leftDrive);
    	requires(CommandBase.rightDrive);
    	requires(CommandBase.armBar);
    	
       	addSequential(new TeleDriveCommand(1.5));			//Set the speed multiplier to 150%
    }
}
