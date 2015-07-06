package hard;
import util.ListNode;

public class LinkedListCycle {
	/**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {  
        // write your code here
        if (head==null||head.next==null||head.next.next==null) return null; 
        ListNode slow = head; 
        ListNode fast = head; 
        while (fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next; 
            slow=slow.next; 
            if (fast==slow) break; 
        }
        if (fast!=slow) return null; 
        slow = head; 
        while (fast!=slow){
            slow = slow.next; 
            fast = fast.next; 
        }
        return slow; 
    }

}
