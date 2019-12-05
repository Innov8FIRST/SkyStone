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
            this.robot.rapServoLeft.setPower(-1);
            this.robot.rapServoRight.setPower(1);
            this.telemetry.addData("Rack and Pinion: ", counter);
            this.telemetry.addData("rapServoLeft: ",  -1);
            this.telemetry.addData("rapServoRight: ", 1 );
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPower());
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPower());
            this.telemetry.update();
            counter++;
        }
        this.robot.rapServoLeft.setPower(0);
        this.robot.rapServoRight.setPower(0);
        this.telemetry.update();
        //moves rack and pinion out
    }
    public void rapIn(int timeCount){
        this.telemetry.addData("Rack and Pinion: ","RAP is moving out");
        int counter = 0;
        while(this.opMode.opModeIsActive() && counter < timeCount){
            this.robot.rapServoLeft.setPower(1);
            this.robot.rapServoRight.setPower(-1);
            this.telemetry.addData("Rack and Pinion: ", counter);
            this.telemetry.addData("rapServoLeft: ", 1);
            this.telemetry.addData("rapServoRight: ", -1);
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoLeft.getPower());
            this.telemetry.addData("Rack and Pinion: ", this.robot.rapServoRight.getPower());
            this.telemetry.update();
            counter++;
        }
        this.robot.rapServoLeft.setPower(0);
        this.robot.rapServoRight.setPower(0);

        this.telemetry.update();
        //moves rack and pinion in
    }

    /*public void handClose(){
        this.robot.handServo.setPosition(.1);
    }
    public void handOpen(){
        this.robot.handServo.setPosition(.6);
    }*/
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        this.telemetry.addData("pickupStatus", "Teleop update called");
        this.telemetry.update();
        this.robot.rapServoLeft.setPower(gamepad2.right_stick_x);
        this.robot.rapServoRight.setPower(-gamepad2.right_stick_x);

        if (gamepad2.right_bumper) {
            //this.robot.handServo.setPosition(this.robot.handServo.getPosition() + 0.005);
            this.robot.handMotor.setPower(.5);
        }
        else if(gamepad2.left_bumper){
            //this.robot.handServo.setPosition(this.robot.handServo.getPosition() - 0.005);
            this.robot.handMotor.setPower(-.5);
        }
        else {
            this.robot.handMotor.setPower(0);
        }
    }

}
