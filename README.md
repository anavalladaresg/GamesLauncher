## Games Launcher

### DAM 1

#### *by: [Anxo Fdez](https://github.com/Anx0Fdez) & [Ana Valladares](https://github.com/anavalladaresg)*

----

_The Games Launcher project has reached its final stage, with significant strides made in the development of the user interface and the underlying logic._

## Final Progress
- Completed the user interface for the sign-up panel, including the design and implementation of a custom button with a rounded border.
- Implemented the logic for the sign-up process, including validation of alphanumeric usernames and passwords, and checking for existing users in the database.
- Enhanced the user experience by adding keyboard listeners to the username and password fields, allowing the user to press the enter key to trigger the sign-up button.
- Added mouse listeners to the sign-up button to change its color and cursor when the mouse enters and exits the button area.
- Developed methods to display the sign-in screen and the main menu.
- Refined the structure and functionality of the `Game`, `User`, `Library`, `GameManager`, `SignInController`, `SignUpController`, `SignUpRoundedPanel`, `SignInRoundedPanel`, `RoundedBorder`, `PlaceHolderTextField`, `PlaceHolderPasswordField`, `LibraryController`, `UIController` and `Main` classes.
- Developed the custom UI components like `PlaceHolderTextField`, `PlaceHolderPasswordField`, `SignUpRoundedPanel`, `SignInRoundedPanel`, and `RoundedBorder`.
- Expanded the functionality of the `GameManager` class to manage the game collection.
- Enhanced the `User` class to handle user login functionality.
- Improved the `LibraryController` class to manage the library view functionality.

The team has worked diligently on the Games Launcher project, with a focus on enhancing the user experience and expanding the functionality of the game management system.

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
        +getGameId()
        +getGameName()
        +getGameDescription()
        +getGameGenre()
        +getGameImage()
        +getGameCoverImage()
        +getExeLocation()
        +getFolderLocation()
    }

    class User {
        -int userId
        -String username
        -String password
        -String email
        +getUserId()
        +getUsername()
        +getPassword()
        +getEmail()
        +login(String, String)
    }

    class LibraryController {
        -JFrame frame
        -User currentUser
        +getFrame()
        +getCurrentUser()
        +loadLibrary()
        +addGameToLibrary(Game game)
        +removeGameFromLibrary(Game game)
    }

    class AnimatedGifPanel {
        -ImageIcon gifBackground
        +getGifBackground()
        +setGifBackground(ImageIcon gifBackground)
    }

    class PlaceholderPasswordField {
        -String placeholder
        +getPlaceholder()
        +setPlaceholder(String placeholder)
    }

    class PlaceholderTextField {
        -String placeholder
        +getPlaceholder()
        +setPlaceholder(String placeholder)
    }

    class RoundedBorder {
        -Color color
        -int cornerRadius
        +getColor()
        +getCornerRadius()
    }

    class RoundedPanel {
        -Color color
        -int cornerRadius
        +getColor()
        +getCornerRadius()
    }

    class SignInRoundedPanel {
        -Color color
        -int cornerRadius
        +getColor()
        +getCornerRadius()
    }

    class SignUpRoundedPanel {
        -Color color
        -int cornerRadius
        +getColor()
        +getCornerRadius()
    }

    class SignInController {
        -JFrame frame
        -User currentUser
        -Color purple
        +getFrame()
        +getCurrentUser()
        +getPurple()
        +signIn(String username, String password)
    }

    class SignUpController {
        -JFrame frame
        -User currentUser
        -Color purple
        +getFrame()
        +getCurrentUser()
        +getPurple()
        +signUp(String username, String password, String email)
    }

    class UIController {
        -JFrame frame
        -User currentUser
        +getFrame()
        +getCurrentUser()
        +loadUI()
    }

    class Main {
        +main(String[] args)
    }

    class DatabaseHandler {
        -String DATABASE_URL
        -String DATABASE_USER
        -String DATABASE_PASSWORD
        -Connection conn
        +DatabaseHandler()
        +connect()
    }

    class GameDatabaseHandler {
        -Connection conn
        +GameDatabaseHandler()
        +connect()
        +addGame(Game game)
        +removeGame(Game game)
        +getGame(int gameId)
    }

    class UserDatabaseHandler {
        -Connection conn
        +UserDatabaseHandler()
        +connect()
        +addUser(User user)
        +removeUser(User user)
        +getUser(int userId)
    }

    User --> LibraryController : uses
    User --> Game : contains
    LibraryController --> Game : manages
    Main --> LibraryController : initializes
    SignInController --> User : uses
    SignInController --> RoundedBorder : uses
    SignInController --> SignInRoundedPanel : uses
    SignUpController --> User : uses
    SignUpController --> RoundedBorder : uses
    SignUpController --> SignUpRoundedPanel : uses
    UIController --> User : uses
    UIController --> RoundedBorder : uses
    PlaceholderPasswordField --> User : used by
    PlaceholderTextField --> User : used by
    SignInRoundedPanel --> RoundedPanel : extends
    SignUpRoundedPanel --> RoundedPanel : extends
    AnimatedGifPanel --> RoundedPanel : extends
    GameDatabaseHandler --> DatabaseHandler : extends
    UserDatabaseHandler --> DatabaseHandler : extends
```