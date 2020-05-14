package com.pavel_karpov.first.myapp1;

public class DbSceme {
    public static final class DbName{
        public static final String NAME = "tasks";
        public static final class Colls{
            public static final String TASK = "task";
            public static final String TASK_DASCRIPTION = "task_description";
            public static final String PERCENT = "percent";
            public static final String ISCOMPLETED = "is_completed";
            public static final String FLAGCOMPLETED = "flag_completed";
            public static final String UUID_PURPOSE = "uuid_purpose";
            public static final String UUID_TASK = "uuid_task";


        }
    }
    public static final class DbNoteName{
        public static final String NAME = "notes";
        public static final class Columns{
            public static final String NOTE = "note";
            public static final String DATE = "date";
            public static final String UUID = "uuid";
        }
    }
    public static final class DbPurposeName{
        public static final String NAME = "purposes";
        public static final class Columns{
            public static final String PURPOSE = "purpose";
            public static final String DATE = "date";
            public static final String RATING = "rating";
            public static final String CATEGORY = "category";
            public static final String UUID = "uuid";
            public static final String ISCOMPLETED = "iscompleted";
            public static final String FLAGCOMPLETED = "flagcompleted";
            public static final String PHOTO_PATH = "photo_path";
            public static final String PURPOSE_DESCRIPTION = "purpose_description";
        }
    }
}
