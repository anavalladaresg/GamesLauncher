## Games Launcher

### DAM 1

#### *by: [Anxo Fdez](https://github.com/Anx0Fdez) & [Ana Valladares](https://github.com/anavalladaresg)*

----

_The Games Launcher project continues to evolve, with significant strides made in the development of the user interface and the underlying logic._

## Current Progress
- Completed the user interface for the sign-up panel, including the design and implementation of a custom button with a rounded border.
- Implemented the logic for the sign-up process, including validation of alphanumeric usernames and passwords, and checking for existing users in the database.
- Enhanced the user experience by adding keyboard listeners to the username and password fields, allowing the user to press the enter key to trigger the sign-up button.
- Added mouse listeners to the sign-up button to change its color and cursor when the mouse enters and exits the button area.
- Developed methods to display the sign-in screen and the main menu.
- Continued to refine the structure and functionality of the `Game`, `User`, `Library`, `GameManager`, `SignInController`, `SignUpController`, `SignUpRoundedPanel`, `SignInRoundedPanel`, `RoundedBorder`, `PlaceHolderTextField`, `PlaceHolderPasswordField`, `LibraryController`, `UIController` and `Main` classes.
- Further developed the custom UI components like `PlaceHolderTextField`, `PlaceHolderPasswordField`, `SignUpRoundedPanel`, `SignInRoundedPanel`, and `RoundedBorder`.
- Expanded the functionality of the `GameManager` class to manage the game collection.
- Enhanced the `User` class to handle user login functionality.
- Improved the `LibraryController` class to manage the library view functionality.

The team continues to work diligently on the Games Launcher project, with a focus on enhancing the user experience and expanding the functionality of the game management system.

## Class Diagram

```mermaid
classDiagram
    class Game {
        -int gameId
        -String gameName
        -String gameDescription
        -String gameGenre
        -String gameImage
        -String gameCoverImage
        -String exeLocation
        -String folderLocation
        +void setGameName(String)
        +void setGameDescription(String)
        +void setGameGenre(String)
        +void setGameImage(byte[])
        +void setGameCoverImage(String)
        +void setExeLocation(String)
        +void setFolderLocation(String)
    }

    class User {
        -int userId
        -String username
        -String password
        -String email
        -List~Game~ library
        +boolean login(String, String)
        +void addGameToLibrary(Game)
        +void removeGameFromLibrary(Game)
    }

    class LibraryController {
        -JFrame frame
        -User currentUser
        +LibraryController()
        +void displayLibrary()
        +void viewGameDetails(Game)
        +byte[] convertImageToBytes(String)
    }

    class DatabaseHandler {
        -Connection conn
        +void connect()
        +void addUser(String, String)
        +boolean userExists(String)
        +String getPassword(String)
        +void addGame(Game)
        +List~Game~ getGames()
    }

    class Main {
        +void main(String[] args)
    }

    class RoundedBorder {
        -Color color
        -int cornerRadius
        +RoundedBorder(Color, int)
        +Shape getBorderShape(int, int, int, int)
        +void paintBorder(Component, Graphics, int, int, int, int)
    }

    class SignInController {
        -JFrame frame
        -User currentUser
        -Color purple
        +SignInController()
        +void displayMainMenu()
        +void viewGameDetails(Game)
        +void createAndConfigureSignInComponents(JPanel)
        +void createComponentsPanel()
        +JFrame createFrame()
        +void displaySignUpScreen()
        +Color getPurple()
    }

    class SignUpController {
        -JFrame frame
        -User currentUser
        -Color purple
        +SignUpController()
        +void displaySignUpScreen()
        +void createAndConfigureSignUpComponents(JPanel)
        +void createComponentsPanel()
        +JFrame createFrame()
        +Color getPurple()
    }

    class GameManager {
        -List~Game~ games
        +void addGame(Game)
        +void removeGame(Game)
        +List~Game~ getGames()
    }

    class UIController {
        -JFrame frame
        -User currentUser
        +UIController()
        +void displayUI()
        +void createAndConfigureUIComponents(JPanel)
        +void createComponentsPanel()
        +JFrame createFrame()
    }

    class Library {
        -ArrayList~Game~ games
        +void addGame(Game)
        +ArrayList~Game~ getGames()
    }

    class PlaceholderPasswordField {
        -String placeholder
        +void setPlaceholder(String)
        +String getPlaceholder()
    }

    class PlaceholderTextField {
        -String placeholder
        +void setPlaceholder(String)
        +String getPlaceholder()
    }

    class SignInRoundedPanel {
        -Color color
        -int cornerRadius
        +SignInRoundedPanel(Color, int)
        +Shape getBorderShape(int, int, int, int)
        +void paintBorder(Component, Graphics, int, int, int, int)
    }

    class SignUpRoundedPanel {
        -Color color
        -int cornerRadius
        +SignUpRoundedPanel(Color, int)
        +Shape getBorderShape(int, int, int, int)
        +void paintBorder(Component, Graphics, int, int, int, int)
    }

    User --> LibraryController : uses
    User --> Game : contains
    LibraryController --> Game : manages
    LibraryController --> DatabaseHandler : uses
    Main --> LibraryController : initializes
    SignInController --> User : uses
    SignInController --> RoundedBorder : uses
    SignInController --> SignInRoundedPanel : uses
    SignUpController --> User : uses
    SignUpController --> RoundedBorder : uses
    SignUpController --> SignUpRoundedPanel : uses
    GameManager --> Game : manages
    UIController --> User : uses
    UIController --> RoundedBorder : uses
    Library --> Game : contains
    PlaceholderPasswordField --> User : used by
    PlaceholderTextField --> User : used by