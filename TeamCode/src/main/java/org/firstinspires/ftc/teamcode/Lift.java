package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {
    Telemetry telemetry;
    String LIFT_CAPTION = "Lift Status";
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    double inchesToTickLift = 1;
    double liftPower = .3;
    double startLift = 0;
    double endLift = 0;
    double upperLimit = 100;
    double lowerLimit = 0;

    public Lift(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode){

        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        this.telemetry.addData(LIFT_CAPTION,"Lift is initialized");
        this.telemetry.update();
        this.robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderReset() {
        this.robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.telemetry.addData("Encoder reset", "");
        this.telemetry.update();
    }

    public void moveUp(double inches){
        startLift = this.robot.liftMotor.getCurrentPosition();
        endLift = startLift + (inches * inchesToTickLift);
        while (this.opMode.opModeIsActive() && this.robot.liftMotor.getCurrentPosition() < endLift && this.robot.liftMotor.getCurrentPosition() < upperLimit) {
            this.robot.liftMotor.setPower(liftPower);
            this.telemetry.addData(LIFT_CAPTION,"Going up?");
            this.telemetry.addData("encoder value is ", this.robot.liftMotor.getCurrentPosition());
            this.telemetry.addData("startLift = ", startLift);
            this.telemetry.addData("endLift = ", endLift);
            this.telemetry.update();
        }
        this.stop();
        this.telemetry.addData(LIFT_CAPTION,"Lift is moving");
        this.telemetry.update();
    }

    public void moveDown(double inches){
        startLift = this.robot.liftMotor.getCurrentPosition();
        endLift = startLift - (inches * inchesToTickLift);
        while (this.opMode.opModeIsActive() && this.robot.liftMotor.getCurrentPosition() > endLift && this.robot.liftMotor.getCurrentPosition() > lowerLimit) {
            this.robot.liftMotor.setPower(-liftPower);
            this.telemetry.addData(LIFT_CAPTION,"Going down?");
            this.telemetry.addData("encoder value is ", this.robot.liftMotor.getCurrentPosition());
            this.telemetry.addData("startLift = ", startLift);
            this.telemetry.addData("endLift = ", endLift);
            this.telemetry.update();
        }
        this.stop();
        this.telemetry.addData(LIFT_CAPTION,"Lift is moving");
        this.telemetry.update();
    }

    public void stop() {
        this.telemetry.addData(LIFT_CAPTION, "Lift is stopped");
        this.robot.liftMotor.setPower(0);
        this.telemetry.update();
    }


    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){

        this.telemetry.addData(LIFT_CAPTION, "gamepad updated");
        telemetry.addData("2_left_stick_y", gamepad1.left_stick_y);
        this.telemetry.update();

        if (Math.abs(gamepad2.left_stick_y) > 0.2 && this.opMode.opModeIsActive()) {
            this.robot.liftMotor.setPower(gamepad2.left_stick_y);
            this.robot.liftMotor2.setPower(gamepad2.left_stick_y);

        }
        if (Math.abs(gamepad2.left_stick_y) <= 0.2) {
            this.robot.liftMotor.setPower(0);
            this.robot.liftMotor2.setPower(0);

        }

    }
}
