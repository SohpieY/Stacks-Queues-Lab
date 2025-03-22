package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IQueue;
import common.QueueEmptyException;
import common.QueueFullException;

/**
 * tests double stack queue implementation
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IQueue queue;

    /**
     * sets up a new queue before each test
     */
    @BeforeEach
    public void setUp() {
        queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE); // get a fresh queue for each test
    }

    /**
     * tests that the factory constructs a non-null object
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        assertNotNull(queue, "whoa, got a null queue from the factory, not what i expected");
    }

    /**
     * tests that we can enqueue elements
     */
    @Test
    public void testEnqueue() throws QueueFullException {
        queue.enqueue("element 1");
        queue.enqueue("element 2");
        assertEquals(2, queue.size(), "should be able to enqueue elements");
    }

    /**
     * tests that enqueueing to a full queue throws an exception
     */
    @Test
    public void testEnqueueFullQueue() throws QueueFullException {
        for (int i = 0; i < DEFAULT_MAX_SIZE / 2; i++) {
            queue.enqueue("element " + i); // fill it up
        }
        assertThrows(QueueFullException.class, () -> {
            queue.enqueue("element 11"); // this should throw an exception
        }, "should throw QueueFullException when trying to enqueue to a full queue");
    }

    /**
     * tests that we can dequeue elements
     */
    @Test
    public void testDequeue() throws QueueFullException, QueueEmptyException {
        queue.enqueue("element 1");
        queue.enqueue("element 2");
        assertEquals("element 1", queue.dequeue(), "should dequeue elements in order");
        assertEquals(1, queue.size(), "size should be updated after dequeue");
    }


    /**
     * tests that dequeueing from an empty queue throws an exception
     */
    @Test
    public void testDequeueEmptyQueue() {
        assertThrows(QueueEmptyException.class, () -> {
            queue.dequeue(); // this should throw an exception
        }, "dequeueing from an empty queue should throw QueueEmptyException");
    }

    /**
     * tests if the queue is empty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty(), "queue should be empty initially");
    }

    /**
     * tests clearing the queue
     */
    @Test
    public void testClear() throws QueueFullException {
        queue.enqueue("element 1");
        queue.enqueue("element 2");
        queue.clear();
        assertTrue(queue.isEmpty(), "queue should be empty after clearing");
    }
}