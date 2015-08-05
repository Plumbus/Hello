import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] VariantSudoku(int nVar) {
        int[] array = {
                0,4,0, 9,0,0, 1,0,0,
                7,0,0, 0,0,5, 0,2,0,
                0,0,6, 0,0,0, 0,3,0,

                0,0,0, 0,0,0, 0,6,0,
                0,0,5, 0,9,7, 0,0,0,
                2,0,0, 5,0,0, 0,0,9,

                0,6,0, 0,0,0, 0,0,8,
                0,0,0, 0,0,0, 2,5,0,
                0,0,0, 2,1,3, 4,0,0};
        if(nVar == 1) return array;
        return new int[81];
    }
    public static void main(String[] args) throws IOException, MyException{
        Sudoku sudoku;
        try {
            sudoku = new Sudoku(VariantSudoku(1));
            sudoku.Solve();
            sudoku.Print();
        } catch (MyException e) {

        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }
}
