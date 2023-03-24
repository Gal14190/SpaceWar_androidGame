package com.example.spacewar;

public class GameFieldModel {
    /**
     * Icons
     */

    // component drawable source
    static final int COMPONENT_SRC = R.drawable.spaceship;
    // obstacle drawable source
    static final int OBSTACLE_SRC = R.drawable.fireball;
    // component view size
    static final int COMPONENT_SIZE = 300;
    // obstacle view size
    static final int OBSTACLE_SIZE = 380;

    /**
     * Field Size
     */

    // obstacles container columns size
    static final int COLUMN_SIZE = 3;
    // obstacles container rows size
    static final int ROW_SIZE = 5;

    /**
     * Cycle thread info
     */

    // cycle thread review time
    static final int CYCLE_DELAY = 400;

    /**
     * Holder data
     */

    // component position holder
    static int componentPosition = 1;
    // lives counter holder
    static int lives = 3;
}
