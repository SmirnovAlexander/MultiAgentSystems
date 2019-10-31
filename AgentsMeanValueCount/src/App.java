import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static ArrayList<ArrayList<Integer>> ADJ_MATRIX;
    public static int NUMBER_OF_AGENTS = 5;
    public static int MAX_STEPS = 100;

    public static void main(String[] args) {

        // ToDo: remove if on main (remove main agent)

        // Initializing connection matrix
        ADJ_MATRIX = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0,1,1,1,1));
        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(1,0,1,1,1));
        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(1,1,0,1,1));
        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(1,1,1,0,1));
        ArrayList<Integer> row5 = new ArrayList<>(Arrays.asList(1,1,1,1,0));
        ADJ_MATRIX.add(row1);
        ADJ_MATRIX.add(row2);
        ADJ_MATRIX.add(row3);
        ADJ_MATRIX.add(row4);
        ADJ_MATRIX.add(row5);

        MainController mc = new MainController(NUMBER_OF_AGENTS);
        mc.initAgents();
    }
}



//    ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0,1,1,0,0));
//    ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(1,0,1,1,0));
//    ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(1,1,0,0,0));
//    ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(0,1,0,0,1));
//    ArrayList<Integer> row5 = new ArrayList<>(Arrays.asList(0,0,0,1,0));
//    ADJ_MATRIX.add(row1);
//    ADJ_MATRIX.add(row2);
//    ADJ_MATRIX.add(row3);
//    ADJ_MATRIX.add(row4);
//    ADJ_MATRIX.add(row5);

//                      +---+
//                      |   |
//                      +-+-+
//                      |
//                      +-+-+
//                      |   |
//                      +-+-+
//                      |
//                      +-+-+
//                      |   |
//                      +---+
//                      |   |
//                  +-----+   +----+
//                  |     |   |    |
//                  |     |   |    |
//                  +-----+   +----+



//        ADJ_MATRIX = new ArrayList<>();
//        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0,1,0,0,0));
//        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(1,0,1,0,0));
//        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(0,1,0,1,0));
//        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(0,0,1,0,1));
//        ArrayList<Integer> row5 = new ArrayList<>(Arrays.asList(0,0,0,1,0));
//        ADJ_MATRIX.add(row1);
//        ADJ_MATRIX.add(row2);
//        ADJ_MATRIX.add(row3);
//        ADJ_MATRIX.add(row4);
//        ADJ_MATRIX.add(row5);

//        +------+
//        |      |
//        |      |
//        +---+--+
//           |
//        +---+--+
//        |      |
//        |      |
//        +---+--+
//           |
//        +---+--+
//        |      |
//        |      |
//        +---+--+
//           |
//        +--+--+
//        |     |
//        |     |
//        +--+--+
//           |
//        +-+---+
//        |     |
//        |     |
//        +-----+
