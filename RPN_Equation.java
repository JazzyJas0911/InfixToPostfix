/* Jasmin Agustin
 * CECS 274 - 05
 * Project 4 - RPN Equation (Stacks and Queues)
 * 11/8/2016
 */

import java.util.ArrayList;
import java.util.Scanner;
public class RPN_Equation {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		boolean valid = false;
		ArrayList<String> RPNequation = new ArrayList<String>();
		String[] token = null;
		numStack stack = new numStack();
		while (valid == false){
			int nOperators = 0;
			int nOperands = 0;
			System.out.println("Please enter a mathematical equation.");
			String equation = keyboard.nextLine();
			equation = equation.replaceAll("\\+", " + ");
			equation = equation.replaceAll("\\-", " - ");
			equation = equation.replaceAll("\\*", " * ");
			equation = equation.replaceAll("\\/", " / ");
			equation = equation.replaceAll("\\^", " ^ ");
			equation = equation.replaceAll("\\(", " ( ");
			equation = equation.replaceAll("\\)", " ) ");
			token = equation.split("[ ]+");
			for(int i = 0; i < token.length; i++){
				if (isOperator(token[i]) == true)
					nOperators++;
				else if (token[i].matches("[0-9]+"))
					nOperands++;
			}
			if (nOperators == nOperands - 1)
				valid = true;
			else
				System.out.println("Error. Invalid equation.");
		}
		
		//infix to postfix
		for(int i = 0; i < token.length; i++){
			if (token[i].matches("[0-9]+"))
				RPNequation.add(token[i]);
			else if (isOperator(token[i]) == true || token[i].equals("(") || token[i].equals(")")){
				if (token[i].equals("+")){
					while(stack.isEmpty() == false && !(stack.top().equals("("))){
						RPNequation.add(stack.pop());
					}
					stack.push(token[i]);
				}
				else if (token[i].equals("-")){
					while(stack.isEmpty() == false && !(stack.top().equals("("))){
						RPNequation.add(stack.pop());
					}
					stack.push(token[i]);
				}
				else if (token[i].equals("/")){
					while(stack.isEmpty() == false && (stack.top().equals("^") || stack.top().equals("*") || stack.top().equals("/"))){
						RPNequation.add(stack.pop());
					}
					stack.push(token[i]);
				}
				else if (token[i].equals("*")){
					while(stack.isEmpty() == false && (stack.top().equals("^") || stack.top().equals("*") || stack.top().equals("/"))){
						RPNequation.add(stack.pop());
					}
					stack.push(token[i]);
				}
				else if (token[i].equals("^")){
					if(stack.isEmpty() == false && stack.top().equals("^")){
						RPNequation.add(stack.pop());
					}
					stack.push(token[i]);
				}
				else if (token[i].equals("(")){
					stack.push(token[i]);
				}
				else if (token[i].equals(")")){
					while(stack.isEmpty() == false && !(stack.top().equals("("))){
						RPNequation.add(stack.pop());
					}
					stack.pop();
				}
			}
		}
		while(stack.isEmpty() == false){
			RPNequation.add(stack.top());
			stack.pop();
		}
		System.out.print("RPN: ");
		for(int i = 0; i < RPNequation.size(); i++){
			System.out.print(RPNequation.get(i) + " ");
		}
		
		//evaluate
		String[] RPNstack = new String[RPNequation.size()];
		RPNstack = RPNequation.toArray(RPNstack);
		numStack evaluate = new numStack();
		String answer;
		double x, y;
		double total = 0;
		for(int i = 0; i < RPNstack.length; i++){
			if (RPNstack[i].matches("[0-9]+"))
				evaluate.push(RPNstack[i]);
			else if (isOperator(RPNstack[i]) == true || RPNstack[i].equals("(") || RPNstack[i].equals(")")){
				y = Double.parseDouble(evaluate.pop());			
				x = Double.parseDouble(evaluate.pop());
				if (RPNstack[i].equals("+"))
					total = x + y;
				else if (RPNstack[i].equals("-"))
					total = x - y;
				else if (RPNstack[i].equals("/"))
					total = (double) x / y;
				else if (RPNstack[i].equals("*"))
					total = x * y;
				else if (RPNstack[i].equals("^"))
					total = Math.pow(x, y);
				answer = String.valueOf(total);
				evaluate.push(answer);
			}
		}
		System.out.println("\nAnswer: " + evaluate.top());
		
	}//end of main

	public static boolean isOperator(String token){
		return (token.equals("^") || token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-"));
	}
	
}//end of class
