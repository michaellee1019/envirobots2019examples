/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7272.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private int auto_step = 1;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	//Drive control for robot
    DifferentialDrive m_drive;

    Encoder encoder_left;
    Encoder encoder_right;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		Spark m_frontLeft = new Spark(RobotMap.FRONT_LEFT_MOTOR);
		Spark m_rearLeft = new Spark(RobotMap.BACK_LEFT_MOTOR);
		SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

		Spark m_frontRight = new Spark(RobotMap.FRONT_RIGHT_MOTOR);
		Spark m_rearRight = new Spark(RobotMap.BACK_RIGHT_MOTOR);
		SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

		m_drive = new DifferentialDrive(m_left, m_right);
		
		DigitalSource ds_encoder_left_1 = new DigitalInput(RobotMap.ENCODER_LEFT_1);
		DigitalSource ds_encoder_left_2 = new DigitalInput(RobotMap.ENCODER_LEFT_2);

		DigitalSource ds_encoder_right_1 = new DigitalInput(RobotMap.ENCODER_RIGHT_1);
		DigitalSource ds_encoder_right_2 = new DigitalInput(RobotMap.ENCODER_RIGHT_2);

		
		encoder_left = new Encoder(ds_encoder_left_1, ds_encoder_left_2);
		encoder_right = new Encoder(ds_encoder_right_1, ds_encoder_right_2);
		
		//Based on wheel measurements, our wheels move forward a certain distance in one rotation, many pulses per rotation (math)
		//The encoder specs tell you how many pulses are in a single rotation.
		encoder_left.setDistancePerPulse(4);
		encoder_right.setDistancePerPulse(4);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous. every 20 ms
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				encoder_left.reset();
				encoder_right.reset();
				
				//drive forward 10 ft
				
				//1. go forward 1/2 speed on both left & right sides.
				if(auto_step == 1) {
					doStep1();
				}
				
				encoder_left.reset();
				encoder_right.reset();
				
				//2. turn right 90deg
				//How many feet does it take to go 90deg? (math)
				//repetitive code can be put into its own method
				if(auto_step == 2) {
					doStep2();
				}
				
				break;
		}
	}

	private void doStep1() {
		double left_current_speed = .5;
		double right_current_speed = .5;
		//stop left when 10ft
		if(encoder_left.getDistance() >= 10) {
			left_current_speed = 0;
		}
		
		//stop right when 10ft
		if(encoder_right.getDistance() >= 10) {
			right_current_speed = 0;
		}
		
		m_drive.tankDrive(left_current_speed, right_current_speed);
		
		//if both left and right are done you proceed to step 2
		auto_step = 2;
	}
	
	private void doStep2() {
		double left_current_speed = -.5;
		double right_current_speed = .5;
		
		//stop left when 10ft
		if(encoder_left.getDistance() >= 10) {
			left_current_speed = 0;
		}
		
		//stop right when 10ft
		if(encoder_right.getDistance() >= 10) {
			right_current_speed = 0;
		}
		
		//need this?
		m_drive.tankDrive(left_current_speed, right_current_speed);
		
		//if both left and right are done you proceed to step 2
		auto_step = 3;
	}
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
