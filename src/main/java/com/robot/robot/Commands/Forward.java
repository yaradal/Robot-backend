package com.robot.robot.Commands;

public class Forward  extends RobotCommand {
    int stepsForward;

    public Forward(int stepsForward){
        super(RobotCommand.Name.FORWARD);
        this.stepsForward = stepsForward;
    }

    public int getStepsForward() {
        return stepsForward;
    }
}
