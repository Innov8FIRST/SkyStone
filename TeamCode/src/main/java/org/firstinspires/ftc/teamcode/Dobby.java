package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

public class Dobby {
    Telemetry telemetry;
    String DOBBY_CAPTION = "Dobby Status";
    DriveTrain driveTrain;
    Pickup pickup;
    Lift lift;
    BaseMover baseMover;
    LinearOpMode opMode;
    DobbyVuforia vuforia;
    Gamepad gamepad1 = new Gamepad();
    Gamepad gamepad2 = new Gamepad();
    HardwareInnov8Dobby robot;

    double startPosition = 0;
    double endPosition = 0;

    public Dobby(Telemetry telemetry, HardwareMap hwmap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.robot = new HardwareInnov8Dobby(hwmap);
        this.telemetry = telemetry;
        pickup = new Pickup(this.telemetry, this.robot, this.opMode);
        lift = new Lift(this.telemetry, this.robot, this.opMode);
        driveTrain = new DriveTrain(this.telemetry, this.robot, this.opMode);
        baseMover = new BaseMover(this.telemetry, this.robot, this.opMode);
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is ready to go");
        this.telemetry.update();
    }

    public double getConfi() {
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is getting confi");
        double confi = 0.98;
        return confi;
    }

    public void stop() {
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is free");

        this.telemetry.update();
    }

    public void teleop(Gamepad gamepad1, Gamepad gamepad2) {

        while (this.opMode.opModeIsActive()) {
            this.telemetry.addData(DOBBY_CAPTION, "Dobby is teleop-ing");
            driveTrain.teleopUpdate(gamepad1, gamepad2);
            pickup.teleopUpdate(gamepad1, gamepad2);
            lift.teleopUpdate(gamepad1, gamepad2);
            baseMover.teleopUpdate(gamepad1, gamepad2);
            this.telemetry.update();
        }
    }

    public void blueNoBase() {
        vuforia = new DobbyVuforia(this.telemetry, this.robot, this.opMode);
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is doing the autonomous");
        int blockNum = 1;
        driveTrain.goForward(24.00); // assuming robot is 18" long & camera is on front of robot
        this.telemetry.addData("auto status", "now entering while loop");
        this.telemetry.addData("Is Skystone", "" + vuforia.isSkystone());
        this.telemetry.addData("Block Number is", blockNum);
        this.telemetry.update();
        while (this.opMode.opModeIsActive() && blockNum <= 3 && !this.vuforia.isSkystone()) {
            this.telemetry.addData("auto status", "now in the while loop");
            this.telemetry.addData("Block Number is", blockNum);
            this.telemetry.addData("Is Skystone", "" + vuforia.isSkystone());
            driveTrain.goRight(8.0);
            this.telemetry.update();
            blockNum++;
        }
        Log.d("auto status", "now extending rap");
        pickup.rapOut(2000);
        Log.d("auto status", "now opening hand");
        pickup.handOpen();
        driveTrain.goForward(6);
        Log.d("auto status", "now closing hand");
        pickup.handClose();
        Log.d("auto status", "now lifting");
        lift.moveUp(0.25);
        Log.d("auto status", "now turning");
        driveTrain.turn(90);
        this.robot.handMotor.setPower(0);
        Log.d("auto status", "now going forward");
        driveTrain.goForward(60.0 + blockNum * 8); // should end up in "building zone"
        Log.d("auto status", "now opening hand");
        pickup.handOpen();
        Log.d("auto status", "now going backward");
        driveTrain.goBackward(26);
        this.telemetry.update();
    }

    public void redNoBase() {

    }

    public void blueFull() {

    }

    public void redFull() {

    }

    public void blueFull2() {

    }

    public void redFull2() {

    }

    public void autoSimple() {

        driveTrain.goForward(12); // assuming robot is 18" long
    }

    public void blueBaseOnly() {
        driveTrain.goForward(34);
        baseMover.lowerMotors();
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }

        driveTrain.goBackward(34);
        driveTrain.turn(90);
        driveTrain.goForward(12);
        baseMover.raiseMotors();
        driveTrain.goBackward(24);
        baseMover.lowerMotors();
    }

    public void redBaseOnly() {

    }

    public void useVuforia() {
        while (this.opMode.opModeIsActive()) {
            telemetry.addData("Is Skystone", vuforia.isSkystone());
            telemetry.update();
        }
    }
}
