# **Games Launcher**

### DAM 1

#### *by: [Anxo Fdez](https://github.com/Anx0Fdez) & [Ana Valladares](https://github.com/anavalladaresg)*

---

This project is a game launcher developed in Java. Currently, we have implemented the user interface, login logic, and sign up logic.

## Current Progress

- User interface with login, registration, and sign up panels.
- Login, registration, and sign up logic.
- Basic structure of the `Game`, `User`, `Library`, `GameManager`, `SignInController`, `SignUpController`, `SignUpRoundedPanel`, `SignInRoundedPanel`, `RoundedBorder`, `PlaceHolderTextField`, `PlaceHolderPasswordField`, `LibraryController`, `UIController` and `Main` classes.

## Class Diagram

```mermaid
classDiagram
    class Game {
        -int gameId
        -String title
        -String description
        -String genre
        -LocalDate releaseDate
        -String developer
        -double price
        -double rating
        +String getDetails()
        +void updateRating(double)
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

    class Library {
        -List~Game~ games
        +void addGame(Game)
        +void removeGame(Game)
        +List~Game~ searchGame(String)
    }

    class GameManager {
        -List~Game~ availableGames
        +void loadGames()
        +void updateGame(Game)
        +Game getGameDetails(int)
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
    }

    class SignUpController {
        -JFrame frame
        -User currentUser
        +SignUpController()
        +void displaySignInScreen()
        +void displayMainMenu()
        +void viewGameDetails(Game)
        +void createAndConfigureSignInComponents(JPanel)
        +void createComponentsPanel()
        +JFrame createFrame()
    }

    class SignUpRoundedPanel {
        -Color backgroundColor
        -int cornerRadius
        +SignUpRoundedPanel(LayoutManager, int, Color)
        +void paintComponent(Graphics)
    }

    class SignInRoundedPanel {
        -Color backgroundColor
        -int cornerRadius
        +SignInRoundedPanel(LayoutManager, int, Color)
        +void paintComponent(Graphics)
    }

    class RoundedBorder {
        -Color color
        -int cornerRadius
        +RoundedBorder(Color, int)
        +Shape getBorderShape(int, int, int, int)
        +void paintBorder(Component, Graphics, int, int, int, int)
    }

    class PlaceHolderTextField {
        -String prompt
        +PlaceHolderTextField(int)
        +void setPrompt(String)
    }

    class PlaceHolderPasswordField {
        -String prompt
        +PlaceHolderPasswordField(int)
        +void setPrompt(String)
    }

    class LibraryController {
        -JFrame frame
        -User currentUser
        +LibraryController()
        +void displayLibrary()
        +void viewGameDetails(Game)
    }

    class Main {
        +void main(String[] args)
    }

    User --> Library : contains
    GameManager --> Game : manages
    GameManager --> Library : manages
    SignInController --> User : uses
    SignInController --> GameManager : uses
    SignInController --> SignInRoundedPanel : uses
    SignInController --> RoundedBorder : uses
    SignInController --> PlaceHolderTextField : uses
    SignInController --> PlaceHolderPasswordField : uses
    SignUpController --> User : uses
    SignUpController --> GameManager : uses
    SignUpController --> SignUpRoundedPanel : uses
    SignUpController --> RoundedBorder : uses
    SignUpController --> PlaceHolderTextField : uses
    SignUpController --> PlaceHolderPasswordField : uses
    LibraryController --> User : uses
    LibraryController --> GameManager : uses
    Main --> SignInController : initializes
    Main --> SignUpController : initializes
    Main --> LibraryController : initializes