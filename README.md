
# Welcome to TNT
![image](https://cdn.discordapp.com/attachments/1111436485594464352/1137570283763544184/Untitled_Artwork_26.png)
## Overview Of The Project

The Pomodoro Timer with Task List is a productivity application designed to help users efficiently
manage their study or work sessions using the popular Pomodoro technique. The app combines a timer
feature, allowing users to work in focused 25-minute study sessions, followed by 5-minute breaks.
Additionally, it provides a task list feature for users to organize their tasks and mark them as
completed.

## Prerequisites and Setup
Before you run the app, ensure you have met the following requirements:
- Android Studio is installed on your computer
### Clone the Repository
To get started with the project, you need to clone the repository. Open your terminal and execute the following command:
`git clone https://github.com/CSC207-2023Y-UofT/course-project-tnt.git`

### Setting up in Android Studio
1. Open Android Studio
2. Click on `File` -> `Open`
3. Navigate to the location where you cloned the repository and select the project's root folder.
4. Android Studio will take a moment to sync and load the project.

### Running the Project/App
1. Once the project is loaded, you might need to wait for the Gradle sync to finish. This process will download the required dependencies.
2. After the sync is complete, you can select a target device to run the app on. This could be an emulator or a physical device connected to your computer.
3. Click on the green "Run" button (usually a play icon) in the top menu or use the shortcut `Shift + F10`.
4. Android Studio will build the project and launch the app on the selected device.

## Features
1. Pomodoro Timer
- Study Session: The timer enables users to initiate a 25-minute study session to maintain focus and productivity.
- Break Reminder: After each study session, the app prompts the user with an option to take a 5-minute break, helping them relax and recharge.
- Continuation Prompt: The user can choose to continue the study session without a break, making the app flexible to individual preferences.
2. Task List
- Add Tasks: Users can add new tasks to the list with details like task name, to keep track of their to-do items.
- Editing Current Tasks: Swiping right on the task allows the user to edit their task description.
- Remove Completed Tasks: Once a task is completed, users can swipe left on the task and remove it from the task list.
3. User Authentication
- User Registration: Users can create a new account by registering with a unique username and password.
- User Login: Registered users can log in securely to access their task list.
- User Logout: User is brought back to the main page, where they are shown the options to either sign in or register.
4. Prompts
- User Sign-in and Registration: Prompt displays authentication updates, such as errors when user attempts to signin or register, providing guidance on how the user can successfully access the app content.
- Pomodoro Session Updates: Using the prompts, the user can choose to continue the study session without a break, making the app flexible to individual preferences.

## Note on SOLID and CA
### User signin and registration:
- SRP: Separating RegisterValidation and SigninValidation from RegisterUser and SigninUser classes
  Single Responsibility Principle. RegisterUser and SigninUser classes are responsible for handling the user interface and user interaction logic, such as capturing user input and displaying results.
  RegisterValidation and SigninValidation classes are responsible for validating the user input and ensuring it meets specific criteria for registration and login.
- OCP: If there's a need to change validation rules or add new validation logic, developers can do so by modifying the respective validation classes without altering the user interface code.
- DIP: DatabaseHelper or UserRepo do not depend on the specific entities database but instead rely on the abstractions provided by the UserRepo interface.
- Clean Architecture layer dependency:  Interface Layer (RegisterUser and SigninUser)
  These classes do not directly depend on the data layer. Instead, they depend on the RegisterValidation and SigninValidation use case layer to handle the validation logic.
  Use Case Layer (DatabaseHelper or UserRepo)
  These classes implement the UserRepo interface, providing a clear boundary between the domain layer and the data layer.
  The domain layer, including RegisterValidation and SigninValidation, only relies on the UserRepo interface, not on the specific database implementation. This allows for easy swapping of different data sources or repositories without affecting the domain logic.

### PomodoroTimer and VibratorHelper:
- SRP: VibratorHelper is a helper class which has single responsibility to control the vibrator funtion in the timer.
  PomodoroTimer has single responsibility as a countdown timer.
- OCP: There is no clear demonstration of OCP in these classes.
- LSP: It doesn’t involve inheritance or polymorphism so it doesn’t demonstrate LSP.
- ISP: There is no code being forced to depend on methods it does not use.
- DIP: High-level modules in PomodoroTimer have no imports from low-level modules.

### Prompt:
- SRP: The Prompt class adheres to the SRP as it focuses on a single responsibility: managing the presentation of prompt dialogs and avoids taking on additional responsibilities.
- OCP: The Prompt class adheres to the OCP as it is designed to be extended by subclasses for creating specific prompt dialogs with custom string parameters and button click behaviours (CustomPrompt). The Prompt class is closed to modifications since its main structure is fixed. New prompts can be added without changing the existing code
- LSP: The Prompt class adheres to the LSP as a subclass of Prompt should not introduce any issues when it is substituted as Prompt. A subclass of Prompt (CustomPrompt) only provides concrete implementations for button click events, so it can be used like any other Prompt.
- ISP: Not applicable as Prompt is an abstract class, not an interface
- DIP: The Prompt class adheres to DIP as it accepts a Context object via its constructor, which allows the Prompt class to depend on abstractions rather than concrete implementations

## Design patterns
- Observer Pattern: This can be seen in the timer function, where when the timer finishes a break session, it automatically notifies the UI component via a prompt.
- Factory Method: Prompt is an abstract class that serves as a "blueprint" for creating Android dialog prompts throughout the application. It has a constructor that takes parameters to initialize the prompt's properties, such as the title, dialog, names of the buttons and their functionalities. The CustomPrompt class, which extends Prompt, acts as a "creator/initializer" that creates instances of CustomPrompt based on the parameters passed to its constructors. Then, CustomPrompt would get called throughout the app with specific parameters according to each use case.
- Template Method: The Prompt abstract class defines a template method show() that provides a concrete algorithm for displaying the dialog prompt, while leaving specific actions to be taken on button clicks (onButton1Clicked() and onButton2Clicked()) left abstract, allowing subclasses to provide their own implementations.

## Test Coverage
![image](file:///Users/mariinakim/Desktop/Screenshot%202023-08-16%20at%2010.25.30%20PM.png)
The test coverage breakdown is as shown. Android Tests including all the instrumented tests that test the UI, database, entities, utils and databinding were unable to be added to the test coverage report.
This accounts for why these classes have a coverage of 0%.
