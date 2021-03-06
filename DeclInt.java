import java.util.*;

public class DeclInt {

    String line;

    IdList idlist;

    DeclInt() {
        line = "";
        idlist = null;
    }

    public void parse(Scanner S) {
        // <decl-int> ::= int <id-list> ;
        if (!S.expectedToken(Core.INT)) {
            Utility.expectedhelper(Core.INT, S.currentToken());
            System.exit(-1);
        }
        idlist = new IdList();
        idlist.parse(S);
        if (!S.expectedToken(Core.SEMICOLON)) {
            Utility.expectedhelper(Core.SEMICOLON, S.currentToken());
            System.exit(-1);
        }
    }

    public void semantic(Stack<Map<String, Core>> scopetrack) {
        idlist.semantic(scopetrack, Core.INT);
    }

    public void execute(Memory memory) {
        idlist.execute(memory, Core.INT);
    }

}
