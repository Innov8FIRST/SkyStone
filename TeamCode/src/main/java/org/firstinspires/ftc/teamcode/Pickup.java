package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {

    Telemetry telemetry;
    public Pickup(Telemetry telemetry){
        this.telemetry=telemetry;
        telemetry.addData("pickupStatus","Pickup initialized");
        telemetry.update();
    }
    public void pull() {
        this.telemetry.addData("pickupStatus","Pickup is pulling");
        this.telemetry.update();
    }
    public void push(){
        this.telemetry.addData("pickupStatus","Pickup is pushing");
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.addData("pickupStatus","Pickup is stopped!!!");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData("pickupStatus","Te/leop update called");
        this.telemetry.update();
    }

}
