import java.util.Stack;
import java.util.Arrays;


public class Board
{
   
    private int dimension;

   
    private int[][] blocksBoard;
  
    public Board(int[][] blocks)
    {
        dimension = blocks.length;
        blocksBoard = copyBlocks(blocks, dimension);
    }

  
    public int dimension()
    {
        return dimension;
    }

    public int hamming()
    {
        int count = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                if ((blocksBoard[i][j] != 0) && (blocksBoard[i][j] != expectedValueAtPosition(i, j))) {
                    count++;
                }
            }
        }

        return count;
    }

    public int manhattan()
    {
        int sum = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                if ((blocksBoard[i][j] != 0) && (blocksBoard[i][j] != expectedValueAtPosition(i, j))) {
                    int expectedI = (blocksBoard[i][j] - 1) / dimension;
                    int expectedJ = (blocksBoard[i][j] - 1) % dimension;

            
                    sum += Math.abs(i - expectedI) + Math.abs(j - expectedJ);
                }
            }
        }

        return sum;
    }

    
    public boolean isGoal()
    {
      
        return (hamming() == 0);
    }

   
    public Board twin()
    {
        int[][] blocksCopy = copyBlocks(blocksBoard, dimension);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension - 1; j++) {

                if ((blocksCopy[i][j] != 0) && (blocksCopy[i][j + 1] != 0)) {
                    int toSwap = blocksCopy[i][j];
                    blocksCopy[i][j] =  blocksCopy[i][j + 1];
                    blocksCopy[i][j + 1] = toSwap;

                    return new Board(blocksCopy);
                }
            }
        }

        return null;
    }

    public boolean equals(Object y)
    {
        if (y == null) {
            return false;
        }

        if (y == this) {
            return true;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;

        return Arrays.deepEquals(this.blocksBoard, that.blocksBoard);
    }

   
    public Iterable<Board> neighbors()
    {
        Stack<Board> boardStack = new Stack<>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
         
                if (blocksBoard[i][j] == 0) {

              
                    if (i > 0) {
                        int[][] blocksCopy = copyBlocks(blocksBoard, dimension);
                        blocksCopy[i][j] = blocksBoard[i - 1][j];
                        blocksCopy[i - 1][j] = blocksBoard[i][j];
                        boardStack.push(new Board(blocksCopy));
                    }

             
                    if (j > 0) {
                        int[][] blocksCopy = copyBlocks(blocksBoard, dimension);
                        blocksCopy[i][j] = blocksBoard[i][j - 1];
                        blocksCopy[i][j - 1] = blocksBoard[i][j];
                        boardStack.push(new Board(blocksCopy));
                    }

             
                    if (i < dimension - 1) {
                        int[][] blocksCopy = copyBlocks(blocksBoard, dimension);
                        blocksCopy[i][j] = blocksBoard[i + 1][j];
                        blocksCopy[i + 1][j] = blocksBoard[i][j];
                        boardStack.push(new Board(blocksCopy));
                    }

                 
                    if (j < dimension - 1) {
                        int[][] blocksCopy = copyBlocks(blocksBoard, dimension);
                        blocksCopy[i][j] = blocksBoard[i][j + 1];
                        blocksCopy[i][j + 1] = blocksBoard[i][j];
                        boardStack.push(new Board(blocksCopy));
                    }

                    break;
                }
            }
        }

        return boardStack;
    }

  
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocksBoard[i][j]));
            }
            s.append("\n");
        }

        return s.toString();
    }

   
    private int[][] copyBlocks(int[][] blocks, int dimensionSize) {
        int[][] result = new int[dimensionSize][dimensionSize];

        for (int i = 0; i < dimensionSize; i++) {
            for (int j = 0; j < dimensionSize; j++) {
                result[i][j] = blocks[i][j];
            }
        }

        return result;
    }

   
    private int expectedValueAtPosition(int i, int j)
    {
        return ((i * dimension) + j + 1);
    }

      
    public static void main(String[] args)
    {
        
    }
}
