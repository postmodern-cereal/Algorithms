import java.util.*;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.*;


class Test
{
    private boolean writeToFile(String toWrite)
    {
        try
        {
            FileWriter writer = new FileWriter("test-data.txt", true);
            writer.append(toWrite);
            writer.close();
            return true;
        } catch (IOException e)
        {
            System.err.println("File issue");
            return false;
        }
    }

    private double average(long[] data)
    {
        double toReturn = 0.0;
        for(int i = 0; i < data.length; i ++)
        {
            toReturn += data[i];
        }
        return(toReturn/data.length);
    }
    
    //Testing was performed automatically by running this class. To change from fully to partially
    //connected graphs and change input sizes, i changed the numbers in the for loop, and made
    //modifications to the testCase functions in Homework7, as noted there.
    //in all cases, i took the average runtime after running the algorithm on a given input size 5 times.
    private void test()
    {
        Homework7 h7 = new Homework7();
        long startTime = 0;
        long endTime = 0;
        long testTimes[] = new long[5];
        writeToFile("Testing partially connected graphs of size from 5 to 10 in increments of 1.\n");
        for(int i = 5; i <=10; i++)
        {
            writeToFile("n = " + i + ":\tRUNS: ");
            for(int j = 0; j < 5; j++)
            {
                startTime = System.currentTimeMillis();
                h7.testCase(i);
                endTime = System.currentTimeMillis();
                testTimes[j] = (endTime-startTime);
                writeToFile(testTimes[j] + " ");
            }
            writeToFile(" AVERAGE: " +(this.average(testTimes)) + "\n");
        }

    }

    public static void main(String[] args)
    {
        Test t = new Test();
        t.test();
    }
}
