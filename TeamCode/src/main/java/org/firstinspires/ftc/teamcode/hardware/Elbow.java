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
    public MotorEx armBottom;

    private static final int MAX_ROTATION = 1000;

    private  static  final int DESIRED_DIFFERENCE_UP = 1000;
    private  static  final int DESIRED_DIFFERENCE_DOWN1 = 100;
    private  static  final int DESIRED_DIFFERENCE_DOWN2 = 1000;


    public  static  double ELBOW_POWER = 0.7;

    public  static  double GOING_DOWN_POWER = 0.3; // conteracts gravity so it doesn't go so hard down.

    public  static  double  DEFAULT_COMMAND_POWER  = 0;

    @Override
    public  void initialize() {

        armBottom = new MotorEx("armBottom");
    //    armBottom.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  armBottom.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



    public Command setPower(double i) {
        return new InstantCommand(()-> {
            armBottom.setPower(i);
        });
    }


    /*@NonNull
    @Override
    public Command getDefaultCommand() {
        return  setPower(DEFAULT_COMMAND_POWER);
    } */

    public  Command makeHorizontalManual( ) {
            if (true || getCurrentPosition() >= 0 && Slides.INSTANCE.getCurrentPosition() < 5) {
              return   setPower(ELBOW_POWER);
            }
        return  new InstantCommand( ()->{} );
    }

    public  Command makeVerticalManual( ) {
        if (true || getCurrentPosition() >= 0 && Slides.INSTANCE.getCurrentPosition() < 5) {
            return   setPower(-1 * ELBOW_POWER);
        }
        return  new InstantCommand( ()->{} );
    }



    public Command makeVerticalExp() {
        return new Command() {

            private int targetPosition;
            private boolean initialized = false;

            @Override
            public void start() {

                armBottom.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                int currentPosition = (int) getCurrentPosition();
                targetPosition = currentPosition + DESIRED_DIFFERENCE_UP;
                //armBottom.getMotor().setTargetPosition(targetPosition);
                //setPower(ELBOW_POWER);

                //armBottom.getMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);

                initialized = true;
            }

            @Override
            public void  update() {
                if (getCurrentPosition() < targetPosition) {
                    setPower(ELBOW_POWER);
                }
            }

            @Override
            public boolean isDone() {
                return initialized && !armBottom.getMotor().isBusy();
            }

            @Override
            public void stop(boolean interuppted) {
                armBottom.setPower(0);
            }
        };
    }

    public Command makeHorizontalExp() {
        return new Command() {

            private int targetPosition1;
            private int targetPosition2;

            private boolean initialized = false;

            @Override
            public void start() {
                int currentPosition = (int) getCurrentPosition();
                targetPosition1 = currentPosition - DESIRED_DIFFERENCE_DOWN2;
                targetPosition2 = currentPosition - DESIRED_DIFFERENCE_UP;

                //armBottom.getMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //armBottom.getMotor().setTargetPosition(targetPosition);
                armBottom.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


               // armBottom.setPower(ELBOW_POWER);
                initialized = true;
            }

            @Override
            public void  update() {
                if ( getCurrentPosition() > targetPosition1 ) {
                    setPower(-1 * ELBOW_POWER);
                } else {
                    if (getCurrentPosition() >=  targetPosition2) {
                        setPower(GOING_DOWN_POWER);
                    }
                }
            }

            @Override
            public boolean isDone() {
                return initialized && !armBottom.getMotor().isBusy();
            }

            @Override
            public void stop(boolean interupted) {
                armBottom.setPower(0);
            }
        };
    }

    public  double getCurrentPosition() {
        return armBottom.getCurrentPosition();
    }
}
