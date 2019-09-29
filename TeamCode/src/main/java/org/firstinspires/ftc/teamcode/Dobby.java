package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Dobby {
    Telemetry telemetry;
    String DOBBY_CAPTION = "Dobby Status";
    DriveTrain driveTrain;
    Pickup pickup;
    Lift lift;
    HardwareInnov8Dobby robot = new HardwareInnov8Dobby();
    public Dobby(Telemetry telemetry, HardwareMap hwmap){
        this.initVuforia();
        driveTrain = new DriveTrain(this.telemetry);
        pickup = new Pickup(this.telemetry);
        lift = new Lift(this.telemetry);
        this.telemetry = telemetry;
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is ready to go");
        this.telemetry.update();
        robot.init(hwmap);
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
        this.telemetry.update();
    }
    public void autonomous() {
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is doing the autonomous");
        driveTrain.goForward(20.00); // assuming robot is 18" long & camera is on front of robot
        int blockNum = 1;
        while(getConfi()<0.8 && blockNum < 6){
            driveTrain.goLeft(8.0);
            blockNum++;
        }

        this.telemetry.update();
    }
}
