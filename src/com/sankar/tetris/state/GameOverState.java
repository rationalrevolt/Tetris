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
public class GameOverState extends BasicGameState {
    private StateBasedGame game;
    
    private int width, height;
    
    private int yoff;
    private int score;
    
    @Override
    public int getID() {
        return Tetris.GAME_OVER_STATE;
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
        
        print(g,"Game Over!");
        print(g,String.format("Score [%d]", score));
        print(g,"[Enter]");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        score = ((PlayingState)game.getState(Tetris.PLAYING_STATE)).getScore();
    }
    
    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) game.enterState(Tetris.MENU_STATE);
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
