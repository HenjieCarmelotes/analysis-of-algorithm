package Main;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*;


/**
 * Source: LeetCode
 * 
 * Given an array of integers nums and an integer target, 
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, 
 * and you may not use the same element twice.
 * 
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * 
 * */

public class TwoSum {
	
	public static void main(String[] args) {
		
		int[] uniqueArray = generateUniqueNumbers(500);
		
		
		 //Experiment the time for the second function:
      	long startTime2 = System.currentTimeMillis();
      	twoSumHash(uniqueArray, 76);
      	long endTime2 = System.currentTimeMillis();
      	// Calculate and print the elapsed time
        long elapsedTime2 = endTime2 - startTime2;
        System.out.println("Execution time of using Hash: " + elapsedTime2 + " milliseconds");
        
      //Experiment the time for the third function:
      	long startTime3 = System.currentTimeMillis();
      	twoSumHashOnePass(uniqueArray, 76);
      	long endTime3 = System.currentTimeMillis();
      	// Calculate and print the elapsed time
        long elapsedTime3 = endTime3 - startTime3;
        System.out.println("Execution time of using one pass Hash: " + elapsedTime3 + " milliseconds");
        
		
		
		
		//Experiment the time for the first function:
		long startTime = System.currentTimeMillis();
		twoSum(uniqueArray, 76);
		long endTime = System.currentTimeMillis();
		// Calculate and print the elapsed time
        long elapsedTime = endTime - startTime;
        System.out.println("Execution time of simple for loop: " + elapsedTime + " milliseconds");
        
     
        
        
		 
	}
	
	
	/* Simple Solution:
	 * The brute force approach is simple. 
	 * Loop through each element x and find if there is another value 
	 * that equals to target. 
	 * */
	
	public static int[] twoSum(int[] nums, int target) {
		Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
	
	/* Complexity Analysis:
	 * Time: For each element, we try to find its complement by looping through the rest of the array 
	 * which takes O(n) time. Therefore, the time complexity is O(n^2).
	 * 
	 * Space: The space required does not depend on the size 
	 * of the input array, so only constant space is used, so O(1)
	 * */
	
	
	/*    ***My Solution***     */
	
    public int[] twoSumMySolution(int[] nums, int target) {
    	Arrays.sort(nums);
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            while (j < nums.length) {
                if (nums[j] + nums[i] == target) {
                    return new int [] {i , j};
                }
                j++;
            }
            i++;
        }
        return null;
    }
	
	/*    ***End of My Solution***     */
	
	
	/*    **********     */
	
	/* Using Hash Solution:
	 * We can reduce the lookup time from O(n) to O(1) by trading space for speed. 
	 * A hash table is well suited for this purpose 
	 * because it supports fast lookup in near constant time. 
	 * */
	
	public static int[] twoSumHash(int[] nums, int target) {
		Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
	
	
	/* Complexity Analysis:
	 * Time: We traverse the list containing n elements exactly twice. 
	 * Since the hash table reduces the lookup time to O(1), the overall time complexity is O(n).
	 * 
	 * Space: The extra space required depends on the number of items stored in the hash table, 
	 * which stores exactly n elements.
	 * */
	
	/*    **********     */
	
	/* Using One Pass Hash Solution:
	 * It turns out we can do it in one-pass. 
	 * While we are iterating and inserting elements into the hash table, 
	 * we also look back to check if current element's complement already exists in the hash table.
	 * */
	
	public static int[] twoSumHashOnePass(int[] nums, int target) {
		Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
	
	/* Complexity Analysis:
	 * Time: We traverse the list containing n elements only once. 
	 * Each lookup in the table costs only O(1) time.
	 * 
	 * Space: The extra space required depends on the number of items stored in the hash table, 
	 * which stores at most n elements.
	 * */
	
	
	// Function to generate an array of unique numbers
    private static int[] generateUniqueNumbers(int size) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int[] uniqueArray = new int[size];

        for (int i = 0; i < size; i++) {
            int randomNumber;
            do {
                randomNumber = (int) (Math.random() * 500);
            } while (!uniqueNumbers.add(randomNumber));

            uniqueArray[i] = randomNumber;
        }

        return uniqueArray;
    }


}
