package 数组;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * 包括leecode 167,243,244,245,340,325,323
 * @author Trucy
 *
 */
public class XITI {
	/**
	 * 167. Two Sum II - Input array is sorted
	 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
	 * <p>
	 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
	 * You may assume that each input would have exactly one solution.
	 * <p>
	 * Input: numbers={2, 7, 11, 15}, target=9<p>
	 * Output: index1=1, index2=2<p>
	 * ordered arrays's sum
	 * @param nums
	 * @param target
	 * @return
	 * @see twoSum1
	 */
	 public  int[] twoSum(int[] nums, int target) {
	        int i=0;
	        int j=nums.length-1;
	        while(i<j){
	            if(nums[j]+nums[i]>target)
	                j--;
	            else if(nums[j]+nums[i]<target)
	                i++;
	                else
	                return new int[]{i+1,j+1};
	        }
	       throw new IllegalArgumentException("No two sum solution");
	    }
	 /**
	  * Example:
		Given nums = [2, 7, 11, 15], target = 9,

		Because nums[0] + nums[1] = 2 + 7 = 9,
		return [0, 1].
	  * unordered arrays's sum
	  * @param nums
	  * @param target
	  * @return
	  */
	 public  int[] twoSum1(int[] nums, int target) {
	        HashSet<Integer> hs=new HashSet<>();
	        for(int i=0;i<nums.length;i++)
	        	hs.add(nums[i]);
	        for(int i=0;i<nums.length;i++){
	        	int tmp=target-nums[i];
	        	if(hs.contains(tmp))
	        		for(int j=0;j<nums.length;j++)
	        			if(nums[j]==tmp&&j!=i)
	        				return new int[]{i,j};
	        			
	        }
	        return null;
	    }
	
	 /**
	  *  340 Longest Substring with At Most K Distinct Characters
	  *  Given a string, find the length of the longest substring T that contains at most k distinct characters.
		For example, Given s = “eceba” and k = 2,
		T is "ece" which its length is 3.
	  * @param s
	  * @param k
	  * @return
	  */
	 public  int lengthOfLongestSubstringKDistinct(String s, int k) {
		 if(s.length()==0||k<=0)
			 return 0;
		Queue<Character> q=new ArrayDeque<>();
		Map<Character,Integer> m=new HashMap<>();
		int max=0;
		for(int i=0;i<s.length();i++){
			q.add(s.charAt(i));
			m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0)+1);
			while(m.size()>k){
				char ch=q.poll();
				m.replace(ch, m.get(ch)-1);
				if(m.get(ch)==0)
					m.remove(ch);
			}
			max=max>q.size()?max:q.size();
		}
		
		return max;
	 }
	 /**
	  * 325. Maximum Size Subarray Sum Equals k<p>
	  * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
	  * Example 1:<p>
	  * Given nums = [1, -1, 5, -2, 3], k = 3,<p>
	  * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
	  * @param nums
	  * @param k
	  * @return
	  */
	 public  int maxSubArrayLen(int[] nums, int k) {
		 int acc=0,m=0;
		 HashMap<Integer,Integer> hm=new HashMap<Integer, Integer>();
		 hm.put(0, -1);		/* 保证中间acc等于0时还能从开始初计算acc*/
		 for(int i=0;i<nums.length;i++){
			 acc+=nums[i];
			 if(!hm.containsKey(acc))
				 hm.put(acc, i);
			 if(hm.containsKey(acc-k)){
				 int length=i-hm.get(acc-k);
				 m=length>m?length:m;
			 }
		 }
	        return m;
	    }
	 /**
	  * 323. Number of Connected Components in an Undirected Graph  
	  * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
	  * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
	  * @param n
	  * @param edges
	  * @return
	  */
	 public int countComponents(int n, int[][] edges) {
	        if(n<=1)
	        	return n;
	        int [] ids=new int[n];
	        for(int i=0;i<n;i++)
	        	ids[i]=i;      
	        for(int[] edge:edges){
	        	int x=find(edge[0],ids);
	        	int y=find(edge[1],ids);
	        	if(x!=y){
	        		ids[x]=y;
	        		n--;
	        	}
	        }
	        return n;
	    }
	 private int find(int n,int[] ids){
		 while(ids[n]!=n){
			 ids[n]=ids[ids[n]];
			 n=ids[n];
		 }
		 return n;
	 }
	 /**
	  * 243. Shortest Word Distance<p>
	  *Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.<p>
	  * For example,<p>
	  * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].<p>
	  * Given word1 = “coding”, word2 = “practice”, return 3.<p>
	  * Given word1 = "makes", word2 = "coding", return 1.<p>
	  * assume that word1 does not equal to word2, and word1 and word2 are both in the list.<p>
	  * @param words  
	  * @param word1
	  * @param word2
	  * @return
	  * @see WordDistance.shortest
	  * @see sshortestWordDistance
	  */
	 public int shortestDistance(String[] words, String word1, String word2) {
	        int p1=-words.length,p2=words.length,dis=words.length;
	        for(int i=0;i<words.length;i++){
	        	if(words[i].equals(word1))
	        		p1=i;
	        	else if(words[i].equals(word2))
	        		p2=i;
	        	else
	        		continue;
	        	dis=Math.min(dis, Math.abs(p2-p1));
	        }
	        return dis;        
	    }
	 /**
	  * 245. Shortest Word Distance III<p>
	  * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.<p>
		Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.<p>
		word1 and word2 may be the same and they represent two individual words in the list.<p>
		For example,<p>
		Assume that words = ["practice", "makes", "perfect", "coding", "makes"].<p>
		Given word1 = “makes”, word2 = “coding”, return 2.<p>
		Given word1 = "makes", word2 = "makes", return 3
	  * @param words
	  * @param word1
	  * @param word2
	  * @return
	  * @see WordDistance.shortest
	  * @see shortestDistance
	  */
	 public int shortestWordDistance(String[] words, String word1, String word2) {
		 int p1=-words.length,p2=words.length,dis=words.length;
	        for(int i=0;i<words.length;i++){
	        	if(!word1.equals(word2)){
	        		if(words[i].equals(word1))
		        		p1=i;
		        	else if(words[i].equals(word2))
		        		p2=i;
		        	else
		        		continue;
		        	dis=Math.min(dis, Math.abs(p2-p1));
	        	}else{
	        		if(words[i].equals(word1)){
	        			dis=Math.min(dis,i-p1);
	        			p1=i;
	        		}
	        	}    	
	        }
	        return dis;            
	    }
	 /**
	  * 244. Shortest Word Distance II <p>
	  * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?<p>
	  * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
	  * For example,<p>
		Assume that words = ["practice", "makes", "perfect", "coding", "makes"].<p>
		Given word1 = “coding”, word2 = “practice”, return 3.<p>
		Given word1 = "makes", word2 = "coding", return 1.	<p>
		Note:<p>
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	  * @see shortestDistance
	  * @see shortestWordDistance
	  * @author Trucy
	  *
	  */
	 class WordDistance {
		 	public Map<String,List<Integer>> hm=new HashMap<>();
		    public WordDistance(String[] words) {
		        if(words==null||words.length==0)
		        	throw new ExceptionInInitializerError("words is null or empty!");
		        for(int i=0;i<words.length;i++){
		        	if(!hm.containsKey(words[i])){
		        		List<Integer> l=new ArrayList<>();
		        		l.add(i);
		        		hm.put(words[i], l);
		        	}else{
		        		List<Integer> l=hm.get(words[i]);
		        		l.add(i);
		        	}
		        }
		    }
		    public int shortest(String word1, String word2) {
		    	List<Integer> l1=hm.get(word1);
		    	List<Integer> l2=hm.get(word2);
		    	int min=Integer.MAX_VALUE;
		    	for(int index1:l1)
		    		for(int index2:l2)
		    			min=Math.min(min, Math.abs(index2-index1));	
		    	return min;
		    }	    
		} 
	 int func(){
	 int count = 0;
	 int num = 12345;
	 while (num>0)
	 {
	 count++;
	 num &= (num - 1);
	 }
	 return count;
	 }
	 
	 
	 
	 
	public static void main(String[] args) {
		XITI x=new XITI();
		int[] nums = new int[]{3,2,4};
		int[] res=x.twoSum1(nums,6);
		if(res!=null)
		for(int i=0;i<res.length;i++)
			System.out.println(res[i]+" ");
		System.out.println(x.lengthOfLongestSubstringKDistinct("eceeeeba",2));
		System.out.println(x.func());
		int  xx=4; 
        System.out.println("a"+ ((xx>4) ? 99.9:9));

	}

}
