package com.phoenixkahlo.slime.core;

import com.phoenixkahlo.slime.assets.ImageAsset;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Phoenix on 5/5/2017.
 */
public class MainMenuState extends BasicGameState {

    @Override
    public int getID() {
        return StaticGameState.MAIN_MENU.ordinal();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(ImageAsset.FLAPPY.image(), 200, 200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

}
