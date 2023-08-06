
# Welcome to TNT
![image](https://cdn.discordapp.com/attachments/1111436485594464352/1137570283763544184/Untitled_Artwork_26.png)
## Overview Of The Project

The Pomodoro Timer with Task List is a productivity application designed to help users efficiently
manage their study or work sessions using the popular Pomodoro technique. The app combines a timer
feature, allowing users to work in focused 25-minute study sessions, followed by 5-minute breaks.
Additionally, it provides a task list feature for users to organize their tasks and mark them as
completed.

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
- Timer Continuation Prompt: The user can choose to continue the study session without a break, making the app flexible to individual preferences.
- User Signin Registration Prompt: Prompt displays authentication errors when user attempts to signin or register, providing guidance on how the user can successfully access the app content.


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

## Design patterns
- Observer Pattern: This can be seen in the timer function, where when the timer finishes a break session, it automatically notifies the UI component via a prompt.
- 

