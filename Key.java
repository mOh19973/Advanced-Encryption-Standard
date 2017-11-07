import java.util.ArrayList;

/**
 * This is the key that will be used per round. It will be implemented using 128/192/256-bits
 */
public class Key {
    private int n;
    private int bytemax=0;
    private int wordmax=0;
    private Word Rcon;
    private void setRcon(int i)
    {
        Byte filler=0x00;
        if(i==1)
        {
            Byte b=0x01;
            Rcon.add(i,b,filler,filler,filler);
        }
        //else
        //{
         //   Rcon.
        //}

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

    public void ExpandKey(Byte key[], Word w[])
    {
        for (int i=0; i<4; i++)
        {
            w[i].add(i,key[4*i],key[4*i+1],key[4*i+2],key[4*i+3]);
        }
        for(int i=4;i<wordmax; i++)
        {
            Word temp=w[i-1];
            //if(i%4==0)
                //temp=xor(SubWord(RotWord(temp),Rcon[i/4]);
            //w[i]=xor(temp,w[i-4]);
        }
    }
}
