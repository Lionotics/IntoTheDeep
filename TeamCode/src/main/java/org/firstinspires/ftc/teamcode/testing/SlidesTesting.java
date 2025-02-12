package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@Config
@TeleOp(name="Slides Testing Freshman", group = "Testing")
public class SlidesTesting extends LinearOpMode {
    Robot robot =  Robot.getInstance();

    @Override
    public void runOpMode() throws InterruptedException {
        GamepadEx gp1 = new GamepadEx();


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(hardwareMap);

        waitForStart();


        while (opModeIsActive()) {
            gp1.update(gamepad1);

            if (gp1.dpad_up.isCurrentlyPressed() ) {
                robot.slides.manualUp();
            } else if (gp1.dpad_down.isCurrentlyPressed()) {
                robot.slides.manualDown();
            } else {
                robot.slides.hold();
            }

           telemetry.addData("dPad Up: ",gp1.dpad_up.isCurrentlyPressed());
            telemetry.addData("dPad Down: ",gp1.dpad_down.isCurrentlyPressed());
            telemetry.update();

        }


    }



}
