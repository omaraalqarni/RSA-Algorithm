import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class encrypt {
    public static void main(String[] args) throws FileNotFoundException {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '?', '!', ',', ';', ':', '-', '(', ')', '[', ']', '{', '}', '\'', '"', ' ', '\n'};


        Scanner kb = new Scanner(System.in);

        System.out.print("please Enter the .txt file name ((without .txt)): ");
        String filename = kb.next();
        File myObj = new File(filename+".txt");
        Scanner sc = new Scanner(myObj);
        int e = sc.nextInt();
        long n = sc.nextLong();
        String text="";
        while (sc.hasNextLine()){
            text += sc.next();
        }
        String textAsNums = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < alphabet.length; j++)
                if (c == alphabet[j])
                    textAsNums += String.format("%02d", j);
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
        String[] textSplitted = textAsNums.split("(?<=\\G.{"+blocksize+"})");
        String enc = findMod(textSplitted, e, n);
        File FileToWriteIn =new File (filename+".rsa");
        PrintWriter pr= new PrintWriter(FileToWriteIn);
        pr.print(enc);
        pr.close();
    }


    private static String findMod(String[] textSplitted, int e, Long n) {
        int nLength = String.valueOf(n).length();
        BigInteger nBig = new BigInteger(String.valueOf(n));
        ArrayList<BigInteger> textAsBigInt = new ArrayList<BigInteger>();
        for (int i = 0; i < textSplitted.length; i++) {
            textAsBigInt.add(new BigInteger(textSplitted[i]));
        }

        ArrayList<BigInteger> numbersPowed = new ArrayList<BigInteger>();
        for (int i = 0; i < textAsBigInt.size(); i++) {
            numbersPowed.add(textAsBigInt.get(i).pow(e));
        }
        ArrayList<BigInteger> numbersModed = new ArrayList<>();
        for (int i = 0; i < numbersPowed.size(); i++) {
            numbersModed.add(numbersPowed.get(i).mod(nBig));
        }
        ArrayList<BigInteger> resArrList = new ArrayList<>();
        for (int i = 0; i < numbersModed.size(); i++) {
            resArrList.add(numbersModed.get(i));
        }

        String[] resArr = new String[resArrList.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = resArrList.get(i).toString();
        }
        String res = "";
        for (int i = 0; i < resArr.length; i++)
            if(nLength > resArr[i].length())
                while (nLength != resArr[i].length())
                    resArr[i] = "0"+resArr[i];

        for (int i = 0; i < resArr.length; i++) {
            res += resArr[i];
        }
        return res;
    }
}