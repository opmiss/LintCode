package hard;
import java.util.*;

public class SlidingWindowMaximum {
	 /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public class Element {
        public int val; 
        public int pos; 
        public Element(int v){
            val = v; 
            pos = 0; 
        }
    } 
    public class Heap{
        public Element[] array = null; 
        int size; 
        public Heap(int leng){
            array = new Element[leng];
            size =0; 
        }
        public void swap(int a, int b){
            array[a].pos = b; 
            array[b].pos = a; 
            Element t = array[a]; 
            array[a] = array[b]; 
            array[b] = t; 
        }
        public void add(Element e){
            e.pos = size; 
            array[size++] = e; 
            int cur = size-1;
            int pre = (cur-1)/2; 
            while (cur>0 && array[pre].val<array[cur].val){
                swap(cur, pre);
                cur = pre; 
                pre = (cur-1)/2; 
            }
        }
        public Element peek(){
            return array[0]; 
        }
        public void remove(int pos){
            swap(pos, --size); 
            int cur = pos; 
            while (cur<size){
                int left = cur*2+1;
                int change = -1; 
                if (left<size && array[left].val>array[cur].val){
                    change = left; 
                }
                int right = left+1; 
                if (right<size && array[right].val>array[cur].val){
                    if (change<0 || array[right].val>array[left].val) change = right;
                }
                if (change<0) break;  
                swap(cur, change); 
                cur = change; 
            }
            int pre = (cur-1)/2; 
            while (cur>0 && array[pre].val<array[cur].val){
                swap(cur, pre);
                cur = pre; 
                pre = (cur-1)/2; 
            }
        }
    }
    
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>(); 
        if (nums.length==0) return res; 
        if (k>=nums.length){
            int max = nums[0]; 
            for (int i=1; i<nums.length; i++){
                max = (max<nums[i])?nums[i]:max; 
            }
            res.add(max); 
            return res; 
        }
        Heap maxheap = new Heap(k);
        Queue<Element> queue = new LinkedList<Element>();
        for (int i=0; i<k; i++){
            Element e = new Element(nums[i]); 
            maxheap.add(e); 
            queue.add(e); 
        }
        for (int i=k; i<nums.length; i++){
            res.add(maxheap.peek().val);
            maxheap.remove(queue.poll().pos); 
            Element e = new Element(nums[i]); 
            maxheap.add(e); 
            queue.add(e);
            if (queue.size()!=maxheap.size) System.out.println("error!"); 
        }
        res.add(maxheap.peek().val);
        return res; 
    }

}
