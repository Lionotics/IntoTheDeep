package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.acmerobotics.dashboard.config.Config;

@Config
public class Elbow extends Subsystem {

    public static final Elbow INSTANCE = new Elbow();

    private Elbow() { }
    private MotorEx armTop;
    private MotorEx armBottom;

    private static final int MAX_ROTATION = 1000;

    @Override
    public  void initialize() {
        armTop = new MotorEx("armTop");
        armTop.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armTop.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armBottom = new MotorEx("armBottom");
        armBottom.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armBottom.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public Command setPower(double i) {
        return new InstantCommand(()-> {
            armTop.setPower(i);
            armBottom.setPower(i);
        });
    }

    @NonNull
    @Override
    public Command getDefaultCommand() {
        return  setPower(0);
    }

    public  Command makeHorizontalManual() {
            if (getCurrentPosition() > 0 && Slides.INSTANCE.getCurrentPosition() < 5) {
              return   setPower(0.5);
            }
        return  new InstantCommand( ()->{} );
    }

    public  Command makeVerticalManual() {
            if (getCurrentPosition() < MAX_ROTATION && Slides.INSTANCE.getCurrentPosition() < 5) {
               return setPower(-0.5);
            }
        return  new InstantCommand( ()->{} );
    }

    public  double getCurrentPosition() {
        return armTop.getCurrentPosition();
    }


}
