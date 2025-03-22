package impl;

import interfaces.IStack;
import common.StackEmptyException;
import common.StackOverflowException;

public class SharedStack implements IStack {
    private Object[] elements; // array to hold stack elements
    private int top; // index of the current top element
    private int capacity; // maxi cap of the stack

    public SharedStack(Object[] array) {
        this.elements = array;
        this.capacity = array.length;
        this.top = -1; //  top for an empty stack
    }

    @Override
    public void push(Object element) throws StackOverflowException {
        if (size() >= capacity) {
            throw new StackOverflowException("Stack is full.");
        }
        elements[++top] = element; // increment top + add  element
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty.");
        }
        return elements[top--]; // return top element + decrement top
    }

    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty.");
        }
        return elements[top]; // return the top element without removing it
    }

    @Override
    public int size() {
        return top + 1; // Size is top index + 1
    }

    @Override
    public boolean isEmpty() {
        return top == -1; // Check if top is -1
    }

    @Override
    public void clear() {
        top = -1; // Reset top to -1 to clear the stack
    }
}