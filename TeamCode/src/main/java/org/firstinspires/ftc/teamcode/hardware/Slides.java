package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.helpers.PIDController;

@Config
public class Slides{
    public enum  LiftState {HOLDING,MANUAL_UP, MANUAL_DOWN,AUTO_MOVE};
    public static final double MAX_SLIDE_UP_SPEED = 1;
    public static final double MAX_SLIDE_DOWN_SPEED = .5;
    public static final double MAX_SLIDE_HEIGHT = 2225;
    // PID constants kP, kI, and kD
    public static double kP = 0.001;
    public static double kI = 0.55;
    public static double kD = 0;
    public static int THRESHOLD = 50; // If the slides are within this threshold of the target position, they are considered to be at the target position
    public static double HOLD_POWER = 0.1; // The power needed to not move, but still counteract gravity
    private DcMotor slide; // The two motors that control the slides
    private PIDController controller; // The PID controller for the slides
    public static LiftState liftState = LiftState.HOLDING; // The current state of the slides

    public void init(HardwareMap hwMap){
        slide = hwMap.dcMotor.get("Slide");

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller = new PIDController(kP, kI, kD);
        controller.setTolerance(THRESHOLD);
    }

    public  void loop(){


        switch (liftState) {
            case HOLDING:
                slide.setPower(0.01);
                break;
            case MANUAL_DOWN:
                slide.setPower(-0.5);
                break;

            case MANUAL_UP:
                if (getCurrentPosition() >= MAX_SLIDE_HEIGHT-THRESHOLD) {
                    liftState =  LiftState.HOLDING;
                } else {
                    slide.setPower(0.5);
                }
                break;

        }
    }

    public  void setLiftState(LiftState newLiftState)  {
        liftState = newLiftState;
    }

    public void manualUp() {
        setLiftState(LiftState.MANUAL_UP);
    }

    public void manualDown() {
        setLiftState(LiftState.MANUAL_DOWN);
    }

    public void hold() {
            setLiftState(LiftState.HOLDING);
    }

    public  float getCurrentPosition() {
        return slide.getCurrentPosition();
    }

}
