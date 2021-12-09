import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        char[] alphabet ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0',
                '1','2','3','4','5','6','7','8','9','.','?','!',',',';',':','-','(',')','[',']','{','}','\'','"',' ','\n'};



        int e=13;
        long n = 2537;
        String text = "STOP";
        String textAsNums = "";
        for (int i=0;i<text.length();i++){
            char c = text.charAt(i);
            for (int j=0;j<alphabet.length; j++){
                if(c == alphabet[j]) {
                    textAsNums += String.format("%02d", j);
                }
            }
        }
        String[] textSplitted=textAsNums.split("(?<=\\G.{4})");
        ArrayList<Long>  encrypted = new ArrayList<Long>();
        for (int i=0;i<textSplitted.length;i++){
            encrypted.add( (long)(Math.pow(Integer.parseInt(textSplitted[i]),e)) % n);
        }
        int f = Integer.parseInt(textSplitted[1]);
        System.out.println( findMod(textSplitted,e,n));
    }


    private static String findMod(String[] textSplitted, int e, Long n){
        BigInteger nBig = new BigInteger(String.valueOf(n));
        ArrayList<BigInteger> textAsBigInt = new ArrayList<BigInteger>();
        for (int i=0;i<textSplitted.length;i++){
            textAsBigInt.add(new BigInteger(textSplitted[i]));
        }

        ArrayList<BigInteger> numbersPowed = new ArrayList<BigInteger>();
        for (int i =0; i<textAsBigInt.size();i++) {
            numbersPowed.add(textAsBigInt.get(i).pow(e));
        }
        ArrayList<BigInteger> numbersModed = new ArrayList<>();
        for (int i=0; i<numbersPowed.size();i++) {
            numbersModed.add(numbersPowed.get(i).mod(nBig));
        }
        ArrayList<Long> resArrList = new ArrayList<>();
        for (int i=0;i<numbersModed.size();i++){
                resArrList.add(numbersModed.get(i).longValue());
        }

        String[] resArr = new String[resArrList.size()];
        for (int i=0;i<resArr.length;i++){
            resArr[i] = Long.toString(resArrList.get(i));
        }
        String res ="";
        for (int i=0;i<resArr.length;i++){
            if(resArr[i].length() ==3 )
                resArr[i] = "0" + resArr[i];
            else if(resArr[i].length() ==2 )
                resArr[i] = "00" + resArr[i];
            else if(resArr[i].length() == 1)
                resArr[i] = "000" +resArr[i];
            }

        for (int i=0; i<resArr.length;i++){
            res+= resArr[i];
        }

        return res;
        }




}
