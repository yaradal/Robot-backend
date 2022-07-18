package com.robot.robot;

import com.robot.robot.Commands.RobotCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class GridServiceTest {
    @Autowired
    GridService gridService;

    @Test
    public  void testMoveRobot(){
        String commandScript = "POSITION 1 3 EAST";
        Robot robot = gridService.MoveRobot(commandScript);
        Assert.assertEquals(robot.getX() ,  1);
        Assert.assertEquals(robot.getY() ,  3);
        Assert.assertNotEquals(robot.getY() ,  7);

        final String commandInvalid = "POSITION 1 7 EAST";
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            gridService.MoveRobot(commandInvalid);
        });
        Assert.assertEquals(exception.getMessage(),"Error parsing, Position maximum is 5 Position minimum is 0" );
    }

}
