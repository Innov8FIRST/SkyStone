package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "StraightRight_Dobby", group = "Dobby")
public class StraightRight_Dobby extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {

        Dobby dobby = new Dobby(telemetry, hardwareMap, this);
        waitForStart();
        dobby.straightRight();

    }
}
