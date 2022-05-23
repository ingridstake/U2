# U2

This is a project in the course DAT257. 
The purpose of the project is to deliver new version of the Tickster website containing a proof of concept of a priliminary categorizarion algorithm based on the name and description of differnt events from the API.

# Team members:

Julia Ekeblad - juliaeke and itggot-julia-ekeblad (on github)

Elvina Fahlgren - elvinafalgren (on github)

Olivia Månström - olimanstrom (on github)

Tove Nilsson - ToveNilsson6569 (on github)

Ingrid Stake - ingridstake (on github)

Martin Toremark - toremark (on github)

Kevin Zhao - LegendarySkinnyP (on github)


Everybody worked together, we started utilizing the co-author feature some time into the project, so the contribution stats here does not accurately represent the reality.


# Path to Team Reflections:

https://github.com/ingridstake/U2/tree/main/Documentation/TeamReflections (U2/Documentation/TeamReflections)

Each team reflection is named after the sprint in question.

# Path to Individual Reflections:

https://github.com/ingridstake/U2/tree/main/Documentation/IndividualReflections (U2/Documentation/IndividualReflections)

The individual reflections of each member is found in the folder that holds the member's name.

# Path to Scrumboard:

https://trello.com/b/K8JLEl0z/scrum-board

# To run the application

## Set up backend

  - Open the backend project in intelliJ (./U2/project/backend)
  - File -> Project Structure -> Project: Choose Project SDK ( version 18 or later ) and Language level 17
  - Right click on pom.xml and choose "Add as Maven Project".
  - Run BackenApplication.java (./src/main/java/com.U2.backend)
  - For more information, see BackendGuide.txt

## Set up frontend

  - Open the frontend in Visual Studio Code (./U2/project/frontend)
  - Necessary libraries to run the application:
    - npm
    - if you are missing node_modules folder in the frontend folder, run:
    	- npm install
    - react-bootstrap
    	- npm install react-bootstrap bootstrap
    - react-multi-carousel
    	- npm install react-multi-carousel
    - axios
    	- npm install axios
    - npm install react-router-dom
  - Run by typing "npm start" in terminal
  - For more information, see FrontendGuide.txt
