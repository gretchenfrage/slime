package com.phoenixkahlo.slime.functional;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Created by kahlo on 5/6/2017.
 */
public class ConcatIterator<E> implements Iterator<E> {

    private Queue<Iterator<E>> iters;

    public ConcatIterator(Iterator<E>... iters) {
        this.iters = new ArrayDeque<>(iters.length);
        for (Iterator<E> iterator : iters) {
            this.iters.add(iterator);
        }
    }

    @Override
    public boolean hasNext() {
        while (!iters.peek().hasNext())
            iters.remove();
        return iters.size() > 0;
    }

    @Override
    public E next() {
        if (hasNext())
            return iters.peek().next();
        else
            throw new NoSuchElementException();
    }
}
