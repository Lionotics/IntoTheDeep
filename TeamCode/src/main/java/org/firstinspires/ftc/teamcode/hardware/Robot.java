package org.firstinspires.ftc.teamcode.hardware;

public class Robot {
    public Slides slides = Slides.INSTANCE;

    private Robot() {};
   /* public void init(HardwareMap hwMap) {
        slides.init(hwMap);
    } */



    public static final Robot INSTANCE = new Robot();





}
