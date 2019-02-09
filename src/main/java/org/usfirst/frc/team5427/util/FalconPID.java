package org.usfirst.frc.team5427.util;

public class FalconPID {

	private double kP, kI, kD, tolerance;

	private double ref;
	private double prevVal, prevD;
	private double errorSum;

	private	boolean isFirstCycle;
	private int cycleCount, minCycleCount;

	private boolean isContinuous;
	private double midPoint;
	private double minInput, maxInput;

	//Blank Constructor: Remember, The PID will not increment for the output
	public FalconPID(){
		this(0.0, 0.0, 0.0, 0.0);
	}

	//Constructor with no Tolerance
	public FalconPID(double p, double i, double d){
		this(p, i, d, 0.0);
	}

	//Complete Constructor
	public FalconPID(double p, double i, double d, double tolerance){
		this.kP = p;
		this.kI = i;
		this.kD = d;
		this.tolerance = tolerance;

		this.ref = 0.0; //setpoint
		this.isFirstCycle = true;

		this.cycleCount = 0;
		this.minCycleCount = 5;

		this.isContinuous = false;
		this.midPoint = 0.0;
		this.minInput = 0.0;
		this.maxInput = 0.0;
	}

	public void setTolerance(double t){ this.tolerance = t; }

	public void setReference(double ref){ this.ref = ref; }

	public void setMinCycleCount(int count){ this.minCycleCount = count; }

	/* Set Continuous makes it so that two points are calculated as the same point.
	 * The proportion is based off of the distance from the midpoint to the two points.
	 */

	public void setContinuous(double min, double max){
		midPoint = (max - min) / 2; //midpoint is the averages of the two values

		maxInput = max;
		minInput = min;

		isContinuous = true;
	}

	public void resetContinuous(){
		isContinuous = false;
		midPoint = 0.0;
		minInput = 0.0;
		maxInput = 0.0;
	}

	public void resetErrorSum(){ this.errorSum = 0.0; }

	public double getRef(){ return this.ref; }

	public double calcPID(double currentVal){
		//PID Error Values
		double pErr = 0.0;
		double iErr = 0.0;
		double dErr = 0.0;

		if(this.isFirstCycle){
			this.prevVal = currentVal;
			this.isFirstCycle = false;
		}

		//Calculate P
		/*Tells the output to try to match the desired value by setting the output equal 
		 * to the difference between the actual value and the setpoint
		 * */

		double error = this.ref - currentVal;

		if (isContinuous) {
			if (Math.abs(error) > midPoint) {
				if (error > 0) {
					error = error - maxInput + minInput;
				} else {
					error = error + maxInput - minInput;
				}
			}
		}

		pErr = this.kP * error;

		//Calculate I
		/*This is the "memory" part. So suppose the motors are going exactly as fast as you want it to be, then the proportion
		 * term is 0 so the output  becomes 0 */
		this.errorSum += error;
		iErr = this.kI * this.errorSum;

		//Calculate D
		//
		double delta = currentVal - this.prevVal;
		dErr = this.kD * delta;

		//Calculate Output
		double output = pErr + iErr + dErr;

		this.prevVal = currentVal;

		return output;

	}

	public double calcPIDIncrement(double currentVal){
		//PID Error Values
		double pErr = 0.0;
		double iErr = 0.0;
		double dErr = 0.0;

		if(this.isFirstCycle){
			this.prevVal = currentVal;
			this.isFirstCycle = false;
		}

		//Calculate P
		double delta = currentVal - this.prevVal;
		pErr = this.kP * delta;

		//Calculate I

		double error = this.ref - currentVal;
		if (isContinuous) {
			if (Math.abs(error) > midPoint) {
				if (error > 0) {
					error = error - maxInput + minInput;
				} else {
					error = error + maxInput - minInput;
				}
			}
		}

		iErr = this.kI * error;

		//Calculate D
		double dDelta = delta - this.prevD;
		dErr = this.kD * dDelta;

		//Calculate Output
		double output = pErr + iErr + dErr;

		this.prevVal = currentVal;

		return output;

	}

	public boolean isDone(){

		double currentError = Math.abs(this.ref - this.prevVal);
		// System.out.println(currentError);
		//If close to target
		if(currentError <= this.tolerance){
			this.cycleCount ++;
		} else{
			this.cycleCount = 0; //Restart the process
		}		

		return this.cycleCount > this.minCycleCount;

	}

	public void resetPrevious(){
		this.prevD = 0.0;
		this.prevVal = 0.0;
	}

}