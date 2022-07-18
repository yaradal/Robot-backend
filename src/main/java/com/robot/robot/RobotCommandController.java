package com.robot.robot;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/robot")
public class RobotCommandController {

    private GridService gridService;

    public RobotCommandController(GridService gridService) {
        this.gridService = gridService;
    }

    @PostMapping(value = "/move", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Robot moveRobot(@RequestBody final String moveScript) {
        try {
            Robot robot = gridService.MoveRobot(moveScript);
            return robot;
        }catch (InvalidInputException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }

}
