package common;

/**
 * This exception may be thrown when attempting to enqueue onto a full queue.
 *
 */
@SuppressWarnings("serial")
public class QueueFullException extends Exception {
    public QueueFullException() {
        super("Oh no! Queue is full!");
    }

    public QueueFullException(String message) {
        super(message);
    }
}
