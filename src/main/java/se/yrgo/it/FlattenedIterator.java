package se.yrgo.it;

import java.util.*;

/**
 * Given a list of iterators this class acts as if all those iterators where a single iterator. I.e.
 * the iterators should be flattened out into one big iterator. The iterators should be iterated
 * through in the order of the list, from first to last.
 * 
 */
public class FlattenedIterator<T> implements Iterator<T> {

    private final Iterator<Iterator<T>> iterators;
    private Iterator<T> currentIterator;

    public FlattenedIterator(List<Iterator<T>> iterators) {
        this.iterators = List.copyOf(iterators).iterator();

        nextIterator();
    }

    /**
     * Returns true if any of the iterators has more elements. Returns false when the last iterator
     * returns false.
     * 
     */
    @Override
    public boolean hasNext() {
        while (currentIterator != null && !currentIterator.hasNext()) {
            nextIterator();
        }

        if (currentIterator == null) {
            return false;
        }

        return currentIterator.hasNext();
    }

    /**
     * Returns the next element from the list of iterators.
     * 
     */
    @Override
    public T next() {
        while (currentIterator != null && !currentIterator.hasNext()) {
            nextIterator();
        }

        if (currentIterator == null) {
            throw new NoSuchElementException();
        }

        return currentIterator.next();
    }

    private void nextIterator() {
        currentIterator = iterators.hasNext() ? iterators.next() : null;
    }
}
