package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BaseMover {
    Telemetry telemetry;
    String BASE_MOVER_CAPTION = "BaseMover Status";
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    double startServo = 0;
    double baseServoStart = 0.2;
    double motorsDown = 1;
    boolean isGoingUp = false;
    double startPosition = 0;
    double endPosition = 0;
    double wormLimit = 1640;

    public BaseMover(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode){
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        this.telemetry.addData(BASE_MOVER_CAPTION,"BaseMover is initialized");
        if  (this.opMode.opModeIsActive()) {
            startPosition = this.robot.baseMover.getCurrentPosition();
            endPosition = startPosition + wormLimit;
            while (this.opMode.opModeIsActive() && this.robot.baseMover.getCurrentPosition() < endPosition) {
                this.robot.baseMover.setPower(-0.5);
            }
        }
        this.telemetry.update();
    }

    public void raiseMotors() {
        this.telemetry.addData(BASE_MOVER_CAPTION,"Motors have been raised");
        this.telemetry.update();
        this.robot.baseMover.setPower(-.8);
        this.robot.baseServoLeft.setPosition(startServo);
        this.robot.bHSupport.setPosition((startServo));

    }

    public void lowerMotors() {
        this.robot.baseMover.setPower(-.8);
        this.robot.baseServoLeft.setPosition(motorsDown);
        this.robot.bHSupport.setPosition(motorsDown);
        this.telemetry.addData(BASE_MOVER_CAPTION,"Motors have been lowered");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){

        this.telemetry.addData(BASE_MOVER_CAPTION, "gamepad updated");
        this.telemetry.addData("Is moving up?", isGoingUp);
        this.telemetry.addData("base support pos", this.robot.bHSupport.getPosition());
        this.telemetry.addData("base holder pos", this.robot.baseServoLeft.getPosition());
        this.telemetry.update();

        if (gamepad1.y) {
            this.telemetry.addData("Base Motor Status",this.robot.baseMover.getCurrentPosition());
            this.robot.baseMover.setPower(-0.8);
            isGoingUp = true;
            this.robot.baseServoLeft.setPosition(startServo);
            this.robot.bHSupport.setPosition(startServo);
            //this.robot.baseServoRight.setPosition(motorsUp);
        }
        if (gamepad1.a && this.robot.baseMover.getCurrentPosition() > -1) {
            Log.d("BaseMotorWorm Status","" + this.robot.baseMover.getCurrentPosition());
            this.telemetry.update();
            this.robot.baseMover.setPower(.8);
            isGoingUp = false;
            this.robot.baseServoLeft.setPosition(motorsDown);
            //this.robot.baseServoRight.setPosition(motorsDown);
        }

        // Moves worm gear w/o limits

        if (gamepad1.dpad_up) {
            this.robot.baseMover.setPower(.8);
        }

        if((!gamepad1.y && !gamepad1.a) || this.robot.baseMover.getCurrentPosition() < 0){
            this.robot.baseMover.setPower(0);
        }

        if(!isGoingUp){
            if(this.robot.baseServoLeft.getPosition() > 0.8){
                this.robot.bHSupport.setPosition(motorsDown);
            }
        }
        if(isGoingUp){
            if(this.robot.bHSupport.getPosition() < 0.3){
                this.robot.baseServoLeft.setPosition(baseServoStart);
            }
        }


    }
}
