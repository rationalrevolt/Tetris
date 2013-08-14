/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.state;

import com.sankar.tetris.Tetris;
import com.sankar.tetris.ui.TetrisGridRenderer;
import com.sankar.tetris.model.GameAction;
import com.sankar.tetris.model.BlockProvider;
import com.sankar.tetris.model.GameState;
import com.sankar.tetris.model.RandomBlockProvider;
import com.sankar.tetris.model.TetrisBlockType;
import com.sankar.tetris.model.TetrisGameEventListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayingState extends BasicGameState {
    private GameState gameState;
    private TetrisGridRenderer view;
    private KeyCommandProvider inputProvider;
    private TetrisGameEventListener gameEventListener;
    private BlockProvider blockProvider;
    
    private TetrisBlockType newBlock;
    
    private int score = 0;
    private boolean paused = false;
    private int timeElapsed = 0;
    
    @Override
    public int getID() {
        return Tetris.PLAYING_STATE;
    }
    
    private void init() {
        score = 0;
        timeElapsed = 0;
        paused = false;
        newBlock = blockProvider.next();
    }
    
    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
        inputProvider = new KeyCommandProvider(container.getInput());
        
        blockProvider = new RandomBlockProvider();
        
        gameEventListener = new TetrisGameEventListener() {
            @Override
            public void linesCleared(int count) {
                score += count * 100;
            }

            @Override
            public void needBlock() {
                newBlock = blockProvider.next();
            }

            @Override
            public void gameOver() {
                game.enterState(Tetris.GAME_OVER_STATE);
            }
        };
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        KeyCommand command = inputProvider.nextCommand();
        
        timeElapsed += delta;
        
        if (newBlock != null) {
            gameState.takeBlock(newBlock);
            newBlock = null;
        } else if (command == KeyCommand.Pause) {
            paused = !paused;
        } else if (!paused) switch(command) {
            case Left: 
                gameState.step(GameAction.MoveLeft);
                break;
            case Right: 
                gameState.step(GameAction.MoveRight);
                break;
            case Down:
                timeElapsed = 0;
                gameState.step(GameAction.MoveDown);
                break;
            case Rotate: 
                gameState.step(GameAction.Rotate);
                break;
            case None:
                if (timeElapsed > 500) {
                    timeElapsed = 0;
                    gameState.step(GameAction.MoveDown);
                }
                break;
        }
        
        command = KeyCommand.None;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        view.render(g);
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        init();
        inputProvider.setActive(true);
        gameState = new GameState(gameEventListener);
        view = new TetrisGridRenderer(gameState, container.getWidth(), container.getHeight());
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game) {
        inputProvider.setActive(false);
    }
    
    public int getScore() {
        return score;
    }
}