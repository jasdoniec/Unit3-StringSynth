/**
 * RingBuffer models a fixed-capacity circular queue for audio synthesis.
 * 
 * CONCEPTUAL BRIDGE:
 * - add(x)   == enqueue (places item at rear pointer)
 * - remove() == dequeue (removes item from front pointer)
 * - peek()   == front   (looks at element at front pointer)
 */
public class RingBuffer 
{
    private double[] data;
    private int front;
    private int rear;
    private int size;

    /**
     * Creates an empty ring buffer (circular queue) with the given capacity.
     */
    public RingBuffer(int capacity)
    {
        data = new double[capacity];
        front = 0;
        rear = -1;
        size = 0;
        // TODO: Initialize array of size capacity, front = 0, rear = -1, size = 0
    }

    public int size()
    {
        // TODO: Return current number of items in buffer
        // You can use the size variable instead of calculating from front and rear
        return size;
    }
    
    public boolean isEmpty()
    {
        // TODO: Return true if buffer is empty
        return size==0;
    }
    
    public boolean isFull()
    {
        // TODO: Return true if buffer capacity is reached (size == capacity)
        return size == data.length;
    }
    
    /**
     * Enqueues value to the rear of the ring buffer.
     * Throws RuntimeException or QueueOverflowException if buffer is full.
     */
    public void add(double value)
    {
        if (isFull()){
            throw new RuntimeException();
        }

        rear++;
        rear%=data.length;

        data[rear] = value;
        size ++;
        // TODO: Enqueue value at rear, advance rear modulo capacity, increment size
    }
    
    /**
     * Returns (without deleting) the sample at the front of the queue.
     * Throws QueueUnderflowException if buffer is empty.
     */
    public double peek()
    {
        if (isEmpty()){
            throw new QueueUnderflowException();
        }
        return data[front];
    }
    
    /**
     * Dequeues and returns the sample from the front of the queue.
     * Throws QueueUnderflowException if buffer is empty.
     */
    public double remove()
    {
        double out = peek();

        front ++;
        front %= data.length;

        size --;

        // TODO: Dequeue sample from front, advance front modulo capacity, decrement size
        return out;
    }

    public void clear(){
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * Test client to verify RingBuffer circular logic.
     */
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println("Final peek value: " + buffer.peek()); // Expected: 5050.0
    }
}
