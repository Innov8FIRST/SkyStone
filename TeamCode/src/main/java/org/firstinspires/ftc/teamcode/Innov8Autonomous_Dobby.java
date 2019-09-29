package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "Innov8Autonomous_Dobby", group = "Dobby")
public class Innov8Autonomous_Dobby extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        Dobby dobby = new Dobby(telemetry, hardwareMap);
        waitForStart();
        dobby.autonomous();
    }

}
