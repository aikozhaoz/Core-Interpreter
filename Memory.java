import java.util.*;

// Using Singleton pattern since we only need ONE memory obj
public class Memory 
{
    public static HashMap<String, Corevar> globalSpace;
    public static Stack<HashMap<String, Corevar>> stackSpace;
    public static ArrayList<Integer> heapSpace;
    public static boolean inGlobal;

    // Make the constructor private so that this class cannot be instantiated outside of this class
    private Memory(){
        globalSpace = new HashMap<String, Corevar>();
        stackSpace = new Stack<HashMap<String, Corevar>>();
        heapSpace = new ArrayList<Integer>();
    }

    // Create a private instance of Memory obj
    private static Memory memory = new Memory();

    // Get the only instance available
    public static Memory getMemory(){
        return memory;
    } 

    // public static HashMap<String, Corevar> getGlobal(){
    //     return globalSpace;
    // }

    // public static Stack<HashMap<String, Corevar>> getStack(){
    //     return stackSpace;
    // }

    // public static ArrayList<Integer> getHeap(){
    //     return heapSpace;
    // }
}
