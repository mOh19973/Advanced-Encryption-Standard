import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This is the key that will be used per round. It will be implemented using 128/192/256-bits
 */
public class Key {
    private byte[] key;
    private int n;
    private int bytemax=0;
    private int wordmax=0;
    private byte[] Rcon;
    private void setRcon()
    {
        Byte b = 0x01;
        Rcon[0] = b;
        Rcon[1] = 0x00;
        Rcon[2] = 0x00;
        Rcon[3] = 0x00;
        int temp=0;
        int j=1;
        while(b!=0xFF)
        {
            temp=((int) Rcon[4 * j - 4])*2;
            Rcon[4^j]=(byte)temp;
        }
    }
    private void set(int n1)
    {
        n=n1;
        bytemax=n1/8;
        if (n1==128)
        {
            wordmax=44;
        }
        else if (n1==192){
            wordmax=52;
        }
        else wordmax=60;
    }
    public Key(int n)
    {
        set(n);
    }

    public byte[] RotWord(byte[] w1)
    {
        BigInteger bigInt = new BigInteger(w1);
        BigInteger shiftInt = bigInt.shiftLeft(4);
        return shiftInt.toByteArray();
    }

    public byte[] xor(byte w1[], byte w2[])
    {
        byte[] xoredword = new byte[w1.length];
        int i = 0;
        for (byte b : w1)
            xoredword[i]= (byte) (b ^ w2[i++]);
        return xoredword;
    }

    public byte[] SubWord(byte w[]) {
        return new byte[0];
    }

    public byte[] ExpandKey(int n, String keyword) //keyword must be the first word (4 bytes)
    {
        for(int j=0;j<=keyword.length();j=j+4)  //if failed must be length-1 since includes /n
        {
            key[j]= ((byte) keyword.codePointCount(j, j+3));
        }
        if (n==0)
            return key;
        else
        {
            /*for (int i = 32; i < wordmax; i++) {
                byte[] temp = key[4 * i - 4];
                if (i % 4 == 0)
                    temp = xor(SubWord(RotWord(temp)), Rcon[i / 4]);
                w[i] = xor(temp, w[i - 4]);
            }*/
        }
        return key;
    }
}
