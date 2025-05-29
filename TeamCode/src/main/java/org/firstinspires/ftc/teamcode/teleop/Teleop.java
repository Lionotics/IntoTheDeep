package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.gamepad.GamepadEx;

import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.gamepad.GamepadManager;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Elbow;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Kicker;
import org.firstinspires.ftc.teamcode.hardware.Slides;

@Config
@TeleOp(name = "YeshivaLeague2025Teleop", group = "Teleop")
public class Teleop extends NextFTCOpMode {

    public Command driverControlled;

    public Teleop() {
        super(DriveTrain.INSTANCE, Slides.INSTANCE, Elbow.INSTANCE, Kicker.INSTANCE, Intake.INSTANCE);
    }

    private GamepadEx gp1;

    @Override
    public void onStartButtonPressed() {
        gp1 = gamepadManager.getGamepad1();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Initialize subsystems
        Intake.INSTANCE.initialize();

        driverControlled = DriveTrain.INSTANCE.Drive(gp1, true);
        driverControlled.invoke();

        gp1.getDpadUp().setHeldCommand(() -> Slides.INSTANCE.moveUpManual());
        gp1.getDpadDown().setHeldCommand(() -> Slides.INSTANCE.moveDownManual());

        gp1.getY().setHeldCommand(() -> Elbow.INSTANCE.makeVerticalManual());
        gp1.getX().setHeldCommand(() -> Elbow.INSTANCE.makeHorizontalManual());

        gp1.getY().setReleasedCommand(() -> Elbow.INSTANCE.setPower(0));
        gp1.getX().setReleasedCommand(() -> Elbow.INSTANCE.setPower(0));

        gp1.getRightBumper().setPressedCommand( ()->Intake.INSTANCE.moveGrabberForwardManual()  );
        gp1.getLeftBumper().setPressedCommand( ()->Intake.INSTANCE.moveGrabberBackwardManual()  );
        gp1.getB().setPressedCommand( ()->Intake.INSTANCE.togglePivotCommand());
        gp1.getA().setPressedCommand( ()-> Kicker.INSTANCE.toggleKickerCommand());
    }

    @Override
    public void onUpdate() {


        // Telemetry
        telemetry.addData("dPad Up: ", gp1.getDpadUp().getState());
        telemetry.addData("dPad Down: ", gp1.getDpadDown().getState());
        telemetry.addData("Slides Position: ", Slides.INSTANCE.getCurrentPosition());
        telemetry.addData("Elbow Position: ", Elbow.INSTANCE.getCurrentPosition());
        telemetry.addData("Pivot State A?: ", Intake.INSTANCE.isPivotAtA());
        telemetry.addData("New Pos: ", Intake.INSTANCE.getNewPos());
        telemetry.update();
    }
}
