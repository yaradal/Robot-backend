package com.robot.robot;

import com.robot.robot.Commands.RobotCommand;

public class Robot {
    private int x;
    private int y;
    private RobotCommand.Direction direction;


    public Robot(int y, int x, RobotCommand.Direction direction) {
        this.y = y;
        this.x = x;
        this.direction = direction;

    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public RobotCommand.Direction getDirection() {
        return this.direction;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setDirection(RobotCommand.Direction direction) {
        this.direction = direction;
    }



}
