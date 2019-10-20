package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class DriveTrain {
    String DRIVE_TRAIN_CAPTION = "Drive Status";
    Telemetry telemetry;
    HardwareInnov8Dobby robot;


    double wheelPower = 0.7; // The standard power for the wheels, will probably be changed later
    double inchToTick = 28.65; // The number of encoder ticks per inch for our wheels, currently from google
    double sideInchToTick = 28.65; // The number of encoder ticks for one inch while travelling sideways, change later
    double startPosition = 0;
    double endPosition = 0;
    double redLine = 0;  // One of the color sensor readings for the red line, definitely change later
    double blueLine = 0; // Same as above but for the blue line
    int counter = 0;
    Orientation angles;
    BNO055IMU.Parameters parameters;
    public DriveTrain(Telemetry telemetry, HardwareInnov8Dobby robot){

        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

// Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
// on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
// and named "imu".
        this.robot = robot;
        this.robot.imu.initialize(parameters);
        angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        this.telemetry = telemetry;
        this.telemetry.addData("first", angles.firstAngle);
        this.telemetry.addData("second", angles.secondAngle);
        this.telemetry.addData("third", angles.thirdAngle);
        this.telemetry.addData("counter", counter);
        this.telemetry.update();
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train initialized");
        this.telemetry.update();
    }

    public void goForward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving forward");
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition + (inches * inchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() < endPosition) {
            this.robot.motorOne.setPower(wheelPower);
            this.robot.motorTwo.setPower(wheelPower);
            this.robot.motorThree.setPower(wheelPower);
            this.robot.motorFour.setPower(wheelPower);
        }
        this.telemetry.update();
    }
    public void goBackward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving backwards");
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * inchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() > endPosition) {
            this.robot.motorOne.setPower(-wheelPower);
            this.robot.motorTwo.setPower(-wheelPower);
            this.robot.motorThree.setPower(-wheelPower);
            this.robot.motorFour.setPower(-wheelPower);
        }
        this.telemetry.update();
    }
    public void goRightToLine(boolean isRed){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
        if (isRed == true) { // If we're on red alliance
            while (this.robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
                this.robot.motorOne.setPower(wheelPower); // May need to turn other direct
                this.robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
                this.robot.motorThree.setPower(-wheelPower);
                this.robot.motorFour.setPower(wheelPower);
            }
        }
        if (isRed == false) { // If we're on blue alliance
            while (this.robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
                this.robot.motorOne.setPower(wheelPower); // May need to turn other direct
                this.robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
                this.robot.motorThree.setPower(-wheelPower);
                this.robot.motorFour.setPower(wheelPower);
            }
        }
        this.telemetry.update();
    }
    public void goLeftToLine(boolean isRed){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
        if (isRed == true) { // If we're on red alliance
            while (this.robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
                this.robot.motorOne.setPower(-wheelPower);
                this.robot.motorTwo.setPower(wheelPower);
                this.robot.motorThree.setPower(wheelPower);
                this.robot.motorFour.setPower(-wheelPower);
            }
        }
        if (isRed == false) { // If we're on blue alliance
            while (this.robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
                this.robot.motorOne.setPower(-wheelPower);
                this.robot.motorTwo.setPower(wheelPower);
                this.robot.motorThree.setPower(wheelPower);
                this.robot.motorFour.setPower(-wheelPower);
            }
        }
        this.telemetry.update();
    }

    /**
     *
     * @param degreesToTurn Number of degrees to turn. If negative, turns right. If positive, turns left.
     */
    public void turn(double degreesToTurn){
        this.robot.imu.initialize(parameters);
        angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        if (degreesToTurn < 0) {
            while (angles.firstAngle > degreesToTurn) {
                this.robot.motorOne.setPower(wheelPower);
                this.robot.motorTwo.setPower(wheelPower);
                this.robot.motorThree.setPower(-wheelPower);
                this.robot.motorFour.setPower(-wheelPower);
                angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("angles", angles.firstAngle);
                telemetry.addData("degreesToTurn", degreesToTurn);
                telemetry.update();
            }
        }
        else {
            while (angles.firstAngle < degreesToTurn) {
                this.robot.motorOne.setPower(-wheelPower);
                this.robot.motorTwo.setPower(-wheelPower);
                this.robot.motorThree.setPower(wheelPower);
                this.robot.motorFour.setPower(wheelPower);
                angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("angles", angles.firstAngle);
                telemetry.addData("degreesToTurn", degreesToTurn);
                telemetry.update();
            }
        }
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is turning");
        this.telemetry.update();
    }
    public void goLeft(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is moving left");
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * sideInchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() > endPosition) {
            this.robot.motorOne.setPower(-wheelPower);
            this.robot.motorTwo.setPower(wheelPower);
            this.robot.motorThree.setPower(wheelPower); // May need to go other direction
            this.robot.motorFour.setPower(-wheelPower); // May need to go other direction
        }
        this.telemetry.update();
    }

    public void goRight(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is moving right");
        startPosition = this.robot.motorFour.getCurrentPosition();
        endPosition = startPosition - (inches * sideInchToTick); // How far you need to travel
        while (this.robot.motorFour.getCurrentPosition() > endPosition) {
            this.robot.motorOne.setPower(wheelPower); // May need to turn other direct
            this.robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
            this.robot.motorThree.setPower(-wheelPower);
            this.robot.motorFour.setPower(wheelPower);
        }
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Drive train is stopped");
        this.telemetry.update();
    }
    public void setPower(double powerLevel){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Drive train power set");
        wheelPower = powerLevel;
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "gamepad updated");
        while (Math.abs(gamepad1.left_stick_y) > 0.2) {
            wheelPower = gamepad1.left_stick_y;
            this.robot.motorOne.setPower(wheelPower);
            this.robot.motorTwo.setPower(wheelPower);
            this.robot.motorThree.setPower(wheelPower);
            this.robot.motorFour.setPower(wheelPower);
        }
        while (Math.abs(gamepad1.left_stick_x) > 0.5) {
            if (gamepad1.left_stick_x > 0) {
                wheelPower = gamepad1.left_stick_x;
                this.robot.motorOne.setPower(wheelPower); // May need to turn other direct
                this.robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
                this.robot.motorThree.setPower(-wheelPower);
                this.robot.motorFour.setPower(wheelPower);
            }
            else {
                wheelPower = Math.abs(gamepad1.left_stick_x);
                this.robot.motorOne.setPower(-wheelPower); // May need to turn other direct
                this.robot.motorTwo.setPower(wheelPower); // May need to turn other direction
                this.robot.motorThree.setPower(wheelPower);
                this.robot.motorFour.setPower(-wheelPower);
            }
        }
        this.telemetry.update();
    }
}
