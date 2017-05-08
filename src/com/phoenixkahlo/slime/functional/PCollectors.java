package com.phoenixkahlo.slime.functional;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by kahlo on 5/6/2017.
 */
public class PCollectors {

    public static Collector<Float, ?, float[]> toFloatArray() {
        return new Collector<Float, Collection<Float>, float[]>() {

            @Override
            public Supplier<Collection<Float>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<Collection<Float>, Float> accumulator() {
                return Collection::add;
            }

            @Override
            public BinaryOperator<Collection<Float>> combiner() {
                return ConcatCollection::new;
            }

            @Override
            public Function<Collection<Float>, float[]> finisher() {
                return collection -> {
                    float[] array = new float[collection.size()];
                    int i = 0;
                    for (float f : collection) {
                        array[i] = f;
                        i++;
                    }
                    return array;
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return new HashSet<>(0);
            }

        };
    }

}
