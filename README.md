# Alfred The Robot - Robot Script Command

### Reference Documentation
This is a simple API for Robot Command Application
Current implementation is for a grid 5x5 
Robot is placed in position (0,0) directed east.

We implemented the server in a way that the position/direction of the robot stays persisted in memory across api requests, as long as the server is running.

To use the API, using backend only:
http://localhost:8080/robot/move

We used a POST method:
```bash
curl -X POST -H "Content-Type: text/plain" --data "this is raw data" http://localhost:8080/robot/move
```
### Frontend
To test the frontend go to this reposistory:


  https://github.com/yaradal/robot-FrontEnd.git

I did very basic testing, not comprenehnsive

