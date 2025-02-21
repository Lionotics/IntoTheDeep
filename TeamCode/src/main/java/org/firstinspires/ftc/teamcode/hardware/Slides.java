package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

import org.firstinspires.ftc.teamcode.helpers.PIDController;


@Config
public class Slides extends Subsystem {
    public static final Slides INSTANCE = new Slides();


    private Slides() { }
    public static final double MAX_SLIDE_UP_SPEED = 1;
    public static final double MAX_SLIDE_DOWN_SPEED = .5;
    public static final double MAX_SLIDE_HEIGHT = 2225;
    // PID constants kP, kI, and kD
    public static double kP = 0.001;
    public static double kI = 0.55;
    public static double kD = 0;
    public static int THRESHOLD = 50; // If the slides are within this threshold of the target position, they are considered to be at the target position
    public static double HOLD_POWER = 0.1; // The power needed to not move, but still counteract gravity
    private MotorEx slide; // The  motor that control the slides

  //  private final PIDFController controller = new PIDFController(new PIDCoefficients(.005, 0.2, 0.0));


  /*  public void init(HardwareMap hwMap){
        slide = hwMap.dcMotor.get("Slide");

     //   controller = new PIDController(kP, kI, kD);
       // controller.setTolerance(THRESHOLD);
    } */

    @Override
    public  void initialize() {
        slide = new MotorEx("Slide");
        slide.getMotor().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // controller.setTolerance(THRESHOLD);
    }

    public  Command setPower(double value) {
        return  new InstantCommand( () -> {
            slide.setPower(value);
            return  null;
        });
    }

    public  double getCurrentPosition() {
        return slide.getCurrentPosition();
    }

}
