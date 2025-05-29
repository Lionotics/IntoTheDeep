package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;

@Config
public class Intake extends Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() {}

    private Servo grabber;
    public Servo pivot;



    private boolean pivotAtA = true;
    private double grabberPos = 0.5;

    // Tunable constants via dashboard
    public static double GRABBER_INCREMENT = 0.1;
    public static double MIN_POS = 0.0;
    public static double MAX_POS = 1.0;
    public static double PIVOT_POSITION_A = 0.2;
    public static double PIVOT_POSITION_B = 0.8;

    public  static  double newPos = 0;

    @Override
    public void initialize() {
        grabber = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "grabber");
        pivot = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "wrist");
        grabber.setPosition(grabberPos);
        pivot.setPosition(PIVOT_POSITION_A);
    }

    public boolean isPivotAtA() {
        return pivotAtA;
    }

    public double getNewPos() {
       // return newPos;
        return  0;
    }

    public Command togglePivotCommand() {
        return new InstantCommand(() -> {
            pivotAtA = !pivotAtA;
           double  newPosition = pivotAtA ? PIVOT_POSITION_A : PIVOT_POSITION_B;
            pivot.setPosition(newPosition);
        }); // 👈 required to declare Intake as a requirement
    }

    public Command moveGrabberForwardManual() {
        return new InstantCommand(()-> {
        grabberPos += GRABBER_INCREMENT;
        grabberPos = Math.min(MAX_POS, grabberPos);
        grabber.setPosition(grabberPos);
        });
    }

    public Command moveGrabberBackwardManual() {
        return new InstantCommand(() -> {
            grabberPos -= GRABBER_INCREMENT;
            grabberPos = Math.max(MIN_POS, grabberPos);
            grabber.setPosition(grabberPos);
        });
    }

}
