package com.robot.robot.Commands;


import com.robot.robot.CommandInterface;

public class RobotCommand  implements CommandInterface {
    Name name;


    public RobotCommand(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }


    // the order of this enum is crucial for right positioning
    public enum Direction{
        EAST,
        SOUTH,
        WEST,
        NORTH
    }
    public enum Name {
        POSITION,
        FORWARD,
        TURNAROUND,
        WAIT,
        LEFT,
        RIGHT
    }


}




