# 📝 ToDo-List

## 🌎 Brief Overview

ToDo-List is a simple but powerful command-line application for managing your daily tasks. You can create a personal profile, add tasks with different priorities (High, Medium, Low), edit them, mark them as complete, and view your entire list in a clean, organized format.

The project is built with a clean architecture that separates the application's logic from the user interface, making it easy to understand and maintain.

---

## 🚀 Getting Started

This project is a `Java` project using `maven` as the build tool.

### 📦 3rd Party Dependencies

For the current command-line version, Maven handles all necessary dependencies. No external setup is required.

### 🛠 Installing `maven`

Install `maven` using your favorite package manager:

#### 🍏 macOS (Using [Homebrew](https://brew.sh))

1.  Install:
    ```bash
    brew install maven
    ```
2.  Verify Installation:
    ```bash
    mvn -v
    ```

#### 🐧 Linux

##### 🐧 Ubuntu/Debian-based

1.  Install:
    ```bash
    sudo apt update && sudo apt install maven
    ```
2.  Verify Installation:
    ```bash
    mvn -v
    ```

#### 🪟 Windows (Using [Scoop](https://scoop.sh))

1.  Install:
    - Open PowerShell as Administrator
    - Run:
      ```powershell
      scoop install maven
      ```
2.  Verify Installation:
    ```bash
    mvn -v
    ```

### 🏗 Building and Running the Application

1.  Clone the repository:
    ```bash
    git clone <your-repository-url>
    ```
2.  Navigate to the root folder:
    ```bash
    cd ToDo-List
    ```
3.  Build the project and create the executable JAR:
    ```bash
    mvn clean package
    ```
4.  Run the application:
    ```bash
    java -jar target/ToDo-List-1.0-SNAPSHOT.jar
    ```
    You will be prompted to enter your name and can then interact with the main menu.

### ✅ Running Unit Tests

1.  Clone the repository.
2.  Navigate to the root folder:
    ```bash
    cd ToDo-List
    ```
3.  Run the tests:
    ```bash
    mvn test
    ```