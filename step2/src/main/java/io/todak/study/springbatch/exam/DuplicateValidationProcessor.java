package io.todak.study.springbatch.exam;

import org.springframework.batch.item.ItemProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DuplicateValidationProcessor<T> implements ItemProcessor<T, T> {

    private final Map<String, Object> pool = new ConcurrentHashMap<>();
    private final Function<T, String> keyExtractor;
    private final boolean allowDuplicate;

    public DuplicateValidationProcessor(Function<T, String> keyExtractor, boolean allowDuplicate) {
        this.keyExtractor = keyExtractor;
        this.allowDuplicate = allowDuplicate;
    }


    @Override
    public T process(T item) throws Exception {
        if (allowDuplicate) {
            return item;
        }
        String key = keyExtractor.apply(item);
        if (pool.containsKey(key)) return null;
        pool.put(key, key);
        return item;
    }
}
