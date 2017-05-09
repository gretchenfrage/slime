package com.phoenixkahlo.slime.entities;

/**
 * Created by kahlo on 5/9/2017.
 */
public enum FilterType {

    ORDINARY(0b0000000000000000, 0b1111111111111111),
    PARTICLE(0b0000000000000010, 0b1111111111111101),
    ;

    public final short categoryBits;
    public final short maskBits;

    FilterType(int categoryBits, int maskBits) {
        this.categoryBits = (short) categoryBits;
        this.maskBits = (short) maskBits;
    }


}
