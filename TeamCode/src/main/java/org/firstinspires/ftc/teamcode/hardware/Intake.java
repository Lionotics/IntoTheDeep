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

    public  int useLessVariable = 0;



    // Tunable constants via dashboard
    public static double GRABBER_INCREMENT = 0.1;
    public static double MIN_POS = 0.0;
    public static double MAX_POS = 1.0;
    public static double PIVOT_POSITION_A = 0.4;
    public static double PIVOT_POSITION_B = 0.9;

    public  static  double newPos = 0;

    @Override
    public void initialize() {
        grabber = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "grabber");
        pivot = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "wrist");
        grabber.setPosition(grabber.getPosition());
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
    public Command moveGrabberBackward() {
        return new InstantCommand(()-> {
            useLessVariable = 0;
        grabberPos = Math.min(MAX_POS, grabberPos + GRABBER_INCREMENT);
        grabber.setPosition(grabberPos);
        });
    }

    public Command moveGrabberForward() {
       return new InstantCommand(() -> {
            useLessVariable = 0;
            grabberPos = Math.max(MIN_POS, grabberPos - GRABBER_INCREMENT);
            grabber.setPosition(grabberPos);
        });
    }

    public  Command stopMovingGrabber() {
        return  new InstantCommand(()-> {
     //       grabber.setPosition(grabber.getPosition());
        } );
    }

    /*@Override
    @NonNull
    public Command getDefaultCommand() {
        return     new InstantCommand(()-> {
            useLessVariable += 1;
            //double position = grabber.getPosition();
            //grabber.setPosition(grabber.getPosition());
        } );
    } */

}
