package hard;
import java.util.*; 

public class SlidingWindowMedian {
	/**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
	   public class Element {
	        public int val; 
	        public int pos; 
	        public boolean inmax; 
	        public Element(int v){
	            val = v; 
	            pos = 0; 
	            inmax=true; 
	        }
	    } 
	    public class MedianHeap{
	        public Element[] max_heap = null;
	        public Element[] min_heap = null; 
	        int max_size, min_size; 
	        public MedianHeap(int k){
	            int size = k/2+1; 
	            max_heap = new Element[size];
	            min_heap = new Element[size];
	            max_size=min_size=0; 
	        }
	        public void swap(Element[] array, int a, int b){
	            array[a].pos = b; 
	            array[b].pos = a; 
	            Element t = array[a]; 
	            array[a] = array[b]; 
	            array[b] = t; 
	        }
	        public void add2Max(Element e){
	            e.pos = max_size;
	            e.inmax=true; 
	            max_heap[max_size++] = e; 
	            int cur = max_size-1;
	            int pre = (cur-1)/2; 
	            while (cur>0 && max_heap[pre].val<max_heap[cur].val){
	                swap(max_heap, cur, pre);  
	                cur = pre; 
	                pre = (cur-1)/2; 
	            }
	        }
	        public void add2Min(Element e){
	            e.pos = min_size;
	            e.inmax=false; 
	            min_heap[min_size++] = e; 
	            int cur = min_size-1;
	            int pre = (cur-1)/2; 
	            while (cur>0 && min_heap[pre].val>min_heap[cur].val){
	                swap(min_heap, cur, pre);  
	                cur = pre; 
	                pre = (cur-1)/2; 
	            }
	        }
	        public Element removeMax(int pos){
	            Element ret = max_heap[pos]; 
	            swap(max_heap, pos, --max_size); 
	            int cur = pos; 
	            while (cur<max_size){
	                int left = cur*2+1;
	                int change = -1; 
	                if (left<max_size && max_heap[left].val>max_heap[cur].val){
	                    change = left; 
	                }
	                int right = left+1; 
	                if (right<max_size && max_heap[right].val>max_heap[cur].val){
	                    if (change<0 || max_heap[right].val>max_heap[left].val) change=right;
	                }
	                if (change<0) break; 
	                swap(max_heap, cur, change);  
	                cur = change; 
	            }
	            int pre = (cur-1)/2; 
	            while (cur>0 && max_heap[pre].val<max_heap[cur].val){
	                swap(max_heap, cur, pre);  
	                cur = pre; 
	                pre = (cur-1)/2; 
	            }
	            return ret; 
	        }
	        public Element removeMin(int pos){
	            Element ret = min_heap[pos]; 
	            swap(min_heap, pos, --min_size); 
	            int cur = pos; 
	            while (cur<min_size){
	                int left = cur*2+1;
	                int change = -1; 
	                if (left<min_size && min_heap[left].val<min_heap[cur].val){
	                    change = left; 
	                }
	                int right = left+1; 
	                if (right<min_size && min_heap[right].val<min_heap[cur].val){
	                    if (change<0 || min_heap[right].val<min_heap[left].val) change=right;
	                }
	                if (change<0) break; 
	                swap(min_heap, cur, change);  
	                cur = change; 
	            }
	            int pre = (cur-1)/2; 
	            while (cur>0 && min_heap[pre].val>min_heap[cur].val){
	                swap(min_heap, cur, pre);  
	                cur = pre; 
	                pre = (cur-1)/2; 
	            }
	            return ret; 
	        }
	        public void add(Element e){
	            if (max_size==0) {
	            	add2Max(e); 
	                return; 
	            }
	            if (e.val<=max_heap[0].val){
	                add2Max(e); 
	            }else {
	                add2Min(e); 
	            }
	            if (max_size>min_size+1){
	                add2Min(removeMax(0)); 
	            }else if (max_size<min_size){
	                add2Max(removeMin(0)); 
	            }
	        }
	        public void remove(Element e){
	            if (e.inmax){
	                removeMax(e.pos); 
	            }else {
	                removeMin(e.pos); 
	            }
	            if (max_size>min_size+1){
	                add2Min(removeMax(0)); 
	            }else if (max_size<min_size){
	                add2Max(removeMin(0)); 
	            }
	        }
	    } 
	    
	    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
	        // write your code here
	        ArrayList<Integer> res = new ArrayList<Integer>(); 
	        if (nums.length==0) return res; 
	        MedianHeap median_heap = new MedianHeap(k);
	        Queue<Element> queue = new LinkedList<Element>();
	        for (int i=0; i<k; i++){
	            Element e = new Element(nums[i]); 
	            median_heap.add(e); 
	            queue.add(e); 
	            if (i==nums.length-1){
	                res.add(median_heap.max_heap[0].val); 
	                return res; 
	            }
	        }
	        res.add(median_heap.max_heap[0].val); 
	        for (int i=k; i<nums.length; i++){
	            median_heap.remove(queue.poll()); 
	            Element e = new Element(nums[i]); 
	            median_heap.add(e); 
	            queue.add(e);
	            res.add(median_heap.max_heap[0].val);
	        }
	        return res; 
	    }
}
