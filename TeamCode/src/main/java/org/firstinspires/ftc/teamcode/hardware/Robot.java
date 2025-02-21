package org.firstinspires.ftc.teamcode.hardware;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import  org.firstinspires.ftc.teamcode.hardware.Slides;
import  org.firstinspires.ftc.teamcode.helpers.GamepadEx;
public class Robot {
    public Slides slides = Slides.INSTANCE;

    private Robot() {};
   /* public void init(HardwareMap hwMap) {
        slides.init(hwMap);
    } */



    public static final Robot INSTANCE = new Robot();





}
