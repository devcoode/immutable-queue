public class QueueImpl<T> implements Queue<T> {

    private static final Queue<?> EMPTY = new QueueImpl<>();

    private final MyStack<T> in;
    private final MyStack<T> out;

    private QueueImpl() {
        this(MyStack.empty(), MyStack.empty());
    }

    private QueueImpl(MyStack<T> in, MyStack<T> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public Queue<T> enQueue(T t) {
        return maintainStacks(in.push(t), out);
    }

    @Override
    public Queue<T> deQueue() {
        return maintainStacks(in, out.pop());
    }

    @Override
    public T head() {
        return out.peek();
    }

    @Override
    public boolean isEmpty() {
        return out.isEmpty();
    }

    private static <T> Queue<T> maintainStacks(MyStack<T> in, MyStack<T> out) {
        return out.isEmpty()
                ? new QueueImpl<>(MyStack.empty(), in.flip())
                : new QueueImpl<>(in, out);
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public static <T> Queue<T> empty() {
        return (Queue<T>) EMPTY;
    }
}

class MyStack<T> {

    private static final MyStack<?> EMPTY = new MyStack<>();

    private final Node<T> head;

    private MyStack() {
        this(null);
    }

    private MyStack(Node<T> head) {
        this.head = head;
    }

    MyStack<T> push(T t) {
        return new MyStack<>(new Node<>(t, head));
    }

    MyStack<T> pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return new MyStack<>(head.next);
    }

    T peek() {
        return isEmpty() ? null : head.element;
    }

    boolean isEmpty() {
        return head == null;
    }

    MyStack<T> flip() {
        MyStack<T> flipped = empty();

        for (MyStack<T> current = this; !current.isEmpty(); current = current.pop()) {
            flipped = flipped.push(current.peek());
        }

        return flipped;
    }

    @SuppressWarnings("unchecked")
    static <T> MyStack<T> empty() {
        return (MyStack<T>) EMPTY;
    }

    private static class Node<T> {

        final T element;
        final Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
