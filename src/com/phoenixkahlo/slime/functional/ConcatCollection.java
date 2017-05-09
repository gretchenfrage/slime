package com.phoenixkahlo.slime.functional;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 * View two collections as one big immutable collection.
 */
public class ConcatCollection<E> extends AbstractCollection<E> {

    private Collection<E> part1;
    private Collection<E> part2;

    public ConcatCollection(Collection<E> part1, Collection<E> part2) {
        this.part1 = part1;
        this.part2 = part2;
    }

    @Override
    public Iterator<E> iterator() {
        return new ConcatIterator<E>(part1.iterator(), part2.iterator());
    }

    @Override
    public int size() {
        return part1.size() + part2.size();
    }

}
