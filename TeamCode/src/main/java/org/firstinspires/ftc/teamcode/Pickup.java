package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    Telemetry telemetry;
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    public Pickup(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode){
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        telemetry.addData("pickupStatus","Pickup initialized");
        telemetry.update();
    }
    public void rapOut(int timeCount) {
        this.telemetry.addData("Rack and Pinion: ","RAP is moving out");
        int counter = 0;
        while(this.opMode.opModeIsActive() && counter < timeCount){
            this.robot.rapServoLeft.setPosition(.25);
            this.robot.rapServoRight.setPosition(.75);
            this.telemetry.addData("Rack and Pinion: ", counter);
            this.telemetry.addData("rapServoLeft: ", .25);
            this.telemetry.addData("rapServoRight: ", .75);
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPosition());
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPosition());
            this.telemetry.update();
            counter++;
        }
        this.robot.rapServoLeft.setPosition(.5);
        this.robot.rapServoRight.setPosition(.5);
        this.telemetry.update();
        //moves rack and pinion out
    }
    public void rapIn(int timeCount){
        this.telemetry.addData("Rack and Pinion: ","RAP is moving out");
        int counter = 0;
        while(this.opMode.opModeIsActive() && counter < timeCount){
            this.robot.rapServoLeft.setPosition(.75);
            this.robot.rapServoRight.setPosition(.25);
            this.telemetry.addData("Rack and Pinion: ", counter);
            this.telemetry.addData("rapServoLeft: ", .75);
            this.telemetry.addData("rapServoRight: ", .25);
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPosition());
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPosition());
            this.telemetry.update();
            counter++;
        }
        this.robot.rapServoLeft.setPosition(.5);
        this.robot.rapServoRight.setPosition(.5);

        this.telemetry.update();
        //moves rack and pinion in
    }
    public void hand(){
        
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData("pickupStatus","Teleop update called");
        this.telemetry.update();
    }

}
