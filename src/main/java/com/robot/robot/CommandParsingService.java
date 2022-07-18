package com.robot.robot;

import com.robot.robot.Commands.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class CommandParsingService {


    public List<CommandInterface> parseText(String commandScript, int gridRows, int gridColumns) throws InvalidInputException {
        List<CommandInterface> commandList = new ArrayList<CommandInterface>();

        Scanner scanner = new Scanner(commandScript);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            //validation - skip empty lines
            if (words.length == 0) {
                continue;
            }
            try {
                RobotCommand.Name command = RobotCommand.Name.valueOf(words[0]);
                switch (command) {
                    case POSITION:
                        //position expects 4 parameters
                        if (words.length != 4) {
                            throw new InvalidInputException("Error parsing, Position command should include 4 parameters");
                        }
                        int x = Integer.parseInt(words[1]);
                        int y = Integer.parseInt(words[2]);
                         if (x < 0  || x > gridRows || y < 0  || y > gridColumns){
                        throw new InvalidInputException("Error parsing, Position maximum is "+ gridColumns + " Position minimum is 0");
                        }
                        commandList.add(new Position(RobotCommand.Direction.valueOf(words[3]), Integer.parseInt(words[1]), Integer.parseInt(words[2])));
                        break;

                    case FORWARD:
                        //Forward expects 2 parameters
                        // in case forward steps is more than 5, we will do modulo 5
                        if (words.length != 2 || Integer.parseInt(words[1]) < 0) {
                            throw new InvalidInputException("Error parsing, Forward command should include 2 parameters ");
                        }
                        commandList.add(new Forward(Integer.parseInt(words[1])));
                        break;
                    case TURNAROUND:
                        if (words.length != 1) {
                            throw new InvalidInputException("Error parsing,Turnaround command should include 1 parameters");
                        }
                        commandList.add(new TurnAround());
                        break;
                    case WAIT:
                        if (words.length != 1) {
                            throw new InvalidInputException("Wait command is not correct");
                        }
                        commandList.add(new Wait());
                        break;
                    case LEFT:
                        if (words.length != 1) {
                            throw new InvalidInputException("Left command is not correct");
                        }
                        commandList.add(new Left());
                        break;
                    case RIGHT:
                        if (words.length != 1) {
                            throw new InvalidInputException("Right command is not correct");
                        }
                        commandList.add(new Right());
                        break;
                }
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
            }

        }
        scanner.close();
        return commandList;
    }

}
class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
    public InvalidInputException(String message, Exception ex) {
        super(message, ex);
    }

}
