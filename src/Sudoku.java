/**
 * Created by RP on 04.08.2015.
 */
public class Sudoku {
    private Cell[] cells;

    public Cell Get(int r, int c) {
        return cells[r * 9 + c];
    }

    public void Set(int r, int c, int d) {
        cells[r * 9 + c] = new Cell(d);
    }

    private void InitCell() {
        cells = new Cell[81];
    }

    public Sudoku() {
        InitCell();
        for (int i = 0; i < 81; i++) cells[i] = new Cell();
    }

    public Sudoku(int[][] ce) throws MyException {
        InitCell();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Set(r, c, ce[r][c]);
            }
        }

        SetVariant();
    }

    public Sudoku(int[] ce) throws MyException {
        InitCell();
        for (int ind = 0; ind < 81; ind++) {
            cells[ind] = new Cell(ce[ind]);
        }
        SetVariant();
    }

    public Sudoku(Sudoku sudoku) throws MyException {
        //Sudoku(sudoku.cells);
        InitCell();
        Cell[] ce = sudoku.cells;
        for (int ind = 0; ind < 81; ind++) {
            cells[ind] = new Cell(ce[ind]);
        }
        SetVariant();
    }

    void SetVariantForCell(int row, int col) throws MyException {
        Cell cell = Get(row, col);

        if (cell.digit != 0) return;

        cell.FillVariant();

        for (int c = 0; c < 9; c++) {
            if (c != col) cell.ExcludeVariant(Get(row, c).digit);
        }

        for (int r = 0; r < 9; r++) {
            if (row != r) cell.ExcludeVariant(Get(r, col).digit);
        }

        for (int r = row / 3 * 3; r <= row / 3 * 3 + 2; r++)
            for (int c = col / 3 * 3; c <= col / 3 * 3 + 2; c++) {
                if (r == row && c == col) continue;
                cell.ExcludeVariant(Get(r, c).digit);
            }
    }

    void SetVariant() throws MyException {
        int r, c;
        for (r = 0; r < 9; r++) {
            for (c = 0; c < 9; c++) SetVariantForCell(r, c);
        }
    }

    public Sudoku Clone() throws MyException {
        Sudoku Copy = new Sudoku(this);
        //for (int i = 0; i < 81; i++) Copy.cells[i] = cells[i];
        //int r, c;
        //for (r = 0; r < 9; r++)
        //    for (c = 0; c < 9; c++)
        //    {
        //         Copy[r,c].SetDigit(cells[r,c].digit);
        //        Copy[r, c].numberVariant = cells[r, c].numberVariant;
        //        for (int i = 0; i < 9; i++) Copy[r, c].variant[i] = cells[r, c].variant[i];
        //    }
        return Copy;
    }

    void RestoreCells(Cell[][] save) {
        int r, c;
        //for (r = 0; r < 9; r++)
        //    for (c = 0; c < 9; c++)
        //    {
        //        cells[r, c].digit = save[r, c].digit;
        //        cells[r, c].numberVariant = save[r, c].numberVariant;
        //        for (int i = 0; i < 9; i++) cells[r, c].variant[i] = save[r, c].variant[i];
        //    }
    }

    public void Print() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(Get(r, c).digit);
                if (c % 3 == 2) System.out.print(" ");
            }
            System.out.println("");
            if (r % 3 == 2) System.out.println("");
        }

    }

    public void PrintVariant() {
        int row, col, i, j;
        for (row = 0; row < 9; row++) {
            for (i = 0; i < 3; i++) {
                for (col = 0; col < 9; col++) {
                    for (j = 0; j < 3; j++) {
                        System.out.print(Get(row, col).variant[i * 3 + j]);
                    }
                    System.out.print(" ");
                    if (col % 3 == 2) System.out.print(" ");
                }
                System.out.println("");
            }
            System.out.println("");
            if (row % 3 == 2) System.out.println("");
        }
    }

    Boolean DeskIsSolved() {
        for (int ind = 0; ind < 81; ind++)
            if (cells[ind].digit == 0) return false;
        return true;
    }

    void ExcludeVariant(int row, int col, int digit) throws MyException {
        for (int i = 0; i < 9; i++) {
            if (i == col) continue;
            Get(row, i).ExcludeVariant(digit);
        }
        for (int i = 0; i < 9; i++) {
            if (i == row) continue;
            Get(i, col).ExcludeVariant(digit);
        }
        for (int r = row / 3 * 3; r <= row / 3 * 3 + 2; r++)
            for (int c = col / 3 * 3; c <= col / 3 * 3 + 2; c++) {
                if (r == row && c == col) continue;
                Get(r, c).ExcludeVariant(digit);
            }

    }

    Boolean SearchLonePerson() throws MyException {
        int row, col;
        Boolean result = false;

        for (row = 0; row < 9; row++)
            for (col = 0; col < 9; col++) {
                Cell cell = Get(row, col);
                if (cell.GetNumberVariant() == 1) {

                    for (int i = 0; i < 9; i++) {
                        if (cell.variant[i] != 0) {
                            result = true;
                            int digit = i + 1;
                            System.out.println("lone person: " + (row + 1) + ", " + (col + 1) + ", value: " + digit);
                            ExcludeVariant(row, col, digit);
                            cell.SetDigit(digit);
                            break;
                        }
                    }

                }
            }
        return result;
    }

    Boolean SearchSolves() throws MyException{
        Boolean searchSolve;
        Boolean result = Boolean.FALSE;
        do {
            searchSolve = SearchLonePerson();
            if (searchSolve) {
                result = Boolean.TRUE;
            }
        } while (searchSolve);
        return result;
    }

    Boolean SearchVariant(int row, int col, int d) {
        Boolean result = Boolean.FALSE;
        int i;

        for (i = 0; i < 9; i++)
        {
            if (i == col) continue;
            if (Get(row, i).digit == 0 && Get(row, i).variant[d - 1] != 0)
            {
                result = Boolean.TRUE;
                break;
            }
        }
        if (!result) return result;
        result = false;

        for (i = 0; i < 9; i++)
        {
            if (i == row) continue;
            if (Get(i, col).digit == 0 && Get(i, col).variant[d - 1] != 0)
            {
                result = Boolean.TRUE;
                break;
            }
        }
        if (!result) return result;
        result = false;

        for(int r = row/3*3; r<=row/3*3+2; r++)
            for (int c = col / 3 * 3; c <= col / 3 * 3 + 2; c++)
            {
                if (r == row && c == col) continue;
                if (Get(r, c).digit == 0 && Get(r, c).variant[d - 1] != 0)
                {
                    result = Boolean.TRUE;
                    break;
                }
            }
        return result;
    }

    Boolean SearchHiddenLonePerson() throws MyException {
        Boolean result = false;
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++) {
                Cell cell = Get(r,c);
                if (cell.digit == 0) {
                  for (int i = 0; i < 9; i++) {
                        if (cell.variant[i] != 0) {
                            int digit = i + 1;
                            if (!SearchVariant(r, c, digit)) {
                                System.out.println("Hidden lone person: " + (r + 1) + ", " + (c + 1) + ", value: " + digit);
                                result = true;
                                ExcludeVariant(r, c, digit);
                                cell.SetDigit(digit);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    Boolean SearchHiddenSolves() throws MyException {
        Boolean searchSolve;
        Boolean result = Boolean.FALSE;
        do {
            searchSolve = SearchHiddenLonePerson();
            if (searchSolve) {
                result = Boolean.TRUE;
            }
        } while (searchSolve);
        return result;
    }

    public void Solve() {
        Boolean result;
        do {
            result = Boolean.FALSE;
            try {

                //1. ѕоиск одиночек
                SearchSolves();
                if (DeskIsSolved()) return;

                //2. —крытый одиночка
                result = SearchHiddenSolves();
                if (DeskIsSolved()) return;
                //2.a ≈сли был скрытый одиночка оп€ть начинаем сначала
                if (result) continue;

                //3. ѕеребор
                //3.1 скопировать
                //3.2 в копии выбрать €чейки с минимальным количеством вариантов
                //3.3 заполнить вариант первым вариантом
                //3.4 прогнать первые 2 шага
                //3.5 проверить корректность , если некорректно то
                //      	ѕроверить заполнение,
                //      	перейти к следующему варианту
                //3.6
                //Cell[,] SaveCells = CopyCells();
            } catch(MyException e){
                System.out.println("stack overflow");
            } finally {
                //new code
            }
        } while(! result);
    }
}
