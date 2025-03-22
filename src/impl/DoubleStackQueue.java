package impl;

import common.StackOverflowException;
import interfaces.IQueue;
import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;

public class DoubleStackQueue implements IQueue {
    private DoubleStack doubleStack;
    private int maxSize; // store the maximum size of the queue

    public DoubleStackQueue(int capacity) {
        this.maxSize = capacity;
        doubleStack = new DoubleStack(capacity);
    }

    @Override
    public void enqueue(Object element) throws QueueFullException {
        // check if the queue is full--> defined maximum size
        if (size() >= maxSize) {
            throw new QueueFullException("Queue is full.");
        }
        try {
            doubleStack.getFirstStack().push(element);
        } catch (StackOverflowException e) {
            throw new QueueFullException("Queue is full");
        }
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty.");
        }

        //first stack --> second stack if empty
        if (doubleStack.getSecondStack().isEmpty()) {
            while (!doubleStack.getFirstStack().isEmpty()) {
                try {
                    doubleStack.getSecondStack().push(doubleStack.getFirstStack().pop());
                } catch (StackOverflowException | StackEmptyException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        try {
            return doubleStack.getSecondStack().pop();
        } catch (StackEmptyException e) {
            throw new QueueEmptyException("Queue is empty.");
        }
    }

    @Override
    public int size() {
        return doubleStack.getFirstStack().size() + doubleStack.getSecondStack().size();
    }

    @Override
    public boolean isEmpty() {
        return doubleStack.getFirstStack().isEmpty() && doubleStack.getSecondStack().isEmpty();
    }

    @Override
    public void clear() {
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
    }
}