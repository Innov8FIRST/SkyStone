package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BaseMover {
    Telemetry telemetry;
    String BASE_MOVER_CAPTION = "BaseMover Status";
    HardwareInnov8Dobby robot;
    LinearOpMode opMode;

    double startServo = 0;
    double baseServoStart = 0.2;
    double motorsDown = 1;
    boolean isGoingUp = false;
    double startPosition = 0;
    double endPosition = 1640/1.5;

    public BaseMover(Telemetry telemetry, HardwareInnov8Dobby robot, LinearOpMode opMode){
        this.opMode = opMode;
        this.robot = robot;
        this.telemetry = telemetry;
        this.telemetry.update();
//        this.robot.baseServoLeft.setPosition(startServo);
//        this.robot.bHSupport.setPosition((0.1));
//       this.robot.bHSupportRight.setPosition(1);
//        this.robot.baseServoRight.setPosition(0.1);
    }

    public void raiseMotors() {
        Log.d(BASE_MOVER_CAPTION,"Motors have been raised");
        this.telemetry.update();
            this.robot.baseServoLeft.setPosition(0.2);
            this.robot.baseServoRight.setPosition(0.1);
    }

    public void lowerMotors() {
        Log.d(BASE_MOVER_CAPTION,"Motors have been lowered");
        this.telemetry.addData(BASE_MOVER_CAPTION,"Motors have been lowered");
        this.telemetry.update();
    }


    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData(BASE_MOVER_CAPTION, "gamepad updated");
        this.telemetry.addData("Is moving up?", isGoingUp);
        this.telemetry.addData("base holder pos", this.robot.baseServoLeft.getPosition());
        this.telemetry.update();

        if (gamepad1.y) {
            Log.d("teleop status", " raising basemovers");
            this.raiseMotors();
        }
        if (gamepad1.a/* && this.robot.baseMover.getCurrentPosition() > -1*/) {
            Log.d("teleop status", "lower motors");
            this.lowerMotors();
        }

        // Moves worm gear w/o limits



    }
}
