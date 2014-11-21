package hu.ait.tiffanynguyen.classscheduler.data;

/**
 * A Day item representing a piece of content.
 */
public class DayItem {

    public enum DayType{

        MONDAY("0"),
        TUESDAY("1"),
        WEDNESDAY("2"),
        THURSDAY("3"),
        FRIDAY("4"),
        SATURDAY("5"),
        SUNDAY("6");

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

    public DayType id;
    private String day;

    public DayItem(DayType id, String day) {
        this.id = id;
        this.day = day;
    }

    public String getDay() {
        return day.toUpperCase();
    }

    @Override
    public String toString() {
        return day;
    }

    public DayType getId() {
        return id;
    }
}