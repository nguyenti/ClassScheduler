package hu.ait.tiffanynguyen.classscheduler.data;

/**
 * A Day item representing a piece of content.
 */
public class DayItem {

    public enum DayType{

        MONDAY("1"),
        TUESDAY("2"),
        WEDNESDAY("3"),
        THURSDAY("4"),
        FRIDAY("5"),
        SATURDAY("6"),
        SUNDAY("7");

        private String value;

        private DayType(String value) {
            this.value = value;
        }

        public static DayType fromInt(int value) {
            for (DayType p : DayType.values()) {
                if (Integer.parseInt(p.value) == value) {
                    return p;
                }
            }
            return MONDAY;
        }

        public String getValue() {
            return value;
        }
    }

    public String id;
    private String day;

    public DayItem(String id, String day) {
        this.id = id;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return day;
    }

    public String getId() {
        return id;
    }
}