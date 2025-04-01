package org.firstinspires.ftc.teamcode.hardware;

import java.util.List;

public class StateMachine {
    public enum State {
        START, GRAB, HOVER
    }

    private final List<State> sampleLine = List.of(State.START, State.GRAB, State.HOVER);
    private  final List<State> specimenLine = List.of();
    private int currentIndex = -1;
    private State currentState = State.START;
    private List<State> currentLine = sampleLine;

    public void next() {
        int nextIndex = (currentIndex + 1) % currentLine.size();
        currentState = currentLine.get(nextIndex);
        currentIndex = nextIndex;
    }

    public void previous() {
        int nextIndex = (currentIndex - 1 + currentLine.size()) % currentLine.size();
        currentState = currentLine.get(nextIndex);
        currentIndex = nextIndex;
    }

    public void switchToSpecimen() {
        currentLine = specimenLine;

        currentState = currentLine.get(0);
        currentIndex = 0;

    }

    public void switchToSample() {
        currentLine = sampleLine;
        currentState = currentLine.get(0);
        currentIndex = 0;
    }

    public State getCurrentState() {
        return currentState;
    }

    public List<State> getCurrentLine() {
        return currentLine;
    }

    public void flush() {
        currentIndex = -1;
        currentState = State.START;
        currentLine = sampleLine;
    }

    public void startAuto() {
        currentLine = sampleLine;
        currentState = currentLine.get(3);
        currentIndex = 3;
    }

}
