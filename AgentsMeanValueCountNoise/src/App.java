import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static ArrayList<ArrayList<Float>> ADJ_MATRIX;

    public static int NUMBER_OF_AGENTS = 5;
    public static int MAX_STEPS = 50;

    public static float NOISE_MIN = -0.01f;
    public static float NOISE_MAX = 0.01f;

    public static void main(String[] args) {

        // Initializing connection matrix
        ADJ_MATRIX = new ArrayList<>();
        ArrayList<Float> row1 = new ArrayList<>(Arrays.asList(0f, 1f, 1f, 1f, 1f));
        ArrayList<Float> row2 = new ArrayList<>(Arrays.asList(1f, 0f, 1f, 1f, 1f));
        ArrayList<Float> row3 = new ArrayList<>(Arrays.asList(1f, 1f, 0f, 1f, 1f));
        ArrayList<Float> row4 = new ArrayList<>(Arrays.asList(1f, 1f, 1f, 0f, 1f));
        ArrayList<Float> row5 = new ArrayList<>(Arrays.asList(1f, 1f, 1f, 1f, 0f));
        ADJ_MATRIX.add(row1);
        ADJ_MATRIX.add(row2);
        ADJ_MATRIX.add(row3);
        ADJ_MATRIX.add(row4);
        ADJ_MATRIX.add(row5);

        MainController mc = new MainController(NUMBER_OF_AGENTS);
        mc.initAgents();
    }
}


//ADJ_MATRIX = new ArrayList<>();
//ArrayList<Float> row1 = new ArrayList<>(Arrays.asList(0f, 0.8f, 0f));
//ArrayList<Float> row2 = new ArrayList<>(Arrays.asList(1f, 0f, 1f));
//ArrayList<Float> row3 = new ArrayList<>(Arrays.asList(0f, 0.7f, 0f));
//ADJ_MATRIX.add(row1);
//ADJ_MATRIX.add(row2);
//ADJ_MATRIX.add(row3);

//// Initializing connection matrix
//        ADJ_MATRIX = new ArrayList<>();
//        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0,1,1,1,1));
//        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(1,0,1,1,1));
//        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(1,1,0,1,1));
//        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(1,1,1,0,1));
//        ArrayList<Integer> row5 = new ArrayList<>(Arrays.asList(1,1,1,1,0));
//        ADJ_MATRIX.add(row1);
//        ADJ_MATRIX.add(row2);
//        ADJ_MATRIX.add(row3);
//        ADJ_MATRIX.add(row4);
//        ADJ_MATRIX.add(row5);



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
