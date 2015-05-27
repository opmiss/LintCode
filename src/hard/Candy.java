package hard;

public class Candy {
	/**
     * @param ratings Children's ratings
     * @return the minimum candies you must give
     */
    public int candy(int[] ratings) {
        // Write your code here
        int n = ratings.length; 
        if (n==0) return 0; 
        int[] candies = new int[n];
        if (n==1) return 1;
        candies[0] =1; 
        for (int i=1; i<n; i++){
            candies[i] = (ratings[i]>ratings[i-1])?(candies[i-1]+1):1;      
        }
        int prev = 1;
        int total = Math.max(prev, candies[n-1]); 
        for (int i=(n-2); i>=0; i--){
            prev = (ratings[i]>ratings[i+1])?(prev+1):1; 
            total+=Math.max(prev, candies[i]);  
        }
        return total; 
    }

}
