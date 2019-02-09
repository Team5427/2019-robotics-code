package org.usfirst.frc.team5427.Networking;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * The object that will be sent from the driver station and will be received by
 * the robot. This class only contains data that may be useful for the robot.
 */
public class GoalData implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Distance between the robot and the goal. The value is
	 */
	private double distance;

	/**
	 * The horizontal angle from the camera to the
	 */
	private double horizontalAngle;


	public GoalData(double distance, double horiztonalAngle) {
		this.distance = distance;
		this.horizontalAngle = horiztonalAngle;
	}

	public GoalData(byte[] buff) {
		setByteBuffer(buff);
	}

	/**
	 * TODO: Change hardcode of index 1 to use with ByteDictionary
	 *
	 * Scans the received byte buffer. If the data can be used and is
	 * successfully set accordingly, then this method will return true. If the
	 * byte array is scanned to be an incorrect type as indicated in the
	 * ByteDictionary for a goal data, then the method will return false.
	 *
	 * The buffer is required to have a size of 17 (index 0 for type, 1-8 for
	 * speed, and 9-16 for the x angle).
	 *
	 * @param buff
	 *            array to be used for setting the data
	 * @return true if data is valid and set according. False if otherwise
	 */
	public boolean setByteBuffer(byte[] buff) {
		if (buff[0] == 1) {
			distance = ByteBuffer.wrap(buff, 1, 8).getDouble();
			horizontalAngle = ByteBuffer.wrap(buff, 9, 8).getDouble();
		}

		return false;
	}

	/**
	 * TODO, temp remove when working
	 * 
	 * @return
	 */
	public String printBuff(byte[] buff) {

		String s = "";

		for (byte b : buff) {
			s += b + ", ";
		}
		return s;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getHorizontalAngle() {
		return horizontalAngle;
	}

	public void setHorizontalAngle(double horizontalAngle) {
		this.horizontalAngle = horizontalAngle;
	}


	/**
	 * The toString use by the network to identify the class type
	 *
	 * @return the class type for networking use
	 */
	public String toString() {
		return "Team 5427 - GoalData " + distance + "  " + Math.toDegrees(horizontalAngle);
	}
}