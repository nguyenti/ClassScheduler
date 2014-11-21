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
        addItem(new DayItem(DayItem.DayType.MONDAY, "Monday"));
        addItem(new DayItem(DayItem.DayType.TUESDAY, "Tuesday"));
        addItem(new DayItem(DayItem.DayType.WEDNESDAY, "Wednesday"));
        addItem(new DayItem(DayItem.DayType.THURSDAY, "Thursday"));
        addItem(new DayItem(DayItem.DayType.FRIDAY, "Friday"));
        addItem(new DayItem(DayItem.DayType.SATURDAY, "Saturday"));
        addItem(new DayItem(DayItem.DayType.SUNDAY, "Sunday"));
    }

    private static void addItem(DayItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id.getValue(), item);
    }
}
