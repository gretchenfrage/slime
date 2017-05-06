package com.phoenixkahlo.slime.core;

import com.phoenixkahlo.slime.entities.FlappyEntity;
import com.phoenixkahlo.slime.entities.PhysicsBrickEntity;
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
        state.add(new FlappyEntity());
        state.add(new PhysicsBrickEntity());
        addState(state);
    }

}
