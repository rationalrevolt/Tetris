/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minerva
 */
public class GameState {
    private final TetrisCell[][] cells = new TetrisCell[GameConstants.WIDTH][GameConstants.HEIGHT];
    private BlockState activeBlock;
    private TetrisGameEventListener listener;
    
    public GameState(TetrisGameEventListener listener) {
        this.listener = listener;
        
        for(int x = 0; x < GameConstants.WIDTH; x++)
            for(int y = 0; y < GameConstants.HEIGHT; y++)
                cells[x][y] = new TetrisCell();
    }
    
    public void takeBlock(TetrisBlockType btype) {
        BlockState bs = new BlockState(btype,4,0,this);
        if (bs.canMoveDown())
            activeBlock = bs;
        else
            listener.gameOver();
    }
    
    public void step(GameAction action) {
        switch (action) {
            case MoveLeft:
                if (activeBlock.canMoveLeft()) activeBlock.moveLeft();
                break;
            case MoveRight:
                if (activeBlock.canMoveRight()) activeBlock.moveRight();
                break;
            case MoveDown:
                if (activeBlock.canMoveDown()) {
                    activeBlock.moveDown();
                } else {
                    activeBlock.freeze();
                    checkAndClearRows();
                    listener.needBlock();
                }   
                break;
            case Rotate:
                if (activeBlock.canRotate()) activeBlock.rotate();
                break;
        }
    }
    
    private void checkAndClearRows() {
        List<Integer> rows = activeBlock.occupiedRowNumbers();
        List<Integer> rowsToClear = new ArrayList<Integer>();
        
        for(int y : rows)
            if (isRowFull(y))
                rowsToClear.add(y);
        
        if (rowsToClear.size() > 0) {
            listener.linesCleared(rowsToClear.size());           
            
            for(int y : rowsToClear) {
                clearOneRow(y);
            }
        }
    }
    
    private void clearOneRow(int y) {
        for(int yy = y - 1; yy >= 0 && !isRowEmpty(yy); yy--) {
            for(int x = 0; x < GameConstants.WIDTH; x++) {
                cells[x][yy+1].becomeLike(cells[x][yy]);
                cells[x][yy].reset();
            }
        }
    }
    
    private boolean isRowEmpty(int y) {
        for(int x = 0; x < GameConstants.WIDTH; x++)
            if (cells[x][y].isAlive() || cells[x][y].isFrozen()) return false;
        
        return true;
    }
    
    private boolean isRowFull(int y) {
        for(int x = 0; x < GameConstants.WIDTH; x++)
            if (cells[x][y].isBlank()) return false;
        
        return true;
    }
    
    public TetrisCell getCell(int x, int y) {
        return cells[x][y];
    }
    
    public void reset() {
        for(int y = 0; y < GameConstants.HEIGHT; y++)
            for(int x = 0; x < GameConstants.WIDTH; x++)
                cells[x][y].reset();
        
        activeBlock = null;
    }
}
