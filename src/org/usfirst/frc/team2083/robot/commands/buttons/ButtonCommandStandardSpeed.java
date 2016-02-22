package org.usfirst.frc.team2083.robot.commands.buttons;

import org.usfirst.frc.team2083.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ButtonCommandStandardSpeed extends CommandGroup {
    
    public  ButtonCommandStandardSpeed() {    	
    	requires(CommandBase.leftDrive);
    	requires(CommandBase.rightDrive);
    	requires(CommandBase.armBar);
    	
       	addSequential(new TeleDriveCommand(1));				//Set the speed multiplier to normal (100%)
    }
}
