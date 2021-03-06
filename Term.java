import java.util.*;

public class Term {

    int option;

    Factor factor;
    Term term;

    Term() {
        option = 0;
        factor = null;
        term = null;
    }

    public void parse(Scanner S) {
        // <term> ::= <factor> | <factor> * <term>
        // Regardless of which option we are on.
        // The first token is <factor>
        // Option 1: <term> ::= <factor>
        option = 1;
        factor = new Factor();
        factor.parse(S);
        // Option 2: <factor> * <term>
        if (S.currentToken() == Core.MULT) {
            option = 2;
            S.expectedToken(Core.MULT);
            term = new Term();
            term.parse(S);
        }
    }

    public void semantic(Stack<Map<String, Core>> scopetrack) {
        factor.semantic(scopetrack);
        if (option == 2) {
            term.semantic(scopetrack);
        }
    }

    public int execute(Memory memory) {
        int result = -1;
        int factornum = factor.execute(memory);
        if (option == 1) {
            result = factornum;
        } else if (option == 2) {
            int termnum = term.execute(memory);
            result = factornum * termnum;
        }
        return result;
    }

}
