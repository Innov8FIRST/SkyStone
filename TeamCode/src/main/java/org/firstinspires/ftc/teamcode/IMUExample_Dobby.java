package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "IMUExample_Dobby", group = "Dobby")
public class IMUExample_Dobby extends LinearOpMode
{
    BNO055IMU imu;
    Orientation angles;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    Telemetry telemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        this.imu.initialize(parameters);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        this.telemetry.addData("first", angles.firstAngle);
        this.telemetry.addData("second", angles.secondAngle);
        this.telemetry.addData("third", angles.thirdAngle);
        this.telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            this.telemetry.addData("first", angles.firstAngle);
            Log.d("first angle: ", "" + angles.firstAngle);
            this.telemetry.addData("second", angles.secondAngle);
            Log.d("second angle: ", "" + angles.secondAngle);
            this.telemetry.addData("third", angles.thirdAngle);
            Log.d("third angle: ", "" + angles.thirdAngle);
            this.telemetry.update();
        }

    }
}