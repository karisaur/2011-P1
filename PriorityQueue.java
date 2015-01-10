public class PriorityQueue<T extends Comparable<? super T>>{

   private Node<T> head, tail;
   int size = 0;
   int time = 0;
   
  public PriorityQueue(){
      this.head = null;
      this.tail = null;
	  size = 0;
   }//constructor, no overhead nodes
   
   // Inserts a new node in the queue based on priority
   public boolean enqueue(int priority, int duration, String data){
	  Node<T> newNode = new Node<T>(priority, duration, data);
	  System.out.println("Inserting Node: " + newNode.getName());
	  if (this.isEmpty()){
	     this.head = newNode;
		 this.tail = newNode;
	 }else {
		 /** If the list is NOT empty, we need to figure out
		  * where to insert the node based on priority.  
		  * There are two main cases: one being that the newNode
		  * has the highest priority, and since we cannot interrupt
		  * the node at the head, we must insert after the head.
		  * The second being that we will have to find the correct location.**/
		  Node<T> current = this.tail;   
		  
		  while(current.getPrev() != null){
		     if (current.priority > newNode.priority)
			    current = current.getPrev();
		  else {// we have to insert between the current and trailing node
			  Node<T> currentTrail = current.getNext();
			  current.setNext(newNode);
			  newNode.setPrev(current);
			  newNode.setNext(currentTrail);
			  if (currentTrail != null)
				 currentTrail.setPrev(newNode);
			  else if (current == this.tail)
				  this.tail = newNode;
		  }
			  size++;
			  return true;
		 }if (current == this.head){
			 Node<T> currentTrail = current.getNext();
			 this.head.setNext(newNode);
			 newNode.setPrev(this.head);
			 newNode.setNext(currentTrail);
			 if (currentTrail != null)
				currentTrail.setPrev(newNode);
			 if (current == this.tail)
				 this.tail = newNode;
		 }	
   }
	  size++;
	  return true;
  }

public Node<T> dequeue(){
	  // Dequeue always removes the FIRST node in the list.
	  // If the list is empty, then there is nothing to remove.
	  // If the list isn't empty then you can remove the node. 
	  if (!this.isEmpty()){
		 Node<T> oldHead = this.head;
		 System.out.println("Removing node: " + oldHead.name);
		 this.head = this.head.next;
	     if(this.head != null) head.setPrev(null);
		 size--;
	     return oldHead;
		}else 
		  System.err.println("List is empty, Node cannot be removed");
	  return null;
   }// end of dequeue method.
   
   public Node<T> update(){
      if(!this.isEmpty()){
         this.head.update();
    	 if(this.head.getFinished() == 0)
    		 return this.dequeue();
      }
	  return null;
   }
   public void print(){
	   Node<T> current = head;
	   String output = "[";
	   while (current != null){
	      output += current.getName() + ", ";
		  current = current.getNext();
	   }
	   output += "]";
	   System.out.println("Print: " + output + " "+size);
   }//end of print method.
   
   public void printBackwards(){
      Node<T> current = tail;
      String outBack = "[";
      while (current != null){
	     outBack += current.getName() + ", ";
		 current = current.getPrev();
	  }
	     outBack += "]";
		 System.out.println("Print reverse: " + outBack + " "+ size);
   }//end of printBackwards method.
   
   //if the head doesn't exist than the list must be empty.
   public boolean isEmpty() {
	if (head == null)
	   return true;
	else
	   return false;
   }
   
   //Node class starts here
   public class Node<T extends Comparable<? super T>> {
	  private int priority, finished; 
	  private String name;
	  private Node<T> prev, next; 
      
	  public Node(int priority, int finished, String name){
         this.priority = priority;	
		 this.finished = finished;
		 this.name = name;
		 this.prev = null;
		 this.next = null;
	  } 
   public void update() {
      this.setFinished(this.getFinished() - 1);
   }
   public int getFinished() {
		return this.finished;
	}
public String getName() {
	   return this.name.toString();
	}
   public int getPriority(){
      return this.priority;
   }
   public Node<T> getPrev(){
   	  return this.prev;
   }
   public Node<T> getNext(){
      return this.next;
   }
   public void setPrev(Node<T> prev) {
      this.prev = prev;
   }
   public void setNext(Node<T> next) {
      this.next = next;		
   } 
   private void setFinished(int i) {
      this.finished = i;
   }
  }// Node class
}
