package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.StackEmptyException;
import common.StackOverflowException;
import impl.SharedStack;

/**
 * tests shared stack implementation
 */
public class TestSharedStack {

    private static final int DEFAULT_MAX_SIZE = 10;
    private SharedStack stack;

    /**
     * sets up a new stack before each test
     */
    @BeforeEach
    public void setUp() {
        Object[] array = new Object[DEFAULT_MAX_SIZE]; // create an array of the default max size
        stack = new SharedStack(array); // create the first stack
    }

    /**
     * tests that we can push elements onto the stack
     */
    @Test
    public void testPush() throws StackOverflowException {
        stack.push("element 1");
        stack.push("element 2");
        assertEquals(2, stack.size(), "should be able to push elements onto the stack");
    }

    /**
     * tests that pushing to a full stack throws an exception
     */
    @Test
    public void testPushFullStack() throws StackOverflowException {
        for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
            stack.push("element " + i); // fill it up
        }
        assertThrows(StackOverflowException.class, () -> {
            stack.push("element 11"); // this should throw an exception
        }, "should throw StackOverflowException when trying to push to a full stack");
    }

    /**
     * tests popping elements from the stack
     */
    @Test
    public void testPop() throws StackOverflowException, StackEmptyException {
        stack.push("element 1");
        assertEquals("element 1", stack.pop(), "should pop the last pushed element");
    }

    /**
     * tests popping from an empty stack throws an exception
     */
    @Test
    public void testPopEmptyStack() {
        assertThrows(StackEmptyException.class, () -> {
            stack.pop(); // this should throw an exception
        }, "popping from an empty stack should throw StackEmptyException");
    }

    /**
     * tests accessing the top element
     */
    @Test
    public void testTop() throws StackOverflowException, StackEmptyException {
        stack.push("element 1");
        assertEquals("element 1", stack.top(), "should return the top element without removing it");
    }

    /**
     * tests that accessing the top of an empty stack throws an exception
     */
    @Test
    public void testTopEmptyStack() {
        assertThrows(StackEmptyException.class, () -> {
            stack.top(); // this should throw an exception
        }, "accessing top of an empty stack should throw StackEmptyException");
    }

    /**
     * tests if the stack is empty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty(), "stack should be empty initially");
    }

    /**
     * tests clearing the stack
     */
    @Test
    public void testClear() throws StackOverflowException {
        stack.push("element 1");
        stack.clear();
        assertTrue(stack.isEmpty(), "stack should be empty after clearing");
    }
}