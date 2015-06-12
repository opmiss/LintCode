package hard;

public class BinaryRepresentation {
	/**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        String[] parts = n.split("\\.");
        int intpart = Integer.parseInt(parts[0]);
        parts[1] = "0."+parts[1]; 
        double floatpart = Double.parseDouble(parts[1]);
        StringBuffer right = convertFloat(floatpart);
        if (right==null) return "ERROR"; 
        if (right.length()==0) return convertInt(intpart).toString(); 
        return convertInt(intpart).append(".").append(right).toString(); 
    }
    StringBuffer convertInt(int number){
        StringBuffer res = new StringBuffer(); 
        if (number==0){
            res.append('0'); 
            return res; 
        } 
        boolean neg = false; 
        if (number<0){
            neg = true; 
            number = -number; 
        }
        while (number>0){
            res.append(number%2); 
            number = number/2; 
        }
        if (neg){
            res.append('-'); 
        }
        return res.reverse(); 
    }
    StringBuffer convertFloat(double number){
        StringBuffer res = new StringBuffer(); 
        int leng = 0; 
        while (number>0){
            number = number*2;
            if (number>1){
                res.append('1');
                number = number-1; 
            }
            else if (number<1){
                res.append('0'); 
            }
            else {
            	res.append('1'); 
            	return res; 
            }
            if (++leng>32) return null; 
        }
        return res; 
    }
    public static void main(String[] args){
    	BinaryRepresentation br = new BinaryRepresentation(); 
    	System.out.println(br.binaryRepresentation("3.72")); 
    	System.out.println(br.binaryRepresentation("1.0")); 
    }
}
