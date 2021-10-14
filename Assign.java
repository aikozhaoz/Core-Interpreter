import java.util.*;

import jdk.jshell.execution.Util;

public class Assign {

    int option;
    String line;

    Expr expr;
    String idone;
    String idtwo;

    Assign() {
        option = 0;
        line = "";

        expr = null;
        idone = "";
        idtwo = "";
    }

    public void parse(Scanner S) {
        // <assign> ::= id = new | id = ref id; | id = <expr>;
        // Regardless of which option we are on.
        // The first two tokens are ID =
        if (S.currentToken() == Core.ID) {
            idone = S.getID();
            S.nextToken();
        } else {
            Utility.expectedhelper(Core.ID, S.currentToken());
            System.exit(-1);
        }
        if (!S.expectedToken(Core.ASSIGN)) {
            Utility.expectedhelper(Core.ASSIGN, S.currentToken());
            System.exit(-1);
        }
        // Option 1: <assign> ::= id = new;
        if (S.currentToken() == Core.NEW) {
            option = 1;
            S.expectedToken(Core.NEW);
        }
        // Option 2: <assign> ::= id = ref id;
        else if (S.currentToken() == Core.REF) {
            option = 2;
            S.expectedToken(Core.REF);
            if (S.currentToken() == Core.ID) {
                idtwo = S.getID();
                S.nextToken();
            } else {
                Utility.expectedhelper(Core.ID, S.currentToken());
                System.exit(-1);
            }
        }
        // Option 3: <assign> ::= id = <expr>;
        // <expr> ::= <term> | <term> + <expr> | <term> – <expr>
        // <term> ::= <factor> | <factor> * <term>
        // <factor> ::= id | const | ( <expr> )
        else if (S.currentToken() == Core.ID || S.currentToken() == Core.CONST || S.currentToken() == Core.LPAREN) {
            option = 3;
            expr = new Expr();
            expr.parse(S);
        }

        // So if the currentToken != "new or id or const or (", then syntax is invalid.
        else {
            Core[] expectedones = new Core[] { Core.NEW, Core.REF, Core.ID, Core.CONST, Core.LPAREN };
            Utility.errorhelper(expectedones, S.currentToken());
            System.exit(-1);
        }
        if (!S.expectedToken(Core.SEMICOLON)) {
            Utility.expectedhelper(Core.SEMICOLON, S.currentToken());
            System.exit(-1);
        }
    }

    public void semantic(Stack<Map<String, Core>> scopetrack) {
        boolean IDdeclared = false;
        boolean rightType = false;
        String key = idone;
        // Check if the current ID is being declared yet before using it.
        for (Map<String, Core> currentscope : scopetrack) {
            // If the current ID is declared. Check if the declared type is right.
            if (currentscope.containsKey(key)) {
                IDdeclared = true;
                if (option == 1 && currentscope.get(idone) == Core.REF) {
                    rightType = true;
                } else if (option == 2 && currentscope.get(idone) == Core.REF && currentscope.get(idtwo) == Core.REF) {
                    rightType = true;
                } 
                // If the current token is expr then expr will handle the semantic check. 
                // Set the rightType to true allows expr to do the semantic check.
                else if (option == 3) {
                    rightType = true;
                    expr.semantic(scopetrack);
                }
            }
        }
        if (!IDdeclared) {
            Utility.UseUndeclaredIdError(idone);
            System.exit(-1);
        }
        if (!rightType) {
            Utility.DeclaredTypeError(idone);
            System.exit(-1);
        }
    }

    public void execute(Memory memory){
        String leftkey = idone;
        String rightkey = idtwo;
        Corevar leftvar = new Corevar();
        Corevar rightvar = new Corevar();
        boolean leftvarInGlobal = true;
        boolean rightvarInGlobal = true;
        for (HashMap<String, Corevar> currentscope : memory.stackSpace) {
            if (currentscope.containsKey(leftkey)) {
                leftvarInGlobal = false;
                leftvar = currentscope.get(leftkey);
            }
            if (currentscope.containsKey(rightkey)) {
                rightvarInGlobal = false;
                rightvar = currentscope.get(rightkey);
            }
        }
        if (leftvarInGlobal){
            leftvar = memory.globalSpace.get(leftkey);
        }
        if (rightvarInGlobal){
            rightvar = memory.globalSpace.get(rightkey);
        }

        if(option == 1){
            int exprnum = expr.execute(memory);
            if (leftvar.type == Core.INT){
                leftvar.setvalue(exprnum);
            }else if(leftvar.type == Core.REF){
                if (leftvar.value == null){
                    Utility.refIndexNull();
                    System.exit(-1);
                }
                memory.heapSpace.set(leftvar.value, exprnum);
            }
        }else if (option == 2){
            if(leftvar.type == Core.REF){
                memory.heapSpace.add(leftvar.value);
                leftvar.value = memory.heapSpace.size()-1;
            }
        }else if (option ==3){
            if(leftvar.type == Core.REF && rightvar.type == Core.REF){
                leftvar.value = rightvar.value;
            }
        }
    }
}
