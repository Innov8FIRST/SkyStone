package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    Telemetry telemetry;
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    public Pickup(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode) {
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        telemetry.addData("pickupStatus", "Pickup initialized");
        telemetry.update();
    }

    public void rapOut(long milliSecs) {
        this.telemetry.addData("Rack and Pinion: ", "RAP is moving out");
        this.robot.rapServoLeft.setPower(-1);
        this.robot.rapServoRight.setPower(1);
        this.telemetry.addData("rapServoLeft: ", -1);
        this.telemetry.addData("rapServoRight: ", 1);
        this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPower());
        this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPower());
        this.telemetry.update();
        try {
            Thread.sleep(milliSecs);
        }
        catch(InterruptedException e){
            Log.e("Sleep", "is broken", e);
        }
        this.robot.rapServoLeft.setPower(0);
        this.robot.rapServoRight.setPower(0);
        this.telemetry.update();
        //moves rack and pinion out
    }

    public void rapIn(long milliSecs) {
        this.telemetry.addData("Rack and Pinion: ", "RAP is moving out");
        this.robot.rapServoLeft.setPower(1);
        this.robot.rapServoRight.setPower(-1);
        this.telemetry.addData("rapServoLeft: ", -1);
        this.telemetry.addData("rapServoRight: ", 1);
        this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPower());
        this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPower());
        this.telemetry.update();
        try {
            Thread.sleep(milliSecs);
        }
        catch(InterruptedException e){
            Log.e("Sleep", "is broken", e);
        }
        this.robot.rapServoLeft.setPower(0);
        this.robot.rapServoRight.setPower(0);
        this.telemetry.update();
        //moves rack and pinion in
    }

    public void handOpen() {
        this.telemetry.addData("Hand status: ", "Hand is opening");
        int endPosition = -511;
        Log.d("hand motor end pos", ""+endPosition);
        while(this.robot.handMotor.getCurrentPosition() > endPosition && this.opMode.opModeIsActive()){
            Log.d("hand motor position", "" + this.robot.handMotor.getCurrentPosition());
            this.robot.handMotor.setPower(0.4);
        }
        this.robot.handMotor.setPower(0);
    }

    public void handClose() {
        this.telemetry.addData("Hand status: ", "Hand is opening");
        this.robot.handMotor.setPower(0.2);
//        while(pastEncoder != this.robot.handMotor.getCurrentPosition() && this.opMode.opModeIsActive()){
//            currentEncoder = this.robot.handMotor.getCurrentPosition();
//            this.robot.handMotor.setPower((0.2));
//            this.telemetry.addData("Past encoder value", pastEncoder);
//            this.telemetry.addData("current hand pos.", this.robot.handMotor.getCurrentPosition());
//            this.telemetry.update();
//            pastEncoder = currentEncoder;
//        }
    }

    public void ginnyDrop () {
        this.robot.ginny.setPosition(0.1);
        this.telemetry.addData("Ginny status: ", "Ginny is open");
        this.telemetry.update();
    }


    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        this.telemetry.addData("pickupStatus", "Teleop update called");
        this.telemetry.update();
        this.robot.rapServoLeft.setPower(gamepad2.right_stick_y);
        this.robot.rapServoRight.setPower(-gamepad2.right_stick_y);
        this.telemetry.addData("hand motor pos.",this.robot.handMotor.getCurrentPosition());
        if (gamepad2.right_bumper) {
            this.robot.handMotor.setPower(.5);
        } else if (gamepad2.left_bumper) {
            this.robot.handMotor.setPower(-.5);
        } else {
            this.robot.handMotor.setPower(0);
        }
        if (gamepad2.y) {
            this.robot.ginny.setPosition(0.1);
        }
        if (gamepad2.a) {
            this.robot.ginny.setPosition(0.5);
        }
        this.telemetry.addData("Red value", this.robot.dumbledore.red());
        this.telemetry.addData("Blue value", this.robot.dumbledore.blue());
        this.telemetry.addData("Green value", this.robot.dumbledore.green());
        this.telemetry.update();
    }

}
