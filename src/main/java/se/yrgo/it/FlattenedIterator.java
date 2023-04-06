package se.yrgo.it;

import java.util.*;

/**
 * Given a list of iterators this class acts as if all those iterators
 * where a single iterator. I.e. the iterators should be flattened out 
 * into one big iterator. The iterators should be iterated through
 * in the order of the list, from first to last.
 * 
 */
public class FlattenedIterator<T> implements Iterator<T> {

    public FlattenedIterator(List<Iterator<T>> iterators) {
        throw new UnsupportedOperationException("Unimplemented constructor");
    }

    /**
     * Returns true if any of the iterators has more elements. Returns
     * false when the last iterator returns false.
     * 
     */
    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    /**
     * Returns the next element from the list of iterators.
     * 
     */
    @Override
    public T next() {
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }    
}
