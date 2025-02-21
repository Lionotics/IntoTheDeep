package org.firstinspires.ftc.teamcode.testing;

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
@TeleOp(name="Slides Testing Freshman", group = "Testing")
public class SlidesTesting extends NextFTCOpMode {
    private  GamepadEx gp1;
    public  SlidesTesting() {
        super(Slides.INSTANCE);
    }

    @Override
    public void onStartButtonPressed() {
         gp1 = gamepadManager.getGamepad1();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        gp1.getDpadUp().setHeldCommand(() -> Slides.INSTANCE.setPower(1));
        gp1.getDpadDown().setHeldCommand(() -> Slides.INSTANCE.setPower(-1));


    }

    @Override
    public  void onUpdate() {
        telemetry.addData("dPad Up: ",gp1.getDpadDown().getState());
        telemetry.addData("dPad Down: ",gp1.getDpadDown().getState());
        telemetry.update();
    }

}
