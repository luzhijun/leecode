package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 252. Meeting Rooms 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

253. Meeting Rooms II  
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
Company Tags Google Facebook
Tags Heap Greedy Sort
 * @author Trucy
 *
 */

//Definition for an interval.
  class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
 }

public class Lee252 {
	/**
	 * 判断是否有交集
	 * @param A
	 * @param B
	 * @return
	 */
	public boolean isUnion(Interval A,Interval B){
		if(A.start<=B.start&&A.end>B.start||B.start<=A.start&&B.end>A.start)
			return true;
		return false;
	}
	/**
	 * 252 第一种方法
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals.length==1)
            return true;
        for(int i=0;i<intervals.length-1;i++)
        	for(int j=i+1;j<intervals.length;j++)
        		if(isUnion(intervals[i],intervals[j]))
        			return false;     
        return true;
    }
	/**
	 * 252 第二种方法
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings1(Interval[] intervals) {  
	    Arrays.sort(intervals, new Comparator<Interval>() {  
	        @Override  
	        public int compare(Interval o1, Interval o2) {  
	            int r = o1.start - o2.start;  
	            return r==0? o1.end - o2.end : r;  
	        }  
	    });  
	    for(int i=1;i<intervals.length;i++) {  
	        Interval t1 = intervals[i-1];  
	        Interval t2 = intervals[i];  
	        if(t1.end>t2.start) return false;  
	    }  
	    return true;  
	} 
	private int  getMaxFromArray(Interval[] As){
		if(As==null||As.length==0)
			return 0;
		int tmp=As[0].end;
		for(int i=0;i<As.length;i++)
			if(As[i].end>tmp)
				tmp=As[i].end;
		return tmp;	
	}
	/**
	 * 253  第一种方法
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(Interval[] intervals) {
		if(getMaxFromArray(intervals)==0)
	        return 0;
		int[] bucket=new int[getMaxFromArray(intervals)+1];
		for(Interval interval : intervals){
			//Arrays.parallelPrefix(bucket, interval.start, interval.end, (left,right) -> right+1);
			for(int i=interval.start;i<interval.end;i++)
				bucket[i]++;
		}
		return Arrays.stream(bucket).max().getAsInt();
    }
	/**
	 * 253 第二种方法
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms1(Interval[] intervals) {
        int res = 0;
        if(intervals.length == 0) return res;
        if(intervals.length == 1) return res+1;
        List<Integer> points = new ArrayList<>();
        for(int i=0;i<intervals.length;i++) {
            points.add(intervals[i].start);
            points.add(-intervals[i].end);
        }
        //Collections.sort(points, (o1,o2)->Math.abs(o1)-Math.abs(o2));
        
        int local = 0;
        for(int i=0;i<points.size();i++) {
            if(points.get(i)>=0) {
                local++;
            }
            else local--;
            res = Math.max(local, res);
        }
        return res;
    }
	public static void main(String[] args) {
		Interval[] intervals=new Interval[]{new Interval(13,15),new Interval(1,13)};
		Lee252 l2=new Lee252();
		System.out.println(l2.canAttendMeetings(intervals));
		System.out.println(l2.minMeetingRooms1(intervals));

	}

}
