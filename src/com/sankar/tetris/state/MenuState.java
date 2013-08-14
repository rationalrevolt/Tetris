/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.state;

import com.sankar.tetris.Tetris;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

/**
 *
 * @author minerva
 */
public class MenuState extends BasicGameState {
    private StateBasedGame game;
    
    private int width, height;
    private int yoff;

    @Override
    public int getID() {
        return Tetris.MENU_STATE;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.width = container.getWidth();
        this.height = container.getHeight();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        initTextSettings(g);
        print(g,"Welcome to Tetris");
        print(g,"[Enter]");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
    
    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) game.enterState(Tetris.PLAYING_STATE);
    }
    
    private void initTextSettings(Graphics g) {
        yoff = (int)(height/2 - g.getFont().getLineHeight() * 1.5);
        g.setColor(Color.yellow);
    }
    
    private void print(Graphics g, String msg) {
        FontUtils.drawCenter(g.getFont(), msg, 0, yoff, width, Color.yellow);
        yoff += g.getFont().getHeight(msg);
    }
}
