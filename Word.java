import java.util.ArrayList;

public class Word {
    private ArrayList<Byte[]> word;
    public boolean add(int n, Byte b1, Byte b2, Byte b3, Byte b4)
    {
        Byte[] e = new Byte[4];
        if (n % 4 != 0)
        {
            return false;
        }
        else
        {
            e[n] = b1;
            e[n + 1] = b2;
            e[n + 2] = b3;
            e[n + 3] = b4;
            word.add(n, e);
        }
        return true;
    }
    public void multiply(int n)
    {

    }

}
