# Tic Tac Toe

A Java-based Tic Tac Toe game featuring two-player and single-player modes, multiple difficulty levels, and a graphical user interface built with Java Swing.

## Features

- **Two-Player Mode:** Play against another person on the same computer.
- **Single-Player Mode:** Play against a computer opponent.
- **Multiple AI Difficulties:**
  - Random: Makes randomly selected moves.
  - Defensive: Prioritizes blocking the player's winning moves.
  - Smart: Prioritizes winning moves, blocking the opponent, and selecting strategic positions.
- **Flexible Starting Order:** Choose whether the computer plays as X or O.
- **Undo and Redo:** Reverse and replay previous moves.
- **Save and Restore:** Save the current board state and restore it later.
- **Win and Draw Detection:** Automatically detects winning combinations and draws.
- **Graphical Interface:** Interactive 3×3 game board with visual feedback and winning-line highlighting.
- **Restart Option:** Start a new game at any time.

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- Design Patterns

## Design Patterns

The project incorporates several design patterns to improve code organization, maintainability, and extensibility:

| Design Pattern | Application |
|---|---|
| MVC (Model-View-Controller) | Separates game logic, user interface, and input handling. |
| Strategy | Implements different computer opponent difficulty levels. |
| Observer | Notifies the game when the board changes to update its status. |
| State | Represents board cells using X, O, and EMPTY states. |
| Memento | Saves and restores previous board configurations. |

## How to Run

### Prerequisites

- Java Development Kit (JDK) installed.
- A Java-compatible IDE or terminal.

### Compile

Open a terminal in the project directory and run:

```bash
javac -d bin (Get-ChildItem -Path src -Filter *.java -Recurse).FullName
```

### Run

```bash
java -cp bin app.Main

```

## How to Play

1. Launch the application.
2. Choose between Two-Player Mode and Single-Player Mode.
3. If playing against the computer, select an AI difficulty.
4. Choose whether the computer plays first as X or second as O.
5. Take turns selecting cells on the 3×3 board.
6. The first player to align three marks horizontally, vertically, or diagonally wins.
7. If all cells are occupied without a winning combination, the game ends in a draw.