package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    LinearOpMode opMode;


    double wheelOnePower = 0.6325; // 0.55 // The standard power for the wheels, will probably be changed later
    double wheelTwoPower = 0.5; // 0.7
    double wheelThreePower = 0.4; //0.35
    double wheelFourPower = 0.23; // 0.2
    double wheelSpeedChanger = 1.1;
    double inchToTick = (360/6); // The number of encoder ticks per inch for our wheels
    double sideInchToTick = (360/6); // The number of encoder ticks for one inch while travelling sideways, change later
    double startPosition = 0;
    double endPosition = 0;
    double redLine = 0;  // One of the color sensor readings for the red line, definitely change later
    double blueLine = 0; // Same as above but for the blue line
    int counter = 0;
    Orientation angles;
    BNO055IMU.Parameters parameters;

    public DriveTrain(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode) {

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
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        this.robot.imu.initialize(parameters);
        angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        this.telemetry.addData("first", angles.firstAngle);
        this.telemetry.addData("second", angles.secondAngle);
        this.telemetry.addData("third", angles.thirdAngle);
        this.telemetry.addData("counter", counter);
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train initialized");
        this.telemetry.update();
    }

    public void goForward(double inches) {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving forward");
        this.telemetry.addData("wheel power", this.robot.motorOne.getPower());
        this.telemetry.update();
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition + (inches * inchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() < endPosition && this.opMode.opModeIsActive()) {
            this.telemetry.addData("StartPosition", startPosition);
            this.telemetry.addData("EndPosition", endPosition);
            this.telemetry.addData("CurrentPosition", this.robot.motorOne.getCurrentPosition());
            this.robot.motorOne.setPower(wheelOnePower);
            this.robot.motorTwo.setPower(wheelTwoPower);
            this.robot.motorThree.setPower((wheelThreePower));
            this.robot.motorFour.setPower(wheelFourPower);
            Log.d("wheel one power", "" + this.robot.motorOne.getPower());
            Log.d("wheel two power", "" + this.robot.motorTwo.getPower());
            Log.d("wheel three power", "" + this.robot.motorThree.getPower());
            Log.d("wheel four power", "" + this.robot.motorFour.getPower());
            Log.d("CurrentPosition", "" + this.robot.motorOne.getCurrentPosition());

            this.telemetry.update();
        }
        this.stop();
        this.telemetry.update();
    }

    public void ForwardAndRapOut(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving forward");
        this.telemetry.addData("wheel power", this.robot.motorOne.getPower());
        this.telemetry.update();
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition + (inches * inchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() < endPosition && this.opMode.opModeIsActive()) {
            if(this.robot.motorOne.getCurrentPosition() < startPosition + 750){
                this.robot.rapServoLeft.setPower(-1);
                this.robot.rapServoRight.setPower(1);
            }
            else{
                this.robot.rapServoLeft.setPower(0);
                this.robot.rapServoRight.setPower(0);
            }
            this.telemetry.addData("StartPosition", startPosition);
            this.telemetry.addData("EndPosition", endPosition);
            this.telemetry.addData("CurrentPosition", this.robot.motorOne.getCurrentPosition());
            this.robot.motorOne.setPower(wheelOnePower);
            this.robot.motorTwo.setPower(wheelTwoPower);
            this.robot.motorThree.setPower((wheelThreePower));
            this.robot.motorFour.setPower(wheelFourPower);
            Log.d("wheel one power", "" + this.robot.motorOne.getPower());
            Log.d("wheel two power", "" + this.robot.motorTwo.getPower());
            Log.d("wheel three power", "" + this.robot.motorThree.getPower());
            Log.d("wheel four power", "" + this.robot.motorFour.getPower());
            Log.d("CurrentPosition", "" + this.robot.motorOne.getCurrentPosition());

            this.telemetry.update();
        }
        this.robot.rapServoLeft.setPower(0);
        this.robot.rapServoRight.setPower(0);
        this.stop();
        this.telemetry.update();
    }

    public void goBackward(double inches) {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving backwards");
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * inchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() > endPosition && this.opMode.opModeIsActive()) {
            this.robot.motorOne.setPower(-wheelOnePower);
            this.robot.motorTwo.setPower(-wheelTwoPower);
            this.robot.motorThree.setPower(-wheelThreePower);
            this.robot.motorFour.setPower(-wheelFourPower);
        }
        this.stop();
        this.telemetry.update();
    }

//    public void goRightToLine(boolean isRed) {
//        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
//        if (isRed == true) { // If we're on red alliance
//            while (this.robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
//                this.robot.motorOne.setPower(wheelOnePower); // May need to turn other direct
//                this.robot.motorTwo.setPower(-wheelTwoPower); // May need to turn other direction
//                this.robot.motorThree.setPower(-wheelThreePower);
//                this.robot.motorFour.setPower(wheelFourPower);
//            }
//        }
//        if (isRed == false) { // If we're on blue alliance
//            while (this.robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
//                this.robot.motorOne.setPower(wheelOnePower); // May need to turn other direct
//                this.robot.motorTwo.setPower(-wheelTwoPower); // May need to turn other direction
//                this.robot.motorThree.setPower(-wheelThreePower);
//                this.robot.motorFour.setPower(wheelFourPower);
//            }
//        }
//        this.stop();
//        this.telemetry.update();
//    }

//    public void goLeftToLine(boolean isRed) {
//        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
//        if (isRed == true) { // If we're on red alliance
//            while (this.robot.leftSensor.red() != redLine) { //Assumes we choose to look at the red value to determine line color
//                this.robot.motorOne.setPower(-wheelOnePower);
//                this.robot.motorTwo.setPower(wheelTwoPower);
//                this.robot.motorThree.setPower(wheelThreePower);
//                this.robot.motorFour.setPower(-wheelFourPower);
//            }
//        }
//        if (isRed == false) { // If we're on blue alliance
//            while (this.robot.leftSensor.blue() != blueLine) { //Assumes we choose to look at the blue value to determine line color
//                this.robot.motorOne.setPower(-wheelOnePower);
//                this.robot.motorTwo.setPower(wheelTwoPower);
//                this.robot.motorThree.setPower(wheelThreePower);
//                this.robot.motorFour.setPower(-wheelFourPower);
//            }
//        }
//        this.stop();
//        this.telemetry.update();
//    }

    /**
     * @param degreesToTurn Number of degrees to turn. If negative, turns right. If positive, turns left.
     */
    public void turn(double degreesToTurn) {
        double turnCorrector = 1;
        degreesToTurn = degreesToTurn * turnCorrector;
        this.robot.imu.initialize(parameters);
        angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        if (degreesToTurn < 0) {
            while ((angles.firstAngle > degreesToTurn) && this.opMode.opModeIsActive()) {
                this.robot.motorOne.setPower(wheelOnePower);
                this.robot.motorTwo.setPower(wheelTwoPower);
                this.robot.motorThree.setPower(-wheelThreePower);
                this.robot.motorFour.setPower(-wheelFourPower);
                angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("angles", angles.firstAngle);
                telemetry.addData("degreesToTurn", degreesToTurn);
                telemetry.update();
                Log.d("Turning", "angles 164: "  + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle);
            }
        } else {
            while ((angles.firstAngle < degreesToTurn) && this.opMode.opModeIsActive()) {
                this.robot.motorOne.setPower(-wheelOnePower);
                this.robot.motorTwo.setPower(-wheelTwoPower);
                this.robot.motorThree.setPower(wheelThreePower);
                this.robot.motorFour.setPower(wheelFourPower);
                angles = this.robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("angles", angles.firstAngle);
                telemetry.addData("degreesToTurn", degreesToTurn);
                telemetry.update();
                Log.d("Turning", "angles 176: " + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle);
            }
        }
        this.stop();
    }

    public void goLeft(double inches) {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving left");

        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition - (inches * sideInchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() > endPosition && this.opMode.opModeIsActive()) {
            Log.d("going Left", "End Position: " + endPosition);
            Log.d("going Left", "Start Pos: " + startPosition);
            Log.d("going Left", "Current Pos: " + this.robot.motorOne.getCurrentPosition());

            this.robot.motorOne.setPower(-wheelOnePower);
            this.robot.motorTwo.setPower(wheelTwoPower);
            this.robot.motorThree.setPower(wheelThreePower); // May need to go other direction
            this.robot.motorFour.setPower(-wheelFourPower); // May need to go other direction
        }
        this.telemetry.update();
        this.stop();
    }

    public void goRight(double inches) {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving right");
        startPosition = this.robot.motorOne.getCurrentPosition();
        endPosition = startPosition + (inches * sideInchToTick); // How far you need to travel
        while (this.robot.motorOne.getCurrentPosition() < endPosition && this.opMode.opModeIsActive()) {
            Log.d("goRight", "start position is " + startPosition);
            Log.d("goRight", "end position is " + endPosition);
            Log.d("goRight", "current position is " + this.robot.motorFour.getCurrentPosition());
            this.robot.motorOne.setPower(wheelOnePower); // May need to turn other direct
            this.robot.motorTwo.setPower(-wheelTwoPower); // May need to turn other direction
            this.robot.motorThree.setPower(-wheelThreePower);
            this.robot.motorFour.setPower(wheelFourPower);
        }
        this.stop();
        this.telemetry.update();
    }

    public void stop() {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Stopping the drive train");
        this.telemetry.addData("wheel power", this.robot.motorOne.getPower());
        this.telemetry.update();
        this.robot.motorOne.setPower(0);
        this.robot.motorTwo.setPower(0);
        this.robot.motorThree.setPower(0);
        this.robot.motorFour.setPower(0);
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train is stopped");
        this.telemetry.addData("wheel power", this.robot.motorOne.getPower());
        this.telemetry.update();
    }

    /*public void setPower(double powerLevel){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Drive train power set");
        wheelPower = powerLevel;
        this.telemetry.update();
    }*/
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "gamepad updated");

        telemetry.addData("1_left_stick_x", gamepad1.left_stick_x);
        telemetry.addData("1_left_stick_y", gamepad1.left_stick_y);
        telemetry.addData("1_right_stick_x", gamepad1.right_stick_x);
        telemetry.addData("1_right_stick_y", gamepad1.right_stick_y);


        if (Math.abs(gamepad1.left_stick_y) > 0.35 && this.opMode.opModeIsActive()) {
            double wheelPower = gamepad1.left_stick_y;
            this.robot.motorOne.setPower(-wheelPower * wheelOnePower);
            this.robot.motorTwo.setPower(-wheelPower * wheelTwoPower);
            this.robot.motorThree.setPower(-wheelPower * wheelThreePower);
            this.robot.motorFour.setPower(-wheelPower * wheelFourPower);

        }
        if (Math.abs(gamepad1.left_stick_x) > 0.6 && this.opMode.opModeIsActive()) {
            //forward backward
            if (gamepad1.left_stick_x > 0) {
                this.telemetry.addData(DRIVE_TRAIN_CAPTION, "robot is going right!");
                double wheelPower = gamepad1.left_stick_x;
                this.robot.motorOne.setPower(wheelPower * wheelOnePower); // May need to turn other direct
                this.robot.motorTwo.setPower(-wheelPower * wheelTwoPower); // May need to turn other direction
                this.robot.motorThree.setPower(-wheelPower * wheelThreePower);
                this.robot.motorFour.setPower(wheelPower * wheelFourPower);
            //left right
            } else {
                double wheelPower = Math.abs(gamepad1.left_stick_x);
                this.telemetry.addData(DRIVE_TRAIN_CAPTION, "robot is going left!");
                this.robot.motorOne.setPower(-wheelPower * wheelOnePower); // May need to turn other direct
                this.robot.motorTwo.setPower(wheelPower * wheelTwoPower); // May need to turn other direction
                this.robot.motorThree.setPower(wheelPower * wheelThreePower);
                this.robot.motorFour.setPower(-wheelPower * wheelThreePower);
            }
        } //turning
        while (Math.abs(gamepad1.right_stick_x) > 0.2 && this.opMode.opModeIsActive()) {
            if (gamepad1.right_stick_x > 0) {
                this.telemetry.addData(DRIVE_TRAIN_CAPTION, "robot is turning right!");
                double wheelPower = gamepad1.right_stick_x;
                this.robot.motorOne.setPower(wheelPower * wheelOnePower); // May need to turn other direct
                this.robot.motorTwo.setPower(wheelPower * wheelTwoPower); // May need to turn other direction
                this.robot.motorThree.setPower(-wheelPower * wheelThreePower);
                this.robot.motorFour.setPower(-wheelPower * wheelFourPower);
            } else {
                double wheelPower = Math.abs(gamepad1.right_stick_x);
                this.telemetry.addData(DRIVE_TRAIN_CAPTION, "robot is turning left!");
                this.robot.motorOne.setPower(-wheelPower * wheelOnePower); // May need to turn other direct
                this.robot.motorTwo.setPower(-wheelPower * wheelTwoPower); // May need to turn other direction
                this.robot.motorThree.setPower(wheelPower * wheelThreePower);
                this.robot.motorFour.setPower(wheelPower * wheelFourPower);
            }
        }

        if (gamepad1.dpad_up) {
            this.telemetry.addData("Zoom level:", "Zoomin'");
            this.telemetry.update();
            wheelOnePower = wheelOnePower * wheelSpeedChanger;
            wheelTwoPower = wheelTwoPower * wheelSpeedChanger;
            wheelThreePower = wheelThreePower * wheelSpeedChanger;
            wheelFourPower = wheelFourPower * wheelSpeedChanger;
        }

        if (gamepad1.dpad_down) {
            this.telemetry.addData("Zoom level:", "unzoomin'");
            this.telemetry.update();
            wheelOnePower = wheelOnePower/wheelSpeedChanger;
            wheelTwoPower = wheelTwoPower/wheelSpeedChanger;
            wheelThreePower = wheelThreePower/wheelSpeedChanger;
            wheelFourPower = wheelFourPower/wheelSpeedChanger;
        }

        if (Math.abs(gamepad1.left_stick_x) < 0.2 && Math.abs(gamepad1.left_stick_y) < 0.2 && this.opMode.opModeIsActive()) {
            this.robot.motorOne.setPower(0);
            this.robot.motorTwo.setPower(0);
            this.robot.motorThree.setPower(0);
            this.robot.motorFour.setPower(0);
        }
        this.telemetry.update();

    }

}
