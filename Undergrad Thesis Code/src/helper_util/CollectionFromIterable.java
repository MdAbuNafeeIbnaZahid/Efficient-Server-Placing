package helper_util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nafee on 11/30/17.
 */
public class CollectionFromIterable
{
    public static<E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public static<E> List<E> makeList(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
