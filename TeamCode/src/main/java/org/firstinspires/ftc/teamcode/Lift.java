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
    double liftPower = .7;
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
        this.robot.liftMotor.setPower(0);
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
        this.robot.liftMotor.setPower(0);
        this.telemetry.addData(LIFT_CAPTION,"Lift is moving");
        this.telemetry.update();
    }

    public void open(){
        this.telemetry.addData(LIFT_CAPTION,"Hand is opening");
        this.telemetry.update();
    }
    public void close(){
        this.telemetry.addData(LIFT_CAPTION,"Hand is closing");
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.addData(LIFT_CAPTION, "Lift is stoppin'");
        this.telemetry.update();
    }
    public void moveFoundation(){
        this.telemetry.addData(LIFT_CAPTION, "Lift is moving foundation");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData(LIFT_CAPTION, "gamepad updated");
        this.telemetry.update();
    }
}
