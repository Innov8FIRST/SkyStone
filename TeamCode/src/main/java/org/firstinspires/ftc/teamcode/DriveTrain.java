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
    HardwareInnov8Dobby robot = new HardwareInnov8Dobby();   // Use a Innov8's hardware



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
    public DriveTrain(Telemetry telemetry){

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
        robot.imu.initialize(parameters);
        angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("first", angles.firstAngle);
        telemetry.addData("second", angles.secondAngle);
        telemetry.addData("third", angles.thirdAngle);
        telemetry.addData("counter", counter);
        telemetry.update();
        this.telemetry = telemetry;
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train initialized");
        this.telemetry.update();
    }

    public void goForward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving forward");
        startPosition = robot.motorOne.getCurrentPosition();
        endPosition = startPosition + (inches * inchToTick); // How far you need to travel
        while (robot.motorOne.getCurrentPosition() < endPosition) {
            robot.motorOne.setPower(wheelPower);
            robot.motorTwo.setPower(wheelPower);
            robot.motorThree.setPower(wheelPower);
            robot.motorFour.setPower(wheelPower);
        }
        this.telemetry.update();
    }
    public void goBackward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving backwards");
        startPosition = robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * inchToTick); // How far you need to travel
        while (robot.motorOne.getCurrentPosition() > endPosition) {
            robot.motorOne.setPower(-wheelPower);
            robot.motorTwo.setPower(-wheelPower);
            robot.motorThree.setPower(-wheelPower);
            robot.motorFour.setPower(-wheelPower);
        }
        this.telemetry.update();
    }
    public void goRightToLine(boolean isRed){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
        if (isRed == true) { // If we're on red alliance
            while (robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
                robot.motorOne.setPower(wheelPower); // May need to turn other direct
                robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
                robot.motorThree.setPower(-wheelPower);
                robot.motorFour.setPower(wheelPower);
            }
        }
        if (isRed == false) { // If we're on blue alliance
            while (robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
                robot.motorOne.setPower(wheelPower); // May need to turn other direct
                robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
                robot.motorThree.setPower(-wheelPower);
                robot.motorFour.setPower(wheelPower);
            }
        }
        this.telemetry.update();
    }
    public void goLeftToLine(boolean isRed){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
        if (isRed == true) { // If we're on red alliance
            while (robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
                robot.motorOne.setPower(-wheelPower);
                robot.motorTwo.setPower(wheelPower);
                robot.motorThree.setPower(wheelPower);
                robot.motorFour.setPower(-wheelPower);
            }
        }
        if (isRed == false) { // If we're on blue alliance
            while (robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
                robot.motorOne.setPower(-wheelPower);
                robot.motorTwo.setPower(wheelPower);
                robot.motorThree.setPower(wheelPower);
                robot.motorFour.setPower(-wheelPower);
            }
        }
        this.telemetry.update();
    }
    public void turn(double degreesToTurn){
        robot.imu.initialize(parameters);
        angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        if (degreesToTurn < 0) {
            while (angles.firstAngle > degreesToTurn) {
                robot.motorOne.setPower(wheelPower);
                robot.motorTwo.setPower(wheelPower);
                robot.motorThree.setPower(-wheelPower);
                robot.motorFour.setPower(-wheelPower);
                angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("angles", angles.firstAngle);
                telemetry.addData("degreesToTurn", degreesToTurn);
                telemetry.update();
            }
        }
        else {
            while (angles.firstAngle < degreesToTurn) {
                robot.motorOne.setPower(-wheelPower);
                robot.motorTwo.setPower(-wheelPower);
                robot.motorThree.setPower(wheelPower);
                robot.motorFour.setPower(wheelPower);
                angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
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
        startPosition = robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * sideInchToTick); // How far you need to travel
        while (robot.motorOne.getCurrentPosition() > endPosition) {
            robot.motorOne.setPower(-wheelPower);
            robot.motorTwo.setPower(wheelPower);
            robot.motorThree.setPower(wheelPower); // May need to go other direction
            robot.motorFour.setPower(-wheelPower); // May need to go other direction
        }
        this.telemetry.update();
    }

    public void goRight(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is moving right");
        startPosition = robot.motorFour.getCurrentPosition();
        endPosition = startPosition - (inches * sideInchToTick); // How far you need to travel
        while (robot.motorFour.getCurrentPosition() > endPosition) {
            robot.motorOne.setPower(wheelPower); // May need to turn other direct
            robot.motorTwo.setPower(-wheelPower); // May need to turn other direction
            robot.motorThree.setPower(-wheelPower);
            robot.motorFour.setPower(wheelPower);
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
        this.telemetry.update();
    }
}
