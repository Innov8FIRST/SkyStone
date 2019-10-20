package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Dobby {
    Telemetry telemetry;
    String DOBBY_CAPTION = "Dobby Status";
    DriveTrain driveTrain;
    Pickup pickup;
    Lift lift;
    Gamepad gamepad1 = new Gamepad();
    Gamepad gamepad2 = new Gamepad();
   HardwareInnov8Dobby robot;
    public Dobby(Telemetry telemetry, HardwareMap hwmap){
        this.robot = new HardwareInnov8Dobby(hwmap);
        this.telemetry = telemetry;
        this.initVuforia();
        pickup = new Pickup(this.telemetry);
        lift = new Lift(this.telemetry);
        driveTrain = new DriveTrain(this.telemetry, this.robot);
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is ready to go");
        this.telemetry.update();
        //robot.init(hwmap);
    }
    public void initVuforia(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby initialized vuforia");
        this.telemetry.update();
    }
    public double getConfi(){
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is getting confi");
        double confi = 0.98;
        return confi;
    }
    public void stop(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is free");

        this.telemetry.update();
    }
    public void teleop(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is teleop-ing");
        driveTrain.teleopUpdate(gamepad1,gamepad2);
        this.telemetry.update();
    }
    public void autonomous() {
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is doing the autonomous");
        //driveTrain.goForward(20.00); // assuming robot is 18" long & camera is on front of robot
        //int blockNum = 1;
        /*while(getConfi()<0.8 && blockNum < 6){
            driveTrain.goRight(8.0);
            blockNum++;
        }*/
        //pickup.pickup();
        driveTrain.goBackward(8.0);
        //driveTrain.goLeft(blockNum*8 + 60.0);
        driveTrain.goForward(8.0);
        //pickup.drop();
        //lift.moveFoundation();
        // driveTrain.goToLine(false);
        driveTrain.goLeft(8);
        driveTrain.goRight(8);
        driveTrain.turn(-90);
        driveTrain.turn(90);
        this.telemetry.update();
    }
}
