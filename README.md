# **Games Launcher**

### DAM 1

#### *by: [Anxo Fdez](https://github.com/Anx0Fdez) & [Ana Valladares](https://github.com/anavalladaresg)*

---

This project is a game launcher developed in Java. Currently, we have implemented the user interface and the login logic.

## Current Progress

- User interface with login and registration panel.
- Login and registration logic.
- Basic structure of the `Game`, `User`, `Library`, `GameManager`, `UIController` and `Main` classes.

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
        -String publisher
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

    class CustomPasswordField {
        -int columns
        +CustomPasswordField(int)
    }

    class CustomTextField {
        -int columns
        -Border defaultBorder
        -Border focusBorder
        +CustomTextField(int)
    }

    class RoundedPanel {
        -int radius
        +RoundedPanel(int)
    }

    class UIController {
        -User currentUser
        -CustomTextField userText
        -CustomPasswordField passwordText
        -RoundedPanel componentsPanel
        +void displayLoginScreen()
        +void displayMainMenu()
        +void viewGameDetails(Game)
    }

    class Main {
        +void main(String[] args)
    }

    User --> Library : contains
    GameManager --> Game : manages
    GameManager --> Library : manages
    UIController --> User : uses
    UIController --> GameManager : uses
    UIController --> CustomTextField : uses
    UIController --> CustomPasswordField : uses
    UIController --> RoundedPanel : uses
    Main --> UIController : initializes
```