package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

@Config
public class Slides extends Subsystem {
    public static final Slides INSTANCE = new Slides();


    private Slides() { }
    public static final double MAX_SLIDE_UP_SPEED = 1;
    public static final double MAX_SLIDE_DOWN_SPEED = .5;
    public static final double MAX_SLIDE_HEIGHT = 2400;
    // PID constants kP, kI, and kD
    public static double kP = 0.001;
    public static double kI = 0.55;
    public static double kD = 0;
    public static int THRESHOLD = 50; // If the slides are within this threshold of the target position, they are considered to be at the target position
    public static double HOLD_POWER = 0.1; // The power needed to not move, but still counteract gravity
    private MotorEx slideRight; // The  motor that control the slides
    private MotorEx slideLeft; // The  motor that control the slides

    public  static double  ELBOW_POWER = 0.7;


    //  private final PIDFController controller = new PIDFController(new PIDCoefficients(.005, 0.2, 0.0));


  /*  public void init(HardwareMap hwMap){
        slide = hwMap.dcMotor.get("Slide");

     //   controller = new PIDController(kP, kI, kD);
       // controller.setTolerance(THRESHOLD);
    } */

    @Override
    public  void initialize() {
        slideRight = new MotorEx("slidesRight");
   //     slideRight.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     //   slideRight.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideLeft = new MotorEx("slidesLeft");
       // slideLeft.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       // slideLeft.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

       // controller.setTolerance(THRESHOLD);
    }

    public Command setPower(double i) {
        return new InstantCommand(()-> {
            slideRight.setPower(i);
            slideLeft.setPower(-i);
        });
    }

    public  Command moveUpManual() {
            if (getCurrentPosition() < MAX_SLIDE_HEIGHT) {
               return setPower(ELBOW_POWER);
        }
            return  new InstantCommand( ()->{} );
    }

    public  Command moveDownManual() {
            if (getCurrentPosition() >= 0) {
              return   setPower(-1*ELBOW_POWER);
            }
        return  new InstantCommand( ()->{} );
    }

    @NonNull
    @Override
    public Command getDefaultCommand() {
        return     setPower(0);
    }
    public  double getCurrentPosition() {
        return slideRight.getCurrentPosition();
    }

}
