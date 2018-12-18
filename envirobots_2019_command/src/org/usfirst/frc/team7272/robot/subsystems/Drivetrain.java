package org.usfirst.frc.team7272.robot.subsystems;

import org.usfirst.frc.team7272.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {

	DifferentialDrive drive = RobotMap.drive;
	Encoder encoder_left = RobotMap.encoder_left;
	Encoder encoder_right = RobotMap.encoder_right;

	public Drivetrain() {
    	//Based on wheel measurements, our wheels move forward a certain distance in one rotation, many pulses per rotation (math)
		//The encoder specs tell you how many pulses are in a single rotation.
    	encoder_left.setDistancePerPulse(4);
    	encoder_right.setDistancePerPulse(4);
	}

    public void drive(double leftSpeed, double rightSpeed, double leftDistance, double rightDistance) {
    	//Reset previous distance recordings
    	encoder_left.reset();
    	encoder_right.reset();
    	
    	double currentLeftSpeed = leftSpeed;    	
    	double currentRightSpeed = rightSpeed;

    	while(encoder_left.getDistance() < leftDistance && encoder_right.getDistance() < rightDistance) {
    		//if left side has gone distance, stop
    		if(encoder_left.getDistance() >= leftDistance) {
    			currentLeftSpeed = 0;
    		}
    		
    		//if right side has gone distance, stop
    		if(encoder_right.getDistance() >= rightDistance) {
    			currentRightSpeed = 0;
    		}
    		
    		drive.tankDrive(currentLeftSpeed, currentRightSpeed);
    	}
    	
    	//Make sure robot is stopped at the end.
		drive.tankDrive(0, 0);
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

