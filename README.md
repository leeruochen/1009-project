## Abstract engine

#### features
- Entity Manager
- Event Manager
- Scene Manager
- Movement Manager
- Collision Manager
- Map Manager
- Camer Manager

#### project structure
```
1009-project/
├── README.md                                     # This overview file
├── core/                                         # Source code 
│   ├── src/main/java/github/com_1009project      # Main
│       ├── abstractEngine                        # Abstract Engine files
│       ├── logicEngine                           # Logic Engine files
│       ├── GameMaster.java                       # Main game/simulation loop
```


to run
```bash
./gradlew lwjgl3:run
or however you run gradle builds
```