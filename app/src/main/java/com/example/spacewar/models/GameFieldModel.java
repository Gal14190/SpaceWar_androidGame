package com.example.spacewar.models;

import com.example.spacewar.R;

public class GameFieldModel {
    /**
     * Icons
     */

    // component drawable source
    public static final int COMPONENT_SRC  = R.drawable.spaceship;
    // obstacle drawable source
    public static final int OBSTACLE_SRC   = R.drawable.fireball;
    public static final int COIN_SRC       = R.drawable.energy;
    // component view size
    public static final int COMPONENT_SIZE = 300;
    // obstacle view size
    public static final int OBSTACLE_SIZE  = 380;

    /**
     * Bonus
     */
    public static final int COIN_BONUS = 10;
    public static final int ROAD_BONUS = 1;

    /**
     * Field Size
     */

    // obstacles container columns size
    public static final int COLUMN_SIZE = 5;
    // obstacles container rows size
    public static final int ROW_SIZE   = 7;

    /**
     * Cycle thread info
     */

    // cycle thread review time
    public static final int CYCLE_DELAY_SLOW = 400;
    public static final int CYCLE_DELAY_FAST = 150;

    /**
     * Holder data
     */

    // component position holder
    public static int componentPosition = 1;
    // lives counter holder
    public static int lives = 3;

    // level
    public static boolean mode = false;
    public static enum eMode {ARROWS, SENSORS}
    public static int cycle_delay = CYCLE_DELAY_SLOW;

}
