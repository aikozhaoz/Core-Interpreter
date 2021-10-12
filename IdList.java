import java.util.*;

import jdk.jshell.execution.Util;

public class IdList {

    int option;
    String line;
    String id;
    
    IdList idlist;
    
    IdList(){
        option = 0;
        line = "";
        id = "";
        idlist = null;
    }

    public void parse(Scanner S) {
        // Option 1: <id-list> ::= id
        if (S.currentToken()==Core.ID) {
            option = 1;
            id = S.getID();
            S.nextToken();
        }else{
            Utility.expectedhelper(Core.ID, S.currentToken());
            System.exit(-1);
        }
        // Option 2: <id-list> ::= id , <id-list>
        if (S.currentToken()==Core.COMMA) {
            option = 2;
            S.expectedToken(Core.COMMA);
            idlist = new IdList();
            idlist.parse(S);
        }
    }

    public void semantic(Stack<Map<String, Core>> scopetrack, Core idorclass){
        // Pop off the current scope from the stack
        Map<String, Core> currentscope = scopetrack.pop();
        String key = id;
            
        // Check if the id is already declared within the current scope.
        // If so output error messages
        if(currentscope.containsKey(key)){
            Utility.DoubleDeclarationError(key);
            System.exit(-1);
        }
        // If not, add <ID, idorclass> to currentscope
        // Put currentscope back to stack
        else{
            currentscope.put(key, idorclass);
            scopetrack.add(currentscope);
        }
        if(option==2){
            idlist.semantic(scopetrack, idorclass);
        }
    }
    
    public void print(int indent) {
        System.out.print(line + id);
        if(option==2){
            System.out.print(",");
            idlist.print(indent);
        }
    }
}