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
    public void pickup() {
        this.telemetry.addData("pickupStatus","Pickup is picking up block");
        this.telemetry.update();
        //pickups up block from ground
    }
    public void drop(){
        this.telemetry.addData("pickupStatus","Pickup is releasing block");
        this.telemetry.update();
        //drops block on foundation
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
