package utils;

import model.SortOption;

import java.util.Map;

public class SortOptionMapper {

    private static final Map<String, SortOption> SORT_MAP = Map.of(
            "price ascending", SortOption.PRICE_ASC,
            "price descending", SortOption.PRICE_DESC,
            "newest", SortOption.NEWEST,
            "oldest", SortOption.OLDEST,
            "upcoming", SortOption.UPCOMING,
            "relevance", SortOption.RELEVANCE
    );

    public static SortOption fromText(String text) {
        SortOption option = SORT_MAP.get(text.toLowerCase().trim());
        if (option == null) {
            throw new IllegalArgumentException("Unknown sort option: " + text);
        }
        return option;
    }
}
