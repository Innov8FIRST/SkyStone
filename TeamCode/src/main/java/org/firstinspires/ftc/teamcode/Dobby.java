package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Dobby {
    Telemetry telemetry;
    String DOBBY_CAPTION = "Dobby Status";
    public Dobby(Telemetry telemetry){
        this.telemetry = telemetry;
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is ready to go");
        this.telemetry.update();
    }
    public void initVuforia(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby initialized vuforia");
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is free");
        this.telemetry.update();
    }
    public void teleop(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is teleop-ing");
        this.telemetry.update();
    }
    public void autonomous(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is doing the autonomous");
        this.telemetry.update();
    }
}
