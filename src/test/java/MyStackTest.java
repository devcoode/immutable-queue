import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MyStackTest {

    private static final MyStack<Integer> empty = MyStack.empty();

    @Test(expected = IllegalStateException.class)
    public void empty() {
        assertThat(empty.isEmpty(), is(true));
        assertThat(empty.peek(), is(nullValue()));
        empty.pop();
    }

    @Test
    public void add() {
        MyStack<Integer> sut = empty.push(1).push(2).push(3);

        assertThat(sut.peek(), is(3));
        sut = sut.pop();
        assertThat(sut.peek(), is(2));
        sut = sut.pop();
        assertThat(sut.peek(), is(1));
        sut = sut.pop();
        assertThat(sut.isEmpty(), is(true));
    }

    @Test
    public void flip() {
        MyStack<Integer> sut = empty.push(1).push(2).push(3).flip();

        assertThat(sut.peek(), is(1));
        sut = sut.pop();
        assertThat(sut.peek(), is(2));
        sut = sut.pop();
        assertThat(sut.peek(), is(3));
        sut = sut.pop();
        assertThat(sut.isEmpty(), is(true));
    }
}
