package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;

public class DoubleStack implements IDoubleStack {
    private SharedStack stack1;
    private SharedStack stack2;

    public DoubleStack(int capacity) {
        // each stack should have its own array for storage
        stack1 = new SharedStack(new Object[capacity / 2]); // first half for stack1
        stack2 = new SharedStack(new Object[capacity / 2]); // second half for stack2
    }

    @Override
    public IStack getFirstStack() {
        return stack1;
    }

    @Override
    public IStack getSecondStack() {
        return stack2;
    }
}