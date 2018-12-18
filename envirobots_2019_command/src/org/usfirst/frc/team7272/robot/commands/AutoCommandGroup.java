package org.usfirst.frc.team7272.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {

    public AutoCommandGroup() {
    	//drive straight 75% speed for 10ft
    	addSequential(new DriveStraight(.75, 10));
    	//Turn 90 degrees to the right
    	addSequential(new TurnDegrees(.20, 90));
    	//drive straight 25% speed for 5ft
    	addSequential(new DriveStraight(.25, 10));
    	
    	//do two things at the same time
    	//addParallel(new LowerRamp());
    	//addParallel(new RaiseArm());
    	
    	//Drive straight after the two parallel tasks have completed
    	addSequential(new DriveStraight(.4, 100));
    }
}
