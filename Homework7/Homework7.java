import java.util.*;
import java.util.Collections;

class Homework7
{
    private int getSize(LinkedList<Integer> islands)
    {
        //sorting the list will put the biggest number on the end
        //there will be no vertices with larger keys than that, so
        //this will be the dimensions for the matrix
        LinkedList<Integer> tmp = new LinkedList<Integer>();
        for(int i = 0; i < islands.size(); i ++)
        {
            tmp.add(islands.get(i));
        }
        Collections.sort(tmp);
        return tmp.getLast() + 1;
    }

    private int[][] clearCell(int[][] matrix, int x, int y, int matrixSize)
    {
        //have to clear both x,y and y,x because the edge has been
        //consumed in both directions
        int[][] tmpMatrix = new int[matrixSize][matrixSize];
        for(int i = 0; i < matrixSize; i ++)
        {
            for(int j = 0; j < matrixSize; j ++)
            {
                tmpMatrix[i][j] = matrix[i][j];
            }
        }
        tmpMatrix[x][y] = 0;
        tmpMatrix[y][x] = 0;
        return tmpMatrix;
    }

    public LinkedList<String> startCycle(int start, int[][] adjMatrix, int matrixSize)
    {
        //System.out.println(start + "\n\n");
        int length = -1;
        LinkedList<String> path = new LinkedList<String>();
        LinkedList<String> tmpCycle = new LinkedList<String>();
        for(int i = 1; i < matrixSize; i ++)
        {
            if((adjMatrix[start][i] == 1) && (i != start))
            {
                int[][] copyMatrix = new int[matrixSize][matrixSize];
                copyMatrix = this.clearCell(adjMatrix, start, i, matrixSize);
                tmpCycle = longestCycle(start, copyMatrix, matrixSize, i);
                if(((tmpCycle.size() -1) > length) && (tmpCycle.getLast().equals(Integer.toString(start))))
                {
                    path.clear();
                    path.addAll(tmpCycle);
                    path.addFirst(Integer.toString(start));
                    length = path.size()-1;
                }
            }
        }
        return path;
    }

    private LinkedList<String> longestCycle (int start, int[][] adjMatrix, int matrixSize, int current)
    {
        //this.printMatrix(adjMatrix, matrixSize);
        int length = -1;
        LinkedList<String> tmpPath = new LinkedList<String>();
        LinkedList<String> path = new LinkedList<String>();
        path.add(Integer.toString(current));

        for(int j = 0; j < matrixSize; j++)
        {
            if((j == start) && (1 > length) && (adjMatrix[current][j] == 1))
            {
                length = 1;
                path.add(Integer.toString(start));
            }
            else if(adjMatrix[current][j] != 0)
            {
                //System.out.println(current + ", " + j);
                int[][] copyMatrix = new int[matrixSize][matrixSize];
                copyMatrix = this.clearCell(adjMatrix, current, j, matrixSize);
                tmpPath = longestCycle(start, copyMatrix, matrixSize, j);
                //System.out.println(tmpPath.getLast().equals(Integer.toString(start)));

                if(((tmpPath.size()) > length) && (tmpPath.getLast().equals(Integer.toString(start))))
                {
                    //System.out.println("Arrived");
                    length = tmpPath.size();
                    path = tmpPath;
                    path.addFirst(Integer.toString(current));
                    /*for(int i = 0; i < path.size(); i++)
                    {
                        System.out.println(path.get(i));
                    }*/
                }
            }
        }

        return path;
    }

    private void printPath(LinkedList<String> path)
    {
        System.out.println(path.size() - 1);
        for(int i = 0; i < path.size(); i ++)
        {
            System.out.println(path.get(i));
        }
    }

    private void printMatrix(int[][] matrix, int matrixSize)
    {
        System.out.print("\t");
        for (int k = 1; k < matrixSize; k ++)
        {
            System.out.print(k + "\t");
        }
        System.out.println();
        for(int i = 1; i < matrixSize; i ++)
        {
            System.out.print(i + "\t");
            for(int j = 1; j < matrixSize; j ++)
            {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("\n\n");
        }
    }

    public static void main (String[] args)
    {
        Homework7 h7 = new Homework7();
        Scanner scan = new Scanner(System.in);
        LinkedList<Integer> islands = new LinkedList<Integer>();

        while(scan.hasNext())
        {
            islands.add(scan.nextInt());
        }
        scan.close();
        int matrixSize = h7.getSize(islands);

        int adjMatrix[][] = new int[matrixSize][matrixSize];

        for(int a = 0; a < matrixSize; a++)
        {
            for(int b = 0; b < matrixSize; b++)
            {
                adjMatrix[a][b] = 0;
            }
        }

        for (int i = 0; i < islands.size(); i += 2)
        {
            //reading the pairs from the input
            //System.out.println(islands.get(i) + ", " + islands.get(i+1));
            adjMatrix[islands.get(i)][islands.get(i+1)] = 1;
            adjMatrix[islands.get(i+1)][islands.get(i)] = 1;
        }

        //h7.printMatrix(adjMatrix, matrixSize);

        h7.printPath(h7.startCycle(islands.get(0), adjMatrix, matrixSize));
    }
}
