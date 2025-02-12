package org.firstinspires.ftc.teamcode.hardware;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import  org.firstinspires.ftc.teamcode.hardware.Slides;
import  org.firstinspires.ftc.teamcode.helpers.GamepadEx;
public class Robot {
    public Slides slides = new Slides();

    public void init(HardwareMap hwMap) {
        slides.init(hwMap);
    }

    private static Robot robotInstance = new Robot();

    public static Robot getInstance() {
        return robotInstance;
    }



}
