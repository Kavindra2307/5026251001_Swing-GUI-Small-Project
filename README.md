# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information

| Field       | Value                              |
|-------------|------------------------------------|
| Name        | Abi Ahmad Kavindra Hendika         |
| Student ID  | 5026251001                         |
| Class       | ES234211 – Programming Fundamental |

---

## Project Description

This project is a simple **Tic-Tac-Toe** game built using **Java Swing GUI**.  
The player (X) plays against the computer (O) on a 3×3 board.  
The application includes a login system, game statistics tracking, and a Top 5 Scorers leaderboard — all backed by a MySQL database with a single table.

---

## Features

- Login using credentials stored in the database
- Play Tic-Tac-Toe against the computer using a Swing GUI
- Computer uses basic strategy (win/block/center/random)
- Record wins, losses, draws, and score after each game
- Display personal statistics
- Display Top 5 Scorers using JTable (ordered by score, then wins)

## Scoring System

| Result | Score Change |
|--------|-------------|
| Win    | +10 points  |
| Draw   | +3 points   |
| Lose   | +0 points   |

---

## Database

- **DBMS:** MySQL
- **Database name:** `game_project`
- **Table:** `players` (one table only)

### Table Schema

| Column   | Type         | Description               |
|----------|--------------|---------------------------|
| id       | INT PK AI    | Auto-increment primary key |
| username | VARCHAR(50)  | Unique login name          |
| password | VARCHAR(100) | Plain-text password        |
| wins     | INT          | Number of wins             |
| losses   | INT          | Number of losses           |
| draws    | INT          | Number of draws            |
| score    | INT          | Total accumulated score    |

---

## How to Run

### Prerequisites
- Java JDK 8 or higher
- MySQL Server running locally
- MySQL Connector/J (JDBC driver JAR)

### Step 1 — Create the database

Open MySQL and run:

```sql
source /path/to/database/schema.sql
```

Or manually execute the contents of `database/schema.sql`.

### Step 2 — Configure database credentials

Open `src/DatabaseManager.java` and edit:

```java
private static final String URL = "jdbc:mysql://localhost:3306/game_project?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "";  // your MySQL password
```

### Step 3 — Add the JDBC driver

Download **MySQL Connector/J** and place the `.jar` file in the `lib/` folder.

### Step 4 — Compile

```bash
cd "C:\My Apps\JavaProject"
javac -cp "lib/mysql-connector-j-*.jar" -d out src/*.java
```

### Step 5 — Run

```bash
java -cp "out;lib/mysql-connector-j-*.jar" Main
```

---

## Class Explanation

| Class | Responsibility |
|-------|---------------|
| `Main` | Entry point — starts the application by opening LoginFrame |
| `DatabaseManager` | Manages JDBC connection to MySQL |
| `Player` | Model class — stores player data (id, username, wins, losses, draws, score) |
| `PlayerService` | Service class — handles login, statistics update, and Top 5 query |
| `GameLogic` | Logic class — handles board state, move validation, win/draw detection, and computer AI |
| `LoginFrame` | Swing window for username/password login |
| `MainMenuFrame` | Swing window for navigating to game, statistics, and leaderboard |
| `GameFrame` | Swing window for playing Tic-Tac-Toe |
| `StatisticsFrame` | Swing window showing the logged-in player's statistics |
| `TopScorersFrame` | Swing window showing Top 5 players in a JTable |

---

## Screenshots

In Screenshots Folder

---

## Links

- GitHub: https://github.com/Kavindra2307/5026251001_Swing-GUI-Small-Project
- YouTube Demo: https://youtu.be/GENPGpaAZ0Q
