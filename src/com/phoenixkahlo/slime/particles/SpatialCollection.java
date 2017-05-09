package com.phoenixkahlo.slime.particles;

import org.jbox2d.common.Vec2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by kahlo on 5/9/2017.
 */
public class SpatialCollection<E> extends AbstractCollection<E> {

    private SortedMap<Float, List<E>> xMap;
    private Function<E, Vec2> positionGetter;

    public SpatialCollection(Function<E, Vec2> positionGetter) {
        xMap = new TreeMap<>();
        this.positionGetter = positionGetter;
    }

    @Override
    public Iterator<E> iterator() {
        return xMap.values().stream().flatMap(Collection::stream).iterator();
    }

    @Override
    public int size() {
        return xMap.values().stream().mapToInt(Collection::size).sum();
    }

    @Override
    public boolean add(E item) {
        return addToBin(item, positionGetter.apply(item).x, xMap);
    }

    private static <A, B> boolean addToBin(B value, A key, SortedMap<A, List<B>> map) {
        List<B> bin = map.get(key);
        if (bin == null) {
            bin = new ArrayList<>();
            map.put(key, bin);
        }
        return bin.add(value);
    }

    @Override
    public boolean contains(Object obj) {
        Vec2 vec;
        try {
            vec = positionGetter.apply((E) obj);
        } catch (ClassCastException e) {
            return false;
        }
        List<E> bin = xMap.get(vec.x);
        return bin != null && bin.contains(obj);
    }



    @Override
    public boolean remove(Object obj) {
        Vec2 vec;
        try {
            vec = positionGetter.apply((E) obj);
        } catch (ClassCastException e) {
            return false;
        }
        List<E> bin = xMap.get(vec.x);
        if (bin == null)
            return false;
        try {
            return bin.remove(obj);
        } finally {
            if (bin.isEmpty())
                xMap.remove(vec.x);
        }
    }

    public Stream<E> neighbors(Vec2 point, float r) {
        // get the submap of values within the correct x range
        SortedMap<Float, List<E>> xRange = xMap.subMap(point.x - r, point.x + r);
        // build a bin map containing the contents of the prior submap but sorted by y value
        SortedMap<Float, List<E>> yMap = new TreeMap<>();
        xRange.values().stream().flatMap(Collection::stream).forEach(item -> {
            addToBin(item, positionGetter.apply(item).y, yMap);
        });
        // get the submap of values that are also within the correct y range
        SortedMap<Float, List<E>> yRange = yMap.subMap(point.y - r, point.y + r);
        // transform into a vector stream and return
        return yRange.values().stream().flatMap(Collection::stream);
    }

}
