package hu.ait.tiffanynguyen.classscheduler.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.ait.tiffanynguyen.classscheduler.data.DayItem;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DayContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DayItem> ITEMS = new ArrayList<DayItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DayItem> ITEM_MAP = new HashMap<String, DayItem>();

    static {
        addItem(new DayItem("1", "Monday"));
        addItem(new DayItem("2", "Tuesday"));
        addItem(new DayItem("3", "Wednesday"));
        addItem(new DayItem("4", "Thursday"));
        addItem(new DayItem("5", "Friday"));
        addItem(new DayItem("6", "Saturday"));
        addItem(new DayItem("7", "Sunday"));
    }

    private static void addItem(DayItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
