import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueueImplTest {

    private static final Queue<Integer> empty = QueueImpl.empty();

    @Test(expected = IllegalStateException.class)
    public void empty() {
        assertThat(empty.isEmpty(), is(true));
        assertThat(empty.head(), is(nullValue()));
        empty.deQueue();
    }

    @Test
    public void enQueue() {
        Queue<Integer> q1 = empty.enQueue(1);
        Queue<Integer> q2 = q1.enQueue(2);
        Queue<Integer> q3 = q2.enQueue(3);

        assertThat(q3.head(), is(1));
        Queue<Integer> q4 = q3.deQueue();
        assertThat(q4.head(), is(2));
        Queue<Integer> q5 = q4.deQueue();
        assertThat(q5.head(), is(3));
        Queue<Integer> q6 = q5.deQueue();
        assertThat(q6.isEmpty(), is(true));
    }
}
