# **Games Launcher**

### DAM 1

#### *by: [Anxo Fdez](https://github.com/Anx0Fdez) & [Ana Valladares](https://github.com/anavalladaresg)*

----

_This project is a sophisticated game launcher, designed to allow users to incorporate and execute their personal collection of .exe game files, while also featuring a dedicated section for mini-games, exclusively developed by our team._

## Current Progress
- User interface with login, registration, and sign up panels.
- Login, registration, and sign up logic.
- Basic structure of the `Game`, `User`, `Library`, `GameManager`, `SignInController`, `SignUpController`, `SignUpRoundedPanel`, `SignInRoundedPanel`, `RoundedBorder`, `PlaceHolderTextField`, `PlaceHolderPasswordField`, `LibraryController`, `UIController` and `Main` classes.
- Implementation of custom UI components like `PlaceHolderTextField`, `PlaceHolderPasswordField`, `SignUpRoundedPanel`, `SignInRoundedPanel`, and `RoundedBorder`.
- Implementation of controllers for sign in, sign up, and library views.
- Basic game management functionality in the `GameManager` class.
- User login functionality in the `User` class.
- Library view functionality in the `LibraryController` class.

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