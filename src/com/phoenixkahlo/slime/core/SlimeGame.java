package com.phoenixkahlo.slime.core;

import com.phoenixkahlo.slime.entities.BoxEntity;
import com.phoenixkahlo.slime.entities.WallEntity;
import com.phoenixkahlo.slime.worldloader.FluidTest1;
import com.phoenixkahlo.slime.worldloader.TestWorld1;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Phoenix on 5/2/2017.
 */
public class SlimeGame extends StateBasedGame {

    public static void main(String[] args) {
        try {
            SlimeGame game = new SlimeGame();
            AppGameContainer container = new AppGameContainer(game);
            container.setDisplayMode(1500, 900, false);
            container.setShowFPS(false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            System.err.println("Failed to initialize game");
            e.printStackTrace();
        }
    }

    public SlimeGame() {
        super(new String[]{
                "Slime",
                "It's Slime-Tastic",
                "Slime Time!",
                "Soon to be named!",
                "Are you ready for the slime!?",
                "Wow!",
                "Over -1 copies sold!"
        }[(int) (Math.random() * 7)]);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        WorldState state = new WorldState(0);
        new FluidTest1().load(state);
        addState(state);
    }

}
