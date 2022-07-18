package com.robot.robot;

import com.robot.robot.Commands.Forward;
import com.robot.robot.Commands.Position;
import com.robot.robot.Commands.RobotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridService {

    @Autowired
    private CommandParsingService commandParsingService;
    private Robot robot;
    private static int gridRows = 5;
    private static   int gridColumns = 5;

    public GridService() {
        this.robot = new Robot(0, 0, RobotCommand.Direction.EAST);
    }
    public static int getGridRows() {
        return gridRows;
    }

    public static int getGridColumns() {
        return gridColumns;
    }

    //The app is not concurrency safe -> we would need to add a mutex and lock/unlock in the GridService
    public Robot MoveRobot(String commandScript) throws InvalidInputException{
        try {
            List<CommandInterface> CommandList = commandParsingService.parseText(commandScript, gridRows,gridColumns);
            for (CommandInterface command : CommandList) {
                switch (command.getName()) {
                    case POSITION:
                        setPosition(((Position) command).getRow(), ((Position) command).getColumn(), ((Position) command).getDirection());
                        break;
                    case RIGHT:
                        turn(1);
                        break;
                    case LEFT:
                        turn(-1);
                        break;
                    case TURNAROUND:
                        turn(2);
                        break;
                    case WAIT:
                        break;
                    case FORWARD:
                        stepForward(((Forward) command).getStepsForward());
                }
            }


        } catch (InvalidInputException e) {
            throw e;
        }
        return robot;

    }

    // TODO validation of state

// in case the forward steps goes over the board limits we won't fail, it will loop (using modulo the size)
    private void stepForward(int stepsForward) {
        switch (robot.getDirection()) {
            case EAST:
                robot.setX((robot.getX() + stepsForward) %gridRows );
                break;
            case WEST:
                robot.setX(Math.abs(((robot.getX() - stepsForward) % gridRows)));
                break;
            case NORTH:
                robot.setY(Math.abs((robot.getY() - stepsForward) % gridColumns));
                break;
            case SOUTH:
                robot.setY((robot.getY() + stepsForward) % gridColumns);
                break;
        }
    }

    // left -1 right +1 turnaround +2
    private void turn(int turns) {
        int nbDirections = RobotCommand.Direction.values().length;
        int newDirection = (robot.getDirection().ordinal() + nbDirections + turns) % nbDirections;
        robot.setDirection(RobotCommand.Direction.values()[newDirection]);
    }

    private void setPosition(int x, int y, RobotCommand.Direction direction) {
        robot.setX(x);
        robot.setY(y);
        robot.setDirection(direction);
    }

}
