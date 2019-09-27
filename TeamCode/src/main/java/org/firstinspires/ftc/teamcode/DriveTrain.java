package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class DriveTrain {
    String DRIVE_TRAIN_CAPTION = "Drive Status";
    Telemetry telemetry;
    public DriveTrain(Telemetry telemetry){
        this.telemetry = telemetry;
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train initialized");
        this.telemetry.update();
    }
    public void goForward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving forward");
        this.telemetry.update();
    }
    public void goBackward(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving backwards");
        this.telemetry.update();
    }
    public void goToLine(boolean isRed){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Robot is moving to line");
        this.telemetry.update();
    }
    public void turn(double degrees){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is turning");
        this.telemetry.update();
    }
    public void goLeft(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is moving left");
        this.telemetry.update();
    }
    public void goRight(double inches){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Robot is moving right");
        this.telemetry.update();
    }
    public void stop(){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Drive train is stopped");
        this.telemetry.update();
    }
    public void setPower(double powerLevel){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION,"Drive train power set");
        this.telemetry.update();
    }
    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2){
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "gamepad updated");
        this.telemetry.update();
    }
}
