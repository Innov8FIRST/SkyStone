package org.firstinspires.ftc.teamcode;

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
    public Dobby(Telemetry telemetry, HardwareMap hwmap, LinearOpMode opMode){
        this.opMode = opMode;
        this.robot = new HardwareInnov8Dobby(hwmap);
        this.telemetry = telemetry;
        vuforia = new DobbyVuforia(this.telemetry, this.robot, this.opMode);
        pickup = new Pickup(this.telemetry, this.robot, this.opMode);
        lift = new Lift(this.telemetry, this.robot, this.opMode);
        driveTrain = new DriveTrain(this.telemetry, this.robot, this.opMode);
        baseMover = new BaseMover(this.telemetry, this.robot, this.opMode);
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is ready to go");
        this.telemetry.update();
}

    public double getConfi(){
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is getting confi");
        double confi = 0.98;
        return confi;
    }
    public void stop(){
        this.telemetry.addData(DOBBY_CAPTION,"Dobby is free");

        this.telemetry.update();
    }
    public void teleop(Gamepad gamepad1, Gamepad gamepad2){
        while (this.opMode.opModeIsActive()) {
            this.telemetry.addData(DOBBY_CAPTION, "Dobby is teleop-ing");
            driveTrain.teleopUpdate(gamepad1, gamepad2);
            pickup.teleopUpdate(gamepad1, gamepad2);
            lift.teleopUpdate(gamepad1, gamepad2);
            baseMover.teleopUpdate(gamepad1, gamepad2);
            this.telemetry.update();
        }
    }
    public void autonomous() {
        this.telemetry.addData(DOBBY_CAPTION, "Dobby is doing the autonomous");
        driveTrain.goForward(18.00); // assuming robot is 18" long & camera is on front of robot
        int blockNum = 1;
        //if(blockNum<=6 /*&& !vuforia.isSkystone()*/ && this.opMode.opModeIsActive()){
         //   this.telemetry.addData("Block Number is", blockNum);
          //  //this.telemetry.addData("Is Skystone", "" + vuforia.isSkystone());
          //  driveTrain.goRight(8.0);
          //  this.telemetry.update();
          //  blockNum++;
        //}
        //pickup.rapOut(100);
        //pickup.handClose();
        //lift.moveUp(3);
        driveTrain.turn(90);
       // driveTrain.goForward(blockNum*8 + 60.0); // should end up in "building zone"
       // pickup.handOpen();
        this.telemetry.update();
    }

    public void autoPark() {
        driveTrain.goForward(12.00); // assuming robot is 18" long & camera is on front of robot
    }
}
