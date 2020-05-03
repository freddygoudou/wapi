package database;

public class DatabaseContent {

    public static final class DatabaseEntry {

        public static final String TABLE_CAROUSSEL = "CAROUSSEL";
        public static final String COLUMN_CAROUSSEL_ID = "id";
        public static final String COLUMN_CAROUSSEL_DESCRIPTION = "description";
        public static final String COLUMN_CAROUSSEL_FORMATION_NAME = "name";
        public static final String COLUMN_CAROUSSEL_AUDIOS_PATHS = "audiosPaths";
        public static final String COLUMN_CAROUSSEL_IMAGES_PATHS = "imagesPaths";


        public static final String TABLE_VIDEO = "VIDEO";
        public static final String COLUMN_VIDEO_ID = "id";
        public static final String COLUMN_VIDEO_PATH = "videoPath";
        public static final String COLUMN_VIDEO_FORMATION_NAME = "name";
        public static final String COLUMN_VIDEO_DESCRIPTION = "description";
        public static final String COLUMN_VIDEO_CAPTION_PATH = "captionPath";


    }
}
