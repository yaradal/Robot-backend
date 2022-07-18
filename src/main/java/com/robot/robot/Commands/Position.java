package com.robot.robot.Commands;


public  class Position extends RobotCommand {
        Direction direction;
        int row;
        int column;


    public Position(Direction direction, int row, int column) {
            super(Name.POSITION);
            this.direction = direction;
            this.row = row;
            this.column = column;
        }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }
}

