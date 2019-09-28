package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Innov8Teleop_Dobby", group = "Dobby")
public class Innov8Teleop_Dobby extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Dobby dobby = new Dobby(telemetry);
        dobby.teleop();
    }
}
