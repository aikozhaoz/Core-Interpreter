# Core-Interpreter

### How to run
* ```cd``` to the ```Interpreter``` folder
* To compile, run ```javac Main.java ``` in your terminal
* To run the actual program, run ```javac Main.java testfile datafile```

### Project Description
* An Interpreter for a version of the Core language, a pretend language. The interpreter executes the whole program and check if there is any runtime error.

### Scanner Class
* Scanner:
    * The class constructor takes as input the name of the input file and finds the first token (the current token)
* tokenize:
    * This method should return a tokens list. 
* currentToken: 
    * This method should return the token the scanner is currently on, without consuming that token.
* nextToken:
    * This method should advance the scanner to the next token in the stream (the next token becomes the current token).
* getID: 
    * If the current token is ID, then this method should return the string value of the identifier.
* getCONST: 
    * If the current token is CONST, then this method should return the value of the constant.
* expectedToken:
    * Return if the current token == given Core c. If so, dequeues the tokens.

### Utility class
* expectedhelper:
    * If the current token is an invalid token, print out error message
    * If the current token is not expected token print out error message, expected token and curren token.
* errorhelper:
    * If the current token is an invalid token, print out error message
    * If the current token is not expected token print out error message, expected tokens and curren token.
* DoubleDeclarationError:
    * Print out error message to notify user the file has double declared variable. 
* UseUndeclaredIdError:
    * Print out error message to notify user the file used undeclared variable.
* DeclaredTypeError:
    * Print out error message to notify user the file contains wrongly declared variable.
* InvalidInput:
    * Print out runtime error message to notify user all values in the .data file have already been used.
* refIndexNull:
    * Print out runtime error message to notify user the class they are assigning constant to is null.

