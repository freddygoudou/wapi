package database;

public class DatabaseContent {

    public static final class DatabaseEntry {

        public static final String TABLE_CAROUSSEL = "CAROUSSEL";
        public static final String COLUMN_CAROUSSEL_ID = "id";
        public static final String COLUMN_CAROUSSEL_DESCRIPTION = "description";
        public static final String COLUMN_CAROUSSEL_NAME = "name";
        public static final String COLUMN_CAROUSSEL_SUBNAME = "subname";
        public static final String COLUMN_CAROUSSEL_URL = "url";
        public static final String COLUMN_CAROUSSEL_LANGUE = "langue";



        public static final String TABLE_VIDEO = "VIDEO";
        public static final String COLUMN_VIDEO_ID = "id";
        public static final String COLUMN_VIDEO_PATH = "videoPath";
        public static final String COLUMN_VIDEO_FORMATION_NAME = "name";
        public static final String COLUMN_VIDEO_DESCRIPTION = "description";
        public static final String COLUMN_VIDEO_CAPTION_PATH = "captionPath";


        public static final String TABLE_CARROUSEL_DOWNLOADED = "CARROUSEL_DOWNLOADED";
        public static final String COLUMN_CARROUSEL_DOWNLOADED_ID = "id";
        public static final String COLUMN_CARROUSEL_DOWNLOADED_NAME = "name";
        public static final String COLUMN_CARROUSEL_DOWNLOADED_SUBNAME = "subname";
        public static final String COLUMN_CARROUSEL_DOWNLOADED_JSONFILEURI = "jsonfileUri";
        public static final String COLUMN_CARROUSEL_DOWNLOADED_LANGUE = "langue";


        public static final String TABLE_CARROUSEL_FORMATION = "CARROUSEL_FORMATION";
        public static final String COLUMN_CARROUSEL_FORMATION_ID = "id";
        public static final String COLUMN_CCARROUSEL_FORMATION_TEXTE = "texte";
        public static final String COLUMN_CARROUSEL_FORMATION_IMAGES= "images";
        public static final String COLUMN_CARROUSEL_FORMATION_AUDIOS= "audios";
        public static final String COLUMN_CARROUSEL_ID_FK = "id_carrousel";


    }
}
