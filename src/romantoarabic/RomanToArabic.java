/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romantoarabic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.TreeMap;

/**
 *
 * @author Ivan
 */
public class RomanToArabic {

    /**
     * @param args the command line arguments
     */
    //initialisation of global variables  
    private static int arabicNumber = 0;
    private static String romanNumber = null;

    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("Please, Input Roman Number:");
        romanNumber = getString();
        arabicNumber = romanToArabic(romanNumber);
        System.out.println("Converted Arabic Number is: " + arabicNumber);

        System.out.println("Please, Input Arabic Number:");
        arabicNumber = getInt();
        romanNumber = arabicToRoman(arabicNumber);
        System.out.println("Converted Roman Number is: " + romanNumber);
    }

    //reading String variables from the console
    public static String getString() {
        LineNumberReader LNR = new LineNumberReader(new InputStreamReader(System.in));

        String S = "";
        try {
            S = LNR.readLine();
        } catch (IOException ioe) {
            S = "";
        }
        return S;
    }

    //reading integer variables from the console
    public static int getInt() {
        try {
            return Integer.parseInt(getString());
        } catch (Exception ie) {
            return 0;
        }
    }

    //converting roman number to arabic
    public static int romanToArabic(String roman) {
        //initialisation of local variables  
        int resArabic = 0;
        int prevArabic = 0;
        int curArabic = 0;

        //converting inputed String roman number to uppercase
        roman = roman.toUpperCase();

        //parsing inputed roman number to char symbols and finding corresponding 
        //arabic values
        for (int i = roman.length() - 1; i >= 0; i--) {
            char curSymbolRoman = roman.charAt(i);
            switch (curSymbolRoman) {
                case 'M':
                    curArabic = 1000;
                    break;
                case 'D':
                    curArabic = 500;
                    break;
                case 'C':
                    curArabic = 100;
                    break;
                case 'L':
                    curArabic = 50;
                    break;
                case 'X':
                    curArabic = 10;
                    break;
                case 'V':
                    curArabic = 5;
                    break;
                case 'I':
                    curArabic = 1;
                    break;
                default:
                    //return error
                    System.out.println("Invalid Roman Number Format!");
                    return 0;
            }

            //comparing symbols and arabic number calculation
            if (prevArabic > curArabic) {
                resArabic = resArabic - curArabic;
            } else {
                resArabic = resArabic + curArabic;
            }

            prevArabic = curArabic;
        }

        //return result
        return resArabic;
    }

    //converting arabic number to roman (recursion process)
    public static String arabicToRoman(int arabic) {
        //initialisation of local TreeMap
        TreeMap<Integer, String> tMap = new TreeMap<Integer, String>();
        tMap.put(1000, "M");
        tMap.put(900, "CM");
        tMap.put(500, "D");
        tMap.put(400, "CD");
        tMap.put(100, "C");
        tMap.put(90, "XC");
        tMap.put(50, "L");
        tMap.put(40, "XL");
        tMap.put(10, "X");
        tMap.put(9, "IX");
        tMap.put(5, "V");
        tMap.put(4, "IV");
        tMap.put(1, "I");

        //searching the greatest key less than to the inputed key(arabic number)
        int curMaxRoman = tMap.floorKey(arabic);

        if (arabic == curMaxRoman) {
            //return result
            return tMap.get(arabic);
        }

        //return result
        return tMap.get(curMaxRoman) + arabicToRoman(arabic - curMaxRoman);
    }
}
