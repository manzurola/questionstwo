package com.prodigy.diff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ItemToCharMapper<T> {

    private static final int MAX_KEY_VALUE = Character.MAX_VALUE;
    private char nextChar = 0;
    private final Map<Integer, Character> charByItemHashcode = new HashMap<>();
    private final HashingStrategy<T> itemHashingStrategy;

    ItemToCharMapper(HashingStrategy<T> itemHashingStrategy) {
        this.itemHashingStrategy = itemHashingStrategy;
    }

    ItemToCharMapper() {
        this.itemHashingStrategy = Object::hashCode;
    }

    List<Character> mapList(List<T> items) {
        return items.stream()
                .map(this::mapItem)
                .collect(Collectors.toList());
    }

    char mapItem(T item) {
        if (maxMappingsReached()) { // TODO change error msg, reveals internal implementation
            throw new RuntimeException(String.format("The maximal number of unique objects to map (%d) was reached", MAX_KEY_VALUE));
        }
        int hashCode = itemHashingStrategy.hashCode(item);
        if (!charByItemHashcode.containsKey(hashCode)) {
            charByItemHashcode.put(hashCode, nextChar);
            nextChar++;
        }
        return charByItemHashcode.get(hashCode);
    }

    private boolean maxMappingsReached() {
        return nextChar == MAX_KEY_VALUE;
    }

}
