public class main {
    public static void main(String[] args)
    {
        AES aes= new AES("FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF","AB CF 45 34 21 47 75 94 05 83 FF 38 49 DB CB 45 12 69 89 45 AB 12 36 48",192);
        System.out.print(aes.encrypt("FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF","AB CF 45 34 21 47 75 94 05 83 FF 38 49 DB CB 45 12 69 89 45 AB 12 36 48"));
    }
}

