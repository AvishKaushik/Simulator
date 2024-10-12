# CSA Simulator

The CSA Simulator is a Java-based tool that simulates the key components of a computer system architecture, including a CPU, memory, input/output devices, and conversion utilities. This project provides a graphical interface for users to interact with the simulated system, execute instructions, and visualize registers, memory states, and fault handling.

## Table of Contents
- [Overview](#overview)
- [System Components](#system-components)
  - [Graphical User Interface (GUI)](#graphical-user-interface-gui)
  - [Memory Management](#memory-management-memoryjava)
  - [Devices Interface](#devices-interface-devicesjava)
  - [Number Conversion Utility](#number-conversion-utility-converterjava)
  - [CPU Simulation](#cpu-simulation-cpujava)
- [Design Considerations](#design-considerations)
- [Future Expansion](#future-expansion)
- [How to Run](#how-to-run)
- [Contributing](#contributing)
- [License](#license)

## Overview

The CSA Simulator emulates core components of a computing system, allowing users to simulate CPU operations, manage memory, and interact with input/output devices. The graphical interface provides an intuitive way to execute, reset, and visualize key aspects of system architecture.

## System Components

### Graphical User Interface (GUI)

- **Java Swing-based GUI**: The GUI provides a user-friendly way to interact with the simulator. Users can control registers, memory, and CPU instructions through buttons.
- **Components**:
  - Registers are displayed using labels for General Purpose Registers (GPRs), Index Registers (IXRs), and the Machine Fault Register (MFR).
  - Control buttons for key functions like `Store`, `Load`, `Reset`, `Run`, and `Single Step` are available.

### Memory Management (`Memory.java`)

- Simulates a 2KB memory block using a `short[]` array.
- All memory cells are initialized to zero at the start.
- Memory operations like load and store are executed based on the instructions provided.

### Devices Interface (`Devices.java`)

- A simple console interface is simulated using `JTextArea` components.
  - **ConsoleOut**: Represents the console output (printer).
  - **ConsoleIn**: Represents the console input (keyboard).
- The interface is organized into panels for console interaction.

### Number Conversion Utility (`Converter.java`)

- Provides methods to convert numbers between binary, decimal, and hexadecimal formats.
- These utility methods are used throughout the simulator for operations involving the CPU and memory.

### CPU Simulation (`CPU.java`)

- Manages the core CPU registers and executes instructions based on binary opcodes.
- Supported operations include load, store, and halt.
- **Memory Fault Handling**: Detects and manages invalid memory access through the Memory Fault Register (MFR).

## Design Considerations

- **Manual Layout**: The GUI is laid out manually using `setBounds()`, which limits scalability. For future versions, using layout managers (e.g., `GridLayout`) could improve flexibility.
- **Threading**: The `Run` function uses a `SwingWorker` thread to ensure the GUI remains responsive during long-running operations.
- **Fixed Memory Size**: The memory is currently fixed at 2KB, which could be made dynamic for future flexibility.

## Future Expansion

- **Dynamic Memory Size**: Allow users to configure the memory size or allocate memory dynamically.
- **Improved GUI Layout**: Refactor the GUI layout to use layout managers for better adaptability to different screen sizes and resolutions.
- **Extended Fault Handling**: Enhance memory fault detection to cover more complex scenarios.

## How to Run

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 23 or above installed on your system.
- **IDE**: You can use any Java-compatible IDE, such as IntelliJ IDEA, Eclipse, or NetBeans.

### Running the Simulator

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/csa-simulator.git
   cd csa-simulator
   ```
2. Open the project in your preferred IDE.

3. Build and run the project:
   Compile and execute the main class that initializes the GUI and starts the simulator.
   
4. Interact with the simulator through the graphical interface. Use the control buttons to load, store, reset, and run instructions.
