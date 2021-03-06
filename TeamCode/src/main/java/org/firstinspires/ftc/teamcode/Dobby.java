package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is doing the autonomous");
        int blockNum = 1;
        baseMover.lowerMotors();
        Log.d("auto status", "extending rap and going forward");
        driveTrain.forwardAndRapOut(14);
        Log.d("auto status", "now entering while loop");
        Log.d("Red value", "" + this.robot.dumbledore.red());
        Log.d("Blue value", "" + this.robot.dumbledore.blue());
        Log.d("Green value", "" + this.robot.dumbledore.green());
        Log.d("Block Number is", "" + blockNum);
        this.telemetry.update();
        while (this.opMode.opModeIsActive() && blockNum < 3 &&
                (this.robot.dumbledore.red() > 10 || this.robot.dumbledore.blue() > 10 || this.robot.dumbledore.green() > 10)) {
            Log.d("auto status", "now in the while loop");
            Log.d("Block Number is", "" + blockNum);
            this.telemetry.addData("Red value", this.robot.dumbledore.red());
            this.telemetry.addData("Blue value", this.robot.dumbledore.blue());
            this.telemetry.addData("Green value", this.robot.dumbledore.green());
            this.telemetry.update();
            driveTrain.goRight(8.0);
            blockNum++;
        }
        Log.d("auto status", "now going left");
        driveTrain.goLeft(6);
        Log.d("auto status", "now opening hand");
        pickup.handOpen();
        Log.d("auto status", "now going forward");
        driveTrain.goForward(8);
        Log.d("auto status", "now closing hand");
        pickup.handClose();
        try {
            Thread.sleep(2500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        Log.d("auto status", "now going backward");
        driveTrain.goBackward(12);
        Log.d("auto status", "now turning");
        driveTrain.turn(55);
        Log.d("auto status", "now setting hand power to 0");
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
    public void straightRight(){
        driveTrain.goForward(15);
        driveTrain.turn(-90);
        driveTrain.goForward((30));
    }
    public void straightLeft(){
        driveTrain.goForward(15);
        driveTrain.turn(90);
        driveTrain.goForward((30));
    }

    public void redBaseWall() {
        driveTrain.goRight(15);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(30);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        baseMover.lowerMotors();
        try {
            Thread.sleep(1500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.turn(-10);
        driveTrain.goBackward(36);
        driveTrain.turn(-60);
        driveTrain.goForward(19);
        baseMover.raiseMotors();
        driveTrain.goRight(30);
        driveTrain.goLeft(2);
//        try {
//            Thread.sleep(11000);
//        }
//        catch(InterruptedException e){
//            Log.d("Spleepy time", "Sleep failed");
//        }
        driveTrain.goBackward(28);
//        driveTrain.turn(90);
//        this.straightLeft();
        baseMover.lowerMotors();
    }

    public void blueBaseWall() {
        driveTrain.goLeft(15);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(30);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        baseMover.lowerMotors();
        try {
            Thread.sleep(1500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.turn(30);
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goBackward(12);
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.turn(60);
        driveTrain.goForward(50);
        baseMover.raiseMotors();
        driveTrain.goLeft(65);
        driveTrain.goRight(2);
//        try {
//            Thread.sleep(11000);
//        }
//        catch(InterruptedException e){
//            Log.d("Spleepy time", "Sleep failed");
//        }
        driveTrain.goBackward(36);
//        driveTrain.turn(90);
//        this.straightLeft();
        baseMover.lowerMotors();
    }

    public void redBaseBridge() {
        driveTrain.goRight(15);
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(33);
        baseMover.lowerMotors();
        try {
            Thread.sleep(1500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.turn(-15);
        driveTrain.goBackward(31);
        driveTrain.turn(-60);
        driveTrain.goForward(12);
        baseMover.raiseMotors();
        driveTrain.goLeft(28);
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(24);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goLeft(9);
//        driveTrain.goRight(26);
//        driveTrain.goLeft(4);
        driveTrain.goBackward(40);
//        driveTrain.goLeft(30);
//        driveTrain.goBackward(15);
        baseMover.lowerMotors();
    }

    public void blueBaseBridge() {
        driveTrain.goLeft(15);
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(33);
        baseMover.lowerMotors();
        try {
            Thread.sleep(1500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.turn(15);
        driveTrain.goBackward(35);
        driveTrain.turn(60);
        driveTrain.goForward(12);
        baseMover.raiseMotors();
        driveTrain.goRight(28);
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
        driveTrain.goForward(24);
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            Log.d("Spleepy time", "Sleep failed");
        }
//        driveTrain.goRight(26);
//        driveTrain.goLeft(4);
        driveTrain.goBackward(40);
//        driveTrain.goLeft(30);
//        driveTrain.goBackward(15);
        baseMover.lowerMotors();
    }

    public void useVuforia() {
        while (this.opMode.opModeIsActive()) {
            telemetry.addData("Is Skystone", vuforia.isSkystone());
            telemetry.update();
        }
    }

}
