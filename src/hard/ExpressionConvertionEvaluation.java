package hard;
import java.util.*;

public class ExpressionConvertionEvaluation {
	/**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        ArrayList<String> output = new ArrayList<String>(); 
        Stack<String> stack = new Stack<String>(); 
        for (int i=0; i<expression.length; i++){
            switch(type(expression[i])){
                case 0:
                    stack.push(expression[i]); 
                    break; 
                case 1:
                    while ((!stack.isEmpty())&&type(stack.peek())>=1) {
                        output.add(stack.pop());
                    }
                    stack.push(expression[i]);
                    break; 
                case 2: 
                    while ((!stack.isEmpty())&&type(stack.peek())==2) {
                        output.add(stack.pop());
                    }
                    stack.push(expression[i]);
                    break; 
                case 3: 
                    while (type(stack.peek())!=0) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    break; 
                case 4: 
                    output.add(expression[i]); 
                    break; 
            }
        }
        while (!stack.isEmpty()){
            output.add(stack.pop()); 
        }
        return output; 
    }
    public ArrayList<String> convertToPN(String[] expression) {
        // write your code here
        Stack<String> stack = new Stack<String>(); 
        ArrayList<String> output = new ArrayList<String>(); 
        for (int i=expression.length-1; i>=0; i--){
            switch(type(expression[i])){
                case 3: 
                    stack.push(expression[i]); 
                    break; 
                case 2: 
                    stack.push(expression[i]);
                    break; 
                case 1: 
                    while (!stack.isEmpty()&&type(stack.peek())==2){
                        output.add(stack.pop()); 
                    }
                    stack.push(expression[i]);
                    break;
                case 0: 
                    while (type(stack.peek())!=3){
                        output.add(stack.pop()); 
                    }
                    stack.pop(); 
                    break; 
                case 4: 
                    output.add(expression[i]);
                    break; 
            }
        }
        while (!stack.isEmpty()) output.add(stack.pop()); 
        Collections.reverse(output);
        return output; 
    }
    
    public int type(String op){
        if (op.equals("(")){
            return 0; 
        }
        if (op.equals("+")||op.equals("-")){
            return 1; 
        }
        if (op.equals("*")||op.equals("/")){
            return 2; 
        }
        if (op.equals(")")){
            return 3; 
        }
        return 4; 
    }
    
    public int evaluateExpression(String[] expression) {
        // write your code here
        if (expression.length==0) return 0; 
        ArrayList<String> rpn = convertToRPN(expression);
        if (rpn.size()==0) return 0; 
        Stack<Integer> res = new Stack<Integer>(); 
        int a, b; 
        for (String e:rpn){
            if (e.equals("+")){
                res.push(res.pop()+res.pop()); 
            }
            else if (e.equals("-")){
                b = res.pop(); 
                a = res.pop(); 
                res.push(a-b); 
            }
            else if (e.equals("*")){
                res.push(res.pop()*res.pop()); 
            }
            else if (e.equals("/")){
                b = res.pop(); 
                a = res.pop(); 
                res.push(a/b); 
            }
            else {
                res.push(Integer.parseInt(e)); 
            }
        }
        return res.pop(); 
    }

}
