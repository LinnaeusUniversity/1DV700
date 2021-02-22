package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MyHashImpl {

    public static void main(String[] args) throws IOException {
        System.out.print("Hash result of abc : "+ myHashFun("abc"));


        System.out.print("\nCheck for Uniformity\n");
        System.out.print("Small changes in input still give very different hash values : \n");


        String file = System.getProperty("user.dir") + "/mit-edu-wordlist-10000.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> testStrings = new ArrayList<>();
        String line;

        while((line = reader.readLine()) != null) {
            testStrings.add(line);

        }
        reader.close();

        System.out.println(testStrings.size());

        diffHashValue(testStrings);
        one1000InputTry();


    }

    private static ArrayList<Double> bj = new ArrayList<>();
    private static ArrayList<Double> nbrBucket = new ArrayList<>();

    //My Hash function
    public static int myHashFun(String str){
        int hashed = 0;
        //Random prime number
        int hashHelp = 31;

        for (int i = 0; i<str.length(); i++){
            //take each ascii value of each character in str
            int character = str.charAt(i);
            //XOR each byte of character and then multiply by the prime number
            hashed = (hashHelp * hashed) ^ character;
        }
        //I add it to make the final result always positive
        return (hashed & 0xFF);
    }


    //Method with small changes in input
    public static void diffHashValue(ArrayList<String> arrToTest) {

        String[][] stringArr = new String[65535][];
        int j = 0;

        for (int i = 0; i < arrToTest.size(); i++) {
            int hash = myHashFun(arrToTest.get(i)); // we get each hash
            String[] stringArrTemp = {arrToTest.get(i)};

            if (stringArr[hash] != null) {
                String[] temp = stringArr[hash];
                temp = Arrays.copyOf(temp, temp.length + 1);
                temp[temp.length - 1] = stringArrTemp[0];
                stringArr[hash] = temp;
                j++;
                System.out.println("Collision occurred with hash: " + hash + " between " + Arrays.toString(temp));

            } else {
                stringArr[hash] = stringArrTemp;
            }


        }

        System.out.println("****************************************");
        System.out.println("Number of collisions: " + j + " with " + arrToTest.size() + " different words" );
        System.out.println("****************************************");



        getBucketCountArray(stringArr);


        double n = arrToTest.size(); //number of keys
        double m = arrToTest.size() - j; //number of buckets



        double chiSquaredTest = 0; //chi squared

        for (int k =0 ; k<bj.size();k++){

            for (double mBucket = m; mBucket>0; mBucket --){
                chiSquaredTest += ((bj.get(k)*(bj.get(k)+1)/2) / ((n/(2*mBucket))*(n+(2*mBucket) - 1)));
                if(nbrBucket.get(k) == mBucket){
                    m = nbrBucket.get(k);
                    continue;
                }

            }
        }

        System.out.println("****************************************");
        System.out.println("Chi Squared test : " +  chiSquaredTest);


    }

    public static void getBucketCountArray(String[][] arr) {
        int temp;
        int [] count = new int[100];

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != null) {
                String[] tmp =  arr[i];
                temp = tmp.length;
                count[temp]++;
            }

        }

        for(int i=0; i < count.length; i++){
            if(count[i] > 0 ){
                System.out.printf("%d buckets have %d entries\n",count[i], i);
                bj.add((double)i);
                nbrBucket.add((double)count[i]);
            }
        }


    }

    public static void one1000InputTry() throws IOException{
        String secondFile = System.getProperty("user.dir") + "/1000-randomStringInput.txt";
        BufferedReader secondReader = new BufferedReader(new FileReader(secondFile));
        ArrayList<String> strings100 = new ArrayList<>();
        String secondLine;
        while((secondLine = secondReader.readLine()) != null) {
            strings100.add(secondLine);

        }

        secondReader.close();
        System.out.println("****************************************");

        System.out.println("Hashes with one bit difference of " + strings100.size() + " input");
        int count = 0;

        int[] hashed = new int[strings100.size()];
        ArrayList<Integer> hashArray = new ArrayList<>();
        for (int i = 0; i<strings100.size();i++){

            int hashFunc = myHashFun(strings100.get(i));

            System.out.println("Hash for " + strings100.get(i) + " = " + hashFunc);

            hashArray.add(hashFunc);
            hashed[i]+=hashFunc;
        }

        System.out.println("****************************************");


        for (int i = 0; i<hashArray.size();i++){

            for (int j = i +1; j<hashed.length;j++){
                if (hashed[i] == hashed[j]){
                    System.out.println("Collision hash of "+strings100.get(i) + ": "+ hashed[i]);
                    count++;
                }
            }
        }
        System.out.println("****************************************");
        System.out.println("Hash that appeared multiple times : " + count);

    }



}




/*
I think for the distribution I get something that is not that good meaning that we can somehow know that
Abc or aBc or abC is close to abc.

This hash function is not secure because it does not produce a fixed length output depending on whatever the input is.
The output does not reveal information about the input
And it should be hard to find collisions
 */


