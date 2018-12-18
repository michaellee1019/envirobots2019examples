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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	private static int FRONT_LEFT_MOTOR = 1;
	private static int FRONT_RIGHT_MOTOR = 2;
	private static int BACK_LEFT_MOTOR = 3;
	private static int BACK_RIGHT_MOTOR = 4;

	//Rotary encoder ports
	private static int ENCODER_LEFT_1 = 5;
	private static int ENCODER_LEFT_2 = 6;

	private static int ENCODER_RIGHT_1 = 7;
	private static int ENCODER_RIGHT_2 = 8;
	
	private static Spark m_frontLeft = new Spark(RobotMap.FRONT_LEFT_MOTOR);
	private static Spark m_rearLeft = new Spark(RobotMap.BACK_LEFT_MOTOR);
	private static SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

	private static Spark m_frontRight = new Spark(RobotMap.FRONT_RIGHT_MOTOR);
	private static Spark m_rearRight = new Spark(RobotMap.BACK_RIGHT_MOTOR);
	private static SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

	public static DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
	
	private static DigitalSource ds_encoder_left_1 = new DigitalInput(RobotMap.ENCODER_LEFT_1);
	private static DigitalSource ds_encoder_left_2 = new DigitalInput(RobotMap.ENCODER_LEFT_2);

	private static DigitalSource ds_encoder_right_1 = new DigitalInput(RobotMap.ENCODER_RIGHT_1);
	private static DigitalSource ds_encoder_right_2 = new DigitalInput(RobotMap.ENCODER_RIGHT_2);

	public static Encoder encoder_left = new Encoder(ds_encoder_left_1, ds_encoder_left_2);
	public static Encoder encoder_right = new Encoder(ds_encoder_right_1, ds_encoder_right_2);
}
