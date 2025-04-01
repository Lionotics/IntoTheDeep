package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToSeperatePositions;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import java.util.HashMap;
import java.util.Map;

@Config
public class Intake extends Subsystem {
    public  static  final Intake  INSTANCE = new Intake();
    private  Intake() {}

    Servo hand;
    Servo pivot;


    @Override
    public  void initialize() {
        hand = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "Hand");
        pivot = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "pivot");

        }

    public  Command setHandPosition(double pos) {
            return new ServoToPosition(hand, pos, this);
        }


    public  Command setPivotPosition(double pos) {
        return new ServoToPosition(pivot, pos, this);
    }

    public Command setIntake(double handPos, double pivotPos) {
        return new MultipleServosToSeperatePositions(new HashMap<>(Map.of(hand, handPos, pivot, pivotPos)), this);
    }



}
        // controller.setTolerance(THRESHOLD);







