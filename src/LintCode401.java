import java.util.PriorityQueue;

/**
 * [URL]: http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/
 * [Description]
 * Find the kth smallest number in at row and column sorted matrix.
 * Example
 Given k = 4 and a matrix:

 [
 [1 ,5 ,7],
 [3 ,7 ,8],
 [4 ,8 ,9],
 ]
 return 5
 */
public class LintCode401 {

    /*
    * Method 1 - Use min heap(java.util.PriorityQueue) to solve the problem
    * Time complexity: O(klogn)
    * n is the bigger one between row size and column size
    * */

    //Method 1 - Data storage class
    public class MatInfo implements Comparable<MatInfo>{
        private int x;
        private int y;
        private int val;

        public MatInfo(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public int getVal(){
            return val;
        }

        @Override
        public int compareTo(MatInfo matInfo){
            return this.val > matInfo.val ? 1 : this.val == matInfo.val ? 0 : -1;
        }
    }
    /**
     * Method 1 - main method
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] mark = new boolean[rows][cols];
        PriorityQueue<MatInfo> minHeap = new PriorityQueue<>();
        minHeap.add(new MatInfo(0, 0, matrix[0][0]));

        for (int i = 0; i < k - 1; i++){
            MatInfo current = minHeap.poll();
            for (int j = 0; j < 2; j++){
                int x = current.getX() + dx[j];
                int y = current.getY() + dy[j];
                if ((x < rows) && (y < cols) && (!mark[x][y]))
                {
                    minHeap.add(new MatInfo(x, y, matrix[x][y]));
                    mark[x][y] = true;
                }
            }
        }

        return minHeap.poll().getVal();
    }


    //TODO - binary search can be implemented here as Method 2.
    //See http://www.jiuzhang.com/solution/kth-smallest-number-in-sorted-matrix/ for more detail.
}
