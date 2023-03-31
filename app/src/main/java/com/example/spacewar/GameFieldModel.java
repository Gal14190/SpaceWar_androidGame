package com.example.spacewar;

public class GameFieldModel {
    /**
     * Icons
     */

    // component drawable source
    static final int COMPONENT_SRC  = R.drawable.spaceship;
    // obstacle drawable source
    static final int OBSTACLE_SRC   = R.drawable.fireball;
    static final int COIN_SRC       = R.drawable.energy;
    // component view size
    static final int COMPONENT_SIZE = 300;
    // obstacle view size
    static final int OBSTACLE_SIZE  = 380;

    /**
     * Bonus
     */
    static final int COIN_BONUS = 10;
    static final int ROAD_BONUS = 1;

    /**
     * Field Size
     */

    // obstacles container columns size
    static final int COLUMN_SIZE = 5;
    // obstacles container rows size
    static final int ROW_SIZE   = 7;

    /**
     * Cycle thread info
     */

    // cycle thread review time
    static final int CYCLE_DELAY_SLOW = 400;
    static final int CYCLE_DELAY_FAST = 150;

    /**
     * Holder data
     */

    // component position holder
    static int componentPosition = 1;
    // lives counter holder
    static int lives = 3;

    // level
    static boolean mode = false;
    static int cycle_delay = CYCLE_DELAY_SLOW;

}
