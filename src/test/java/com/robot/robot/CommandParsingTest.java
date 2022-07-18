package com.robot.robot;

import com.robot.robot.Commands.Forward;
import com.robot.robot.Commands.RobotCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommandParsingTest {

    @Autowired
    private  CommandParsingService commandParsingService;

    @Test
    public  void testParseText(){
        String script = "FORWARD 3";
        List<CommandInterface> commandList = commandParsingService.parseText(script, 5, 5);
        CommandInterface command = new Forward(3);
        Assert.assertEquals(commandList.get(0).getName(), command.getName());
        Assert.assertEquals(((Forward) command).getStepsForward(),((Forward) (commandList.get(0))).getStepsForward());

        final String commandInvalid = "POSITION";
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            commandParsingService.parseText(commandInvalid, 5, 5);
        });
        Assert.assertEquals(exception.getMessage(),"Error parsing, Position command should include 4 parameters" );
    }
}
