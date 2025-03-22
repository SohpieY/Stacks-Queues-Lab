package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;
import common.StackOverflowException;
import common.StackEmptyException;

/**
 * Tests array collection implementation for DoubleStack.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IDoubleStack doubleStack;

    @BeforeEach
    public void setUp() {
        doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE); // Get a new double stack for each test
    }

    /**
     * tests that the factory constructs a non-null double stack
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        assertNotNull(doubleStack, "Uh oh! The factory returned a null double stack");
    }

    /**
     * tests pushing elements onto the first stack
     */
    @Test
    public void testPushToFirstStack() throws StackOverflowException {
        doubleStack.getFirstStack().push("Element 1");
        doubleStack.getFirstStack().push("Element 2");
        assertEquals(2, doubleStack.getFirstStack().size(), "Should be able to push elements to the first stack");
    }

    /**
     * Tests that pushing to a full first stack throws an exception.
     */
    @Test
    public void testPushFullFirstStack() throws StackOverflowException {
        // push 5 elements to the first stack (if stack can hold 1/2 total size)
        for (int i = 0; i < 5; i++) {
            doubleStack.getFirstStack().push("Element " + i);
        }
        assertThrows(StackOverflowException.class, () -> {
            doubleStack.getFirstStack().push("Element 5"); // this should fail
        }, "should throw StackOverflowException when pushing to a full stack");
    }

    /**
     * Tests popping elements from the first stack.
     */
    @Test
    public void testPopFromFirstStack() throws StackOverflowException, StackEmptyException {
        doubleStack.getFirstStack().push("Element 1");
        assertEquals("Element 1", doubleStack.getFirstStack().pop(), "Should pop the last pushed element");
    }

    /**
     * Tests popping from an empty first stack.
     */
    @Test
    public void testPopEmptyFirstStack() {
        assertThrows(StackEmptyException.class, () -> {
            doubleStack.getFirstStack().pop(); // This should fail
        }, "Popping from an empty stack should throw StackEmptyException.");
    }

    /**
     * Tests pushing elements onto the second stack.
     */
    @Test
    public void testPushToSecondStack() throws StackOverflowException {
        // check if  second stack
        assertNotNull(doubleStack.getSecondStack(), "second stack should not be null");

        // push 5 elements
        for (int i = 0; i < 5; i++) {
            doubleStack.getSecondStack().push("Element " + i);
        }

        // check if the size updated
        assertEquals(5, doubleStack.getSecondStack().size(), "should be able to push elements to the second stack");
    }

    /**
     * Tests that pushing to a full second stack throws an exception.
     */
    @Test
    public void testPushFullSecondStack() throws StackOverflowException {
        // Push 5 elements to the second stack
        for (int i = 0; i < 5; i++) {
            doubleStack.getSecondStack().push("Element " + i); // Fill it up to capacity
        }

        // check the size or else errorrrrrrrr
        assertEquals(5, doubleStack.getSecondStack().size(), "should have 5 elements in the second stack");

        // now push one more element to the second stack to fill
        assertThrows(StackOverflowException.class, () -> {
            doubleStack.getSecondStack().push("Element 5"); // This should fail
        }, "should throw StackOverflowException when pushing to a full second stack");
    }

    /**
     * Tests popping elements from the second stack.
     */
    @Test
    public void testPopFromSecondStack() throws StackOverflowException, StackEmptyException {
        doubleStack.getSecondStack().push("Element 1");
        assertEquals("Element 1", doubleStack.getSecondStack().pop(), "Should pop the last pushed element from the second stack");
    }

    /**
     * Tests popping from an empty second stack.
     */
    @Test
    public void testPopEmptySecondStack() {
        assertThrows(StackEmptyException.class, () -> {
            doubleStack.getSecondStack().pop(); // This should fail
        }, "Popping from an empty second stack should throw StackEmptyException");
    }
}