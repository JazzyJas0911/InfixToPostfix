
public class numStack {
	
	private class Node{
		private String value;
		private Node next;
		
		public Node (String x){
			value = x;
			next = null;
		}
	}
	
	private Node head;
		
	public numStack (){
		head = null;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public void push(String token){
		Node temp = new Node(token);
		temp.next = head;
		head = temp;
	}
	
	public String pop(){
		if (isEmpty() == true)
			return null;
		else{
			String x = head.value;
			head = head.next;
			return x;
		}
	}
	
	public String top(){ 
		if (isEmpty() == true)
			return null;
		else{
			String x = head.value;
			return x;
		}
	}

}
