package com.yan.game.utils;

public class SqlConstants {
    public static final String SELECT_USER_BY_ID_WITH_RESULTS =
            "select u.id as user_id, u.username, r.id as result_id, r.level_id, r.points " +
                    "from user as u join result as r on u.id = r.user_id " +
                    "where u.id = :userId";
    public static final String SELECT_LEVEL_BY_ID_WITH_RESULTS =
            "select l.id as level_id, l.levelname, r.id as result_id, r.user_id, r.points " +
                    "from level as l join result as r on l.id = r.level_id " +
                    "where l.id = :levelId";
    public static final String INSERT_RESULT = "insert into result(user_id, level_id, points) " +
            "values(:userId, :levelId, :points)";
}
