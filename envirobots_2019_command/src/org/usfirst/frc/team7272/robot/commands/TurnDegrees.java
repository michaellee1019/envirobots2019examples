package org.usfirst.frc.team7272.robot.commands;

import org.usfirst.frc.team7272.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	private boolean isFinished = false;
	
	private final double speed;
	private final double degree;

	
    public TurnDegrees(final double speed, final double degree) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.speed = speed;
    	this.degree = degree;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//TODO equation to calculate degree of turning based on distance detected by encoder (math)
    	final double distance = degree * 1.75 * Math.PI;
    	
    	//If degree is negative, then make left wheel go backwards
    	if(degree < 0) {
    		Robot.drivetrain.drive(speed * -1, speed, distance, distance);
    	}
    	//Otherwise make right go backwards
    	else {
    		Robot.drivetrain.drive(speed, speed * -1, distance, distance);
    	}
    	isFinished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
