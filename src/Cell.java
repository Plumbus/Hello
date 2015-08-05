/**
 * Created by elenagerasimova on 01.08.15.
 */
public class Cell extends Variant {
    public int digit;

    public Cell()
    {
        super();
        digit = 0;
    }

    public Cell(int d)
    {
        super();
        digit = d;
    }

    public Cell(Cell c)
    {
        super(c);
        digit = c.digit;
    }

    public void SetDigit(int d)
    {
        digit = d;
        Clear();
    }
}
