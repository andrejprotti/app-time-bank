package hackday.timecardman;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "datetime";
        public static final String COLUMN_NAME_TITLE = "date";
        public static final String COLUMN_NAME_SUBTITLE = "time";
    }
}
