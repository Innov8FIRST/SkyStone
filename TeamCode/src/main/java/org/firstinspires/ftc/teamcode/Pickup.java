package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {

    Telemetry telemetry;
    public Pickup(Telemetry telemetry){
        this.telemetry=telemetry;
        telemetry.add("Pickup initialized");
        telemetry.update();
    }
    public void pull() {
        this.telemtry.add("Pickup is pulling!!");
        this.telemetry.update();
    }
    public void push(){
        this.telemtry.add("Pickup is pushing!!");
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.add("Pickup is stopped!!!");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.add("updated gamepad!");
        this.telemetry.update();
    }

}
