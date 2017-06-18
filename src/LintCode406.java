/**
 * [Date]: 06.17.2017
 * [URL]: http://www.lintcode.com/en/problem/minimum-size-subarray-sum/
 * [Description]
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
 * Example
 Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.
 */
public class LintCode406 {

    //Method 1 - Solution of LintCode
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null)
            return -1;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int j = 0;
        for (int i = 0; i < nums.length;i++){
            while ((j < nums.length) && (sum < s)){
                sum += nums[j];
                j++;
            }
            if (sum >= s)
                minLength = Math.min(minLength, j - i);
            sum -= nums[i];

        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }



    //Method 2 - No tail pointer index check. But it's guaranteed no out of bound. Since all this values are positive integers and sum >= s.
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize2(int[] nums, int s) {
        // write your code here
        int sum = 0;
        int tail = 0;
        int length = nums.length + 1;
        int i = 0;
        while (i < nums.length){
            if (sum >= s){
                length = Math.min(length, i - tail);
                sum -= nums[tail++];
            } else {
                sum += nums[i++];
            }
        }
        while (sum >= s){
            length = Math.min(length, i - tail);
            sum -= nums[tail++];
        }
        return length == (nums.length + 1) ? -1 : length;
    }
}
