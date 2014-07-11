package test.java.util.concurrent.ReentrantLock;
public interface IBuffer {
    public void write();

    public void read() throws InterruptedException;
}
