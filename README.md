
# 📝 ToDo-List

## 🌎 Brief Overview

ToDo-List is a simple but powerful application for managing your daily tasks. It features both a **command-line interface (CLI)** for quick terminal-based use and a **graphical user interface (GUI)** for a more visual experience.

You can create a personal profile, add tasks with different priorities (High, Medium, Low), edit them, mark them as complete, and view your entire list in a clean, organized format. The project is built with a clean architecture that separates the application's logic from the user interface, making it easy to understand and maintain.

---

## 🚀 Getting Started

This project is a `Java` project using `maven` as the build tool. A `Makefile` is included to provide simple shortcuts for common commands.

### 📦 3rd Party Dependencies

Maven handles all necessary dependencies automatically. No external setup is required.

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

### 🏗 Building the Project

1.  Clone the repository:
    ```bash
    git clone <your-repository-url>
    ```
2.  Navigate to the root folder:
    ```bash
    cd ToDo-List
    ```
3.  Build the project using the Makefile shortcut:
    ```bash
    make build
    ```
    This command will compile your code and package the application.

### ▶️ Running the Application

You can run either the command-line or the graphical version of the application.

#### To Run the **Console Version**:

```bash
make run-console

```
#### To Run the **GUI Version**:
```bash
make run-gui
```
    



#### ✅ Running Unit Tests
Clone the repository and navigate to the root folder.

Run all unit tests with this simple command:
```bash
make test
```
        