package common;

/**
 * This exception may be thrown when attempting to dequeue from an empty queue.
 *
 */
@SuppressWarnings("serial")
public class QueueEmptyException extends Exception {
    public QueueEmptyException() {
        super("Uh oh! Queue is empty!");
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
