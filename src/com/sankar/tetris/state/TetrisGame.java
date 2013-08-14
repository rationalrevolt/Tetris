/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.state;

import com.sankar.tetris.Tetris;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author minerva
 */
public class TetrisGame extends StateBasedGame {
    
    public TetrisGame() {
        super(Tetris.TITLE);
    }
    
    @Override
    public void initStatesList(final GameContainer container) throws SlickException {
        Input inp = container.getInput();
        inp.enableKeyRepeat();
        
        final Command QUIT = new BasicCommand("Quit");
        InputProvider keyp = new InputProvider(inp);
        keyp.bindCommand(new KeyControl(Input.KEY_ESCAPE), QUIT);
        keyp.addListener(new InputProviderListener() {
            @Override
            public void controlPressed(Command command) {
                container.exit();
            }
            @Override
            public void controlReleased(Command command) {}
        });
        
        container.setShowFPS(false);
        
        addState(new MenuState());
        addState(new PlayingState());
        addState(new GameOverState());
        
        enterState(Tetris.MENU_STATE);
    }
}
