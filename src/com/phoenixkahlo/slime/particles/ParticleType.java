package com.phoenixkahlo.slime.particles;

/**
 * Created by kahlo on 5/9/2017.
 */
public enum ParticleType {

    SOAPY_WATER(0b00000000),
    ;

    public final byte bits;

    ParticleType(int bits) {
        this.bits = (byte) bits;
    }

    public static short compound(ParticleType type1, ParticleType type2) {
        ParticleType lesserType;
        ParticleType greaterType;
        if (type1.ordinal() > type2.ordinal()) {
            greaterType = type1;
            lesserType = type2;
        } else {
            lesserType = type1;
            greaterType = type2;
        }
        return (short) (((short) lesserType.bits) | (((short) greaterType.bits) << 8));
    }

}
