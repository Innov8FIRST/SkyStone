package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BaseMover {
    Telemetry telemetry;
    String BASE_MOVER_CAPTION = "BaseMover Status";
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    double motorsUp = 0;
    double motorsDown = 0.5;

    public BaseMover(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode){
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        this.telemetry.addData(BASE_MOVER_CAPTION,"BaseMover is initialized");
        this.telemetry.update();
    }

    public void raiseMotors() {
        this.robot.baseServoLeft.setPosition(motorsUp);
        this.robot.baseServoRight.setPosition(motorsUp);
        this.telemetry.addData(BASE_MOVER_CAPTION,"Motors have been raised");
        this.telemetry.update();
    }

    public void lowerMotors() {
        this.robot.baseServoLeft.setPosition(motorsDown);
        this.robot.baseServoRight.setPosition(motorsDown);
        this.telemetry.addData(BASE_MOVER_CAPTION,"Motors have been lowered");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){

        this.telemetry.addData(BASE_MOVER_CAPTION, "gamepad updated");
        this.telemetry.update();

        if (gamepad1.y) {
            this.raiseMotors();
        }
        if (gamepad1.a) {
            this.lowerMotors();
        }

    }
}
