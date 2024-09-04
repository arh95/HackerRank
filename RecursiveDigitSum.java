import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//https://www.hackerrank.com/challenges/recursive-digit-sum/problem
//solution by Adam Hayes
class Result {

    /*
     * Complete the 'superDigit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING n
     *  2. INTEGER k
     */
     
    private static Long superDigitRecursive(String n, int k)
    {
        long superDigit = 0;
        if (n.length() == 1) {
            superDigit = Long.parseLong(n);
            return superDigit;
        }
        else {
          //problem brief states that int k should be used to append string n to itself k times, to create String P
          //where P is the initial value of the string to procure the super digit from

          //in my initial solution, I did just that before entering my recursive method: append string n to itself k times
          //however, this was failing tests w/ runtime concerns

          //In my more performant final solution, I chose to separate the recursive function into three cases:
          //1) where string N is only length one, meaning we found our superDigit (base case)
          //2) Where string N.length > 1, but k = -1, meaning we can just sum up each individual digit of the given string, and recursively call function again
          //3) Where string N.length > 1, and k != -1 (k is its original value). This is the initial case, where to save time iterating over a potentially very long string
          //  a very large number of times, we are able to use mathmatic principles to calculate the sum of the substring n, and multiply that sum by K, which gets us the same 
          //result as pre-processing the string appending, but with a lot less overhead
            for (int i = 0; i < n.length(); i++)
            {
             
                superDigit += Character.getNumericValue(n.charAt(i));
            }
            if (k != -1)
            {
                superDigit = superDigit * k;
            } 

            String subN = "" + superDigit;
            return superDigitRecursive(subN, -1);
        }

    }

  
    public static int superDigit(String n, int k) {
        long superDigit =  superDigitRecursive(n, k);
        return (int) superDigit;
   }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
