import java.util.*;

public class DeclSeq {

    int option;

    Decl decl;
    DeclSeq declseq;

    DeclSeq() {
        option = 0;
        decl = null;
        declseq = null;
    }

    public void parse(Scanner S) {
        // Option 1: <decl-seq> ::= <decl>
        option = 1;
        decl = new Decl();
        decl.parse(S);
        // Option 2: <decl-seq> ::= <decl><decl-seq>
        // If the current token != Core.BEGIN, continue parsing declseq.
        if (S.currentToken() == Core.INT || S.currentToken() == Core.REF) {
            option = 2;
            declseq = new DeclSeq();
            declseq.parse(S);
        }
    }

    public void execute(){
        decl.execute();
        if (option == 2){
            declseq.execute();
        }

}