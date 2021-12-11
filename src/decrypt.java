import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class decrypt {
    public static void main(String[] args) throws FileNotFoundException {




    char[] alphabet ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0',
            '1','2','3','4','5','6','7','8','9','.','?','!',',',';',':','-','(',')','[',']','{','}','\'','"',' ','\n'};


    Scanner kb = new Scanner(System.in);
    System.out.print("Welcome\nPlease Enter the d key: " );
    int d = kb.nextInt();
    System.out.print("\nPlease Enter the n key: ");
    long n = kb.nextLong();
    System.out.println("please Enter the .rsa file name ((without .rsa))");
    String filename = kb.next();
    File myObj = new File(filename+".rsa");
    Scanner sc = new Scanner(myObj);
    String text="";
    while (sc.hasNextLine()){
        text += sc.nextLine();
        }
    String nMin = "0";
    int res = 0;
    ArrayList<Integer> ress = new ArrayList<>();
    while(res<n){
        nMin += "78";
        res = Integer.parseInt(nMin);
        ress.add(res);
    }
    int blocksize = String.valueOf(ress.get(ress.size()-2)).length();
    int nLength = String.valueOf(n).length();
    String[] textSplitted=text.split("(?<=\\G.{"+nLength+"})");
    String dec = decrypt(n,d,textSplitted,blocksize);
    File FileToWriteIn =new File (filename+".dec");
    PrintWriter pr= new PrintWriter(FileToWriteIn);
    pr.print(dec);
    pr.close();

    }

    private static String decrypt(long n, int d,String[] message,int blocksize){
        BigInteger[] messageBig = new BigInteger[message.length];
        for(int i=0;i<message.length;i++) {
            messageBig[i] = new BigInteger(message[i]);
        }
        BigInteger[] powed = new BigInteger[messageBig.length];
        for (int i=0; i<powed.length;i++){
            powed[i] = messageBig[i].pow(d);
            powed[i] = powed[i].mod(BigInteger.valueOf(n));
        }

        ArrayList<Long> resArrList = new ArrayList<>();
        for (int i=0;i<powed.length;i++){
            resArrList.add(powed[i].longValue());
        }

        String[] resArr = new String[resArrList.size()];
        for (int i=0;i<resArr.length;i++){
            resArr[i] = Long.toString(resArrList.get(i));
        }
        for (int i = 0; i < resArr.length; i++)
            if(blocksize > resArr[i].length())
                while (blocksize != resArr[i].length())
                    resArr[i] = "0"+resArr[i];



        String res ="";
        for (int i=0; i<resArr.length;i++){
            res+= resArr[i];
        }
        return res;
    }

}
