# PL-GOTO-Compiler
An interpreter for the computational model GOTO. Data Set: Natural Numbers (N).

### IDE
I have developed an IDE for programming and interpreting GOTO source code. Its source code is available [here].
(Download link comming soon)

# Syntax

### Variables
- Input: X1(=X), X2, X3, ... 
- Output: Y
- Auxiliary: Z1(=Z), Z2, Z3, ... 
If you only use one input/auxliary variable you can put X/Z without subindex.

### Labels
A1, B1, C1, D1, E1, A2, B2, C2, D2, E2, ...
- Notation: A=A1, B=B1, C=C1, D=D1, E=E1
- Special: E (exit program)

### Basic instructions
For every variable V and label L:
- Increment:              V <- V+1
- Decrement:              V <- V-1
- Conditional:            IF V != 0 GOTO L
- Skip:                   V <- V
- Labelled instructions:  [K] V <- V+1

### Macros
A GOTO macro is a GOTO program makes a specific task.

### GOTO program example
```
[A] X <- X-1
    Y <- Y+1
    IF X != 0 GOTO A
```

[here]:<https://github.com/garmo/GOTO-IDE>
