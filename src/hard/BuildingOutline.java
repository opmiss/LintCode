package hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BuildingOutline {
	class Event{
        Building bui; 
        int pos; 
        boolean enter; 
        Event(Building b, int p, boolean e){
            bui = b; 
            pos = p; 
            enter = e; 
        }
    }
    public Comparator<Event> EventComparator = new Comparator<Event>(){
        public int compare(Event e1, Event e2){
            int r = e1.pos - e2.pos;
            if (r!=0) return r; 
            if (e1.enter && e2.enter){
                if (e1.bui.height==e2.bui.height) return 0; 
                else return (e1.bui.height>e2.bui.height)?-1:1; 
            }
            if (!(e1.enter||e2.enter)) {
                if (e1.bui.height==e2.bui.height) return 0; 
                else return (e1.bui.height>e2.bui.height)?1:-1; 
            }
            if (e1.enter) return -1; 
            return 1; 
        }
    };
    class Building{
        int bid; 
        int height; 
        Building(int b, int h){
            bid = b; 
            height = h; 
        }
    }
    public Comparator<Building> BuildingComparator=new Comparator<Building>(){
        public int compare(Building b1, Building b2){
            return b2.height-b1.height; 
        }
    };
    public void handleEvents(Event cur, Queue<Building> activeBuildings, ArrayList<ArrayList<Integer>> ret, int[][] buildings){
        if (cur.enter){
            Building top = activeBuildings.peek(); 
            if (top==null||top.height<cur.bui.height) {
                ArrayList<Integer> turn = new ArrayList<Integer>(); 
                turn.add(cur.pos); 
                turn.add(cur.bui.height); 
                ret.add(turn); 
                activeBuildings.add(cur.bui); 
            }
            else {
                int topend = buildings[top.bid][1]; 
                int curend = buildings[cur.bui.bid][1]; 
                if (topend<curend)
                    activeBuildings.add(cur.bui); 
            }
        }
        else{
            boolean rm = activeBuildings.remove(cur.bui);
            if (!rm) return; 
            Building top = activeBuildings.peek(); 
            if (top==null||top.height<cur.bui.height){
                ArrayList<Integer> turn = new ArrayList<Integer>(); 
                turn.add(cur.pos); 
                turn.add( (top==null)?0:top.height); 
                ret.add(turn); 
            }
        }
    }
    
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Queue<Event> events = new PriorityQueue<Event>(10000, EventComparator);
        Queue<Building> activeBuildings = new PriorityQueue<Building>(10000, BuildingComparator);
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>(); 
        for (int i=0; i<buildings.length; i++){
            Building bui = new Building(i, buildings[i][2]); 
            events.add(new Event(bui, buildings[i][0], true));
            events.add(new Event(bui, buildings[i][1], false)); 
            Event cur = events.poll(); 
            handleEvents(cur, activeBuildings, ret, buildings); 
        }
        while (!events.isEmpty()){
            Event cur = events.poll(); 
            handleEvents(cur, activeBuildings, ret, buildings);
        }
        return ret; 
    }

}
