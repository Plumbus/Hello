/**
 * Created by gerasimov on 05.08.2015.
 */
public class Variant {
    protected int[] variant;
    protected int numberVariant;

    public Variant()
    {
        numberVariant = 0;
        variant = new int[9];
    }

    public Variant(Variant v)
    {
        //Variant();
        variant = new int[9];
        numberVariant = v.numberVariant;
        for (int i = 0; i < 9; i++) variant[i] = v.variant[i];
    }

    public void SetVariant(int v) throws MyException
    {
        if (v > 9) { throw new MyException("Index cannot be > 9 Index cannot be > 9"); }
        variant[v] = 1;
    }
    public void FillVariant()
    {
        numberVariant = 9;
        for (int i = 0; i < 9; i++) variant[i] = 1;
    }
    public void Clear()
    {
        numberVariant = 0;
        for (int i = 0; i < 9; i++) variant[i] = 0;
    }

    public int GetNumberVariant()
    {
        return numberVariant;
    }

    public int EvalNumberVariant() {
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += variant[i];
        numberVariant = sum;
        return numberVariant;
    }
    public void ExcludeVariant(int v) throws MyException
    {
        if (v == 0) return;
        if (v >= 9) { throw new MyException("Index cannot be > 9"); }
        if (variant[v] != 0){
            variant[v] = 0;
            numberVariant--;
        }
    }

    public String ToString()
    {
        String str;
        str = "Variant: " + numberVariant+ " " + numberVariant;
        return str;
    }
}
