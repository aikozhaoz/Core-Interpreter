# Core-Interpreter

### How to run
* ```cd``` to the ```Interpreter``` folder
* To compile, run ```javac Main.java ``` in your terminal
* To run the actual program, run ```javac Main.java testfile datafile```

### Project Description
* An Interpreter for a version of the Core language, a pretend language. The interpreter executes the whole program and check if there is any runtime error.

### Main class
* Skeleton of the program's workflow : )

### Core class
* Core Enums.

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
    
### Memory class
* Construct the memory class using singleton pattern.
* Separated the memory in 3 different scope:
    * Static --> Global
    * Stack --> Local
    * Heap --> Reference variable 
### Corevar class
* Contains 2 key attributes for each Core variable:
    * Type: int/ref
    * Value: int --> the actual int value | ref --> the pointer

### Grammar classes:
### Prog class
* Contains scanner, parser and execute functions for the following grammar
    * <prog> ::= program <decl-seq> begin <stmt-seq> end | program begin <stmt-seq> end
### DeclSeq class
* Contains scanner, parser and execute functions for the following grammar
    * <decl-seq> ::= <decl> | <decl><decl-seq>
### StmtSeq class
* Contains scanner, parser and execute functions for the following grammar
    * <stmt-seq> ::= <stmt> | <stmt><stmt-seq>
### Decl class
* Contains scanner, parser and execute functions for the following grammar
    * <decl> ::= <decl-int> | <decl-class>
### DeclInt class
* Contains scanner, parser and execute functions for the following grammar
    * <decl-int> ::= int <id-list> ;
### Declclass class
* Contains scanner, parser and execute functions for the following grammar
    * <decl-class> ::= ref <id-list> ;
### IdList class
* Contains scanner, parser and execute functions for the following grammar
    * <id-list> ::= id | id , <id-list>
### Stmt class
* Contains scanner, parser and execute functions for the following grammar
    * <stmt> ::= <assign> | <if> | <loop> | <in> | <out> | <decl>
### Assign class
* Contains scanner, parser and execute functions for the following grammar
    * <assign> ::= id = <expr> ; | id = new ; | id = ref id ;
### In class
* Contains scanner, parser and execute functions for the following grammar
    * <in> ::= input id ;
### Out class
* Contains scanner, parser and execute functions for the following grammar
    * <out> ::= output <expr> ;
### If class
* Contains scanner, parser and execute functions for the following grammar
    * <if> ::= if <cond> then <stmt-seq> endif | if <cond> then <stmt-seq> else <stmt-seq> endif
### Loop class
* Contains scanner, parser and execute functions for the following grammar
    * <loop> ::= while <cond> begin <stmt-seq> endwhile
### Cond class
* Contains scanner, parser and execute functions for the following grammar
    * <cond> ::= <cmpr> | ! ( <cond> ) | <cmpr> or <cond>
### Cmpr class
* Contains scanner, parser and execute functions for the following grammar
    * <cmpr> ::= <expr> == <expr> | <expr> < <expr> | <expr> <= <expr>
### Expr class
* Contains scanner, parser and execute functions for the following grammar
    * <expr> ::= <term> | <term> + <expr> | <term> – <expr>
### Term class
* Contains scanner, parser and execute functions for the following grammar
    * <term> ::= <factor> | <factor> * <term>
### Factor class
* Contains scanner, parser and execute functions for the following grammar
    * <factor> ::= id | const | ( <expr> )








