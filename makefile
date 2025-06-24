# Makefile for the ToDo-List Java/Maven Project
# Provides simple shortcuts for building and running the application.

# Define variables
# MVN is the command for Maven.
MVN = mvn

# Define the full class paths for the main classes.
CONSOLE_MAIN_CLASS = org.ST.mfusi.app.Main
GUI_MAIN_CLASS = org.ST.mfusi.app.GuiApp


# Phony targets are not actual files. This prevents 'make' from getting confused
# if a file with the same name as a target exists.
.PHONY: all build run-console run-gui test clean

# Default target: Running 'make' will just build the project.
all: build

# Target to build the project and create the JAR file.
build:
	@echo "--- Building the project using Maven... ---"
	$(MVN) clean package

# Target to run the CONSOLE version of the application.
run-console:
	@echo "--- Running the Console Application... ---"
	$(MVN) exec:java -Dexec.mainClass="$(CONSOLE_MAIN_CLASS)"

# Target to run the GUI version of the application.
run-gui:
	@echo "--- Running the GUI Application... ---"
	$(MVN) exec:java -Dexec.mainClass="$(GUI_MAIN_CLASS)"

# Target to run the unit tests.
test:
	@echo "--- Running Unit Tests... ---"
	$(MVN) test

# Target to clean up all generated files (like the 'target' directory).
clean:
	@echo "--- Cleaning the project... ---"
	$(MVN) clean