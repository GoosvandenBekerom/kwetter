package com.goosvandenbekerom.util;

public class RegexHelpers {
    public static final String HASHTAG = "(?<=#)(\\S+)";
    public static final String MENTION = "(?<=@)(\\S+)";
    public static final String URL = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
}
