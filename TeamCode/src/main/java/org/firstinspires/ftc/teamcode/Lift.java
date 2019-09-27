package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {
    Telemetry telemetry;
    String LIFT_CAPTION = "Lift Status";
    public Lift(Telemetry telemetry){
        this.telemetry = telemetry;
        this.telemetry.addData(LIFT_CAPTION,"Lift is initialized");
        this.telemetry.update();
    }
    public void move(double inches){
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
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData(LIFT_CAPTION, "gamepad updated");
        this.telemetry.update();
    }
}
