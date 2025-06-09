package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.hardware.Intake.newPos;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;

@Config
public class Kicker extends Subsystem {

    public static final Kicker INSTANCE = new Kicker();
    private Kicker() {}

    private boolean KickerAtHome = true;

    public static double HOME = 0.65;
    public static double KICK = 0.15;

    private Servo kicker;

    @Override
    public void initialize() {
        kicker = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "kicker");
        kicker.setPosition(HOME);

    }

    public Command toggleKickerCommand() {
        return new InstantCommand(() -> {
            KickerAtHome = !KickerAtHome;
            double newPosition = KickerAtHome ? HOME : KICK;
            kicker.setPosition(newPosition);
        }); // 👈 required to declare Intake as a requirement
    }

}
