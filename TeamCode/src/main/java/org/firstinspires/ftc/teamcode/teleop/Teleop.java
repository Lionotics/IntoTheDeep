package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.gamepad.GamepadEx;
import  com.rowanmcalpin.nextftc.ftc.gamepad.GamepadManager;


import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Elbow;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Slides;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import com.rowanmcalpin.nextftc.ftc.gamepad.GamepadEx;


@Config
@TeleOp(name = "YeshivaLeague2025Teleop", group = "Teleop")
public class Teleop extends  NextFTCOpMode {

    public Command driverControlled;

    public  Teleop() {
        super(DriveTrain.INSTANCE,Slides.INSTANCE,Elbow.INSTANCE);
    }
    private GamepadEx gp1;

    @Override
    public void onStartButtonPressed() {
        gp1 = gamepadManager.getGamepad1();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverControlled = DriveTrain.INSTANCE.Drive(gamepadManager.getGamepad1(), true);
        driverControlled.invoke();

        gp1.getDpadUp().setHeldCommand(()-> Slides.INSTANCE.moveUpManual());
        gp1.getDpadDown().setHeldCommand(() -> Slides.INSTANCE.moveDownManual());

        gp1.getY().setHeldCommand(() -> Elbow.INSTANCE.makeVerticalManual());
        gp1.getX().setHeldCommand(() -> Elbow.INSTANCE.makeHorizontalManual());



    }

    @Override
    public  void onUpdate() {
        telemetry.addData("dPad Up: ",gp1.getDpadUp().getState());
        telemetry.addData("dPad Down: ",gp1.getDpadDown().getState());
        telemetry.addData("Slides Position:  ", Slides.INSTANCE.getCurrentPosition());
        telemetry.addData("Elbow Position ",Elbow.INSTANCE.getCurrentPosition());
        telemetry.update();
    }


}
