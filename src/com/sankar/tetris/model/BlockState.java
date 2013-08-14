/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author minerva
 */
public class BlockState {
    private final TetrisBlockType blockType;
    private TetrisBlockType.TBlockDef blockDef;
    private final GameState gameState;
    
    private int xpos,ypos;
    
    public BlockState(TetrisBlockType btype, int xpos, int ypos, GameState gameState) {
        this.blockType = btype;
        this.blockDef = btype.getInitialBlockDef();
        this.gameState = gameState;
        
        this.xpos = xpos;
        this.ypos = ypos;
        
        updateGameState(ALIVE_ACTION);
    }
    
    public TetrisBlockType getBlockType() {
        return blockType;
    }
    
    public void moveLeft() {
        updateGameState(BLANK_ACTION);
        xpos -= 1;
        updateGameState(ALIVE_ACTION);
    }
    
    public void moveRight() {
        updateGameState(BLANK_ACTION);
        xpos += 1;
        updateGameState(ALIVE_ACTION);
    }
    
    public void moveDown() {
        updateGameState(BLANK_ACTION);
        ypos += 1;
        updateGameState(ALIVE_ACTION);
    }
    
    public void rotate() {
        updateGameState(BLANK_ACTION);
        blockDef = blockDef.next();
        updateGameState(ALIVE_ACTION);
    }
    
    public void freeze() {
        updateGameState(FREEZE_ACTION);
    }
    
    public boolean canMoveLeft() {
        TetrisBlockType.TBlockDef bd = blockDef;
        int xx = xpos, yy = ypos, size = bd.size();
        for(int x = 0; x < size; x++) {
            if (xx + x < 0) continue;
            for(int y = 0; y < size; y++) {
                if (yy + y < 0 || yy + y >= GameConstants.HEIGHT) continue;
                if (!bd.cell(x,y)) continue;
                int xxx = xx + x - 1;
                int yyy = yy + y;
                if (xxx < 0) return false;
                if (gameState.getCell(xxx, yyy).isFrozen()) return false;
            }
        }
        
        return true;
    }
    
    public boolean canMoveRight() {
        TetrisBlockType.TBlockDef bd = blockDef;
        int xx = xpos, yy = ypos, size = bd.size();
        for(int x = size - 1; x >= 0; x--) {
            if (xx + x >= GameConstants.WIDTH) continue;
            for(int y = 0; y < size; y++) {
                if (yy + y < 0 || yy + y >= GameConstants.HEIGHT) continue;
                if (!bd.cell(x,y)) continue;
                int xxx = xx + x + 1;
                int yyy = yy + y;
                if (xxx >= GameConstants.WIDTH) return false;
                if (gameState.getCell(xxx, yyy).isFrozen()) return false;
            }
        }
        
        return true;
    }
    
    public boolean canMoveDown() {
        TetrisBlockType.TBlockDef bd = blockDef;
        int xx = xpos, yy = ypos, size = bd.size();
        for(int y = size - 1; y >= 0; y--) {
            if(yy + y >= GameConstants.HEIGHT) continue;
            for(int x = 0; x < size; x++) {
                if (xx + x < 0 || xx + x >= GameConstants.WIDTH) continue;
                if (!bd.cell(x,y)) continue;
                int xxx = xx + x;
                int yyy = yy + y + 1;
                if (yyy >= GameConstants.HEIGHT) return false;
                if (gameState.getCell(xxx,yyy).isFrozen()) return false;
            }
        }
        
        return true;
    }
    
    public boolean canRotate() {
        TetrisBlockType.TBlockDef bd = blockDef.next();
        int xx = xpos, yy = ypos, size = bd.size();
        
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                if (bd.cell(x,y) && (xx + x < 0 || xx + x >= GameConstants.WIDTH || yy + y < 0 || yy + y >= GameConstants.HEIGHT))
                    return false;
                else if (bd.cell(x,y) && gameState.getCell(xx + x,yy + y).isFrozen())
                    return false;
                else 
                    continue;
            }
        }
        
        return true;
    }
    
    public List<Integer> occupiedRowNumbers() {
        List<Integer> rows = new ArrayList<Integer>();
        for(int y = 0; y < blockType.size(); y++) {
            for(int x = 0; x < blockType.size(); x++) {
                if (blockDef.cell(x,y)) {
                    rows.add(ypos + y);
                    break;
                }
            }
        }
        
        return Collections.unmodifiableList(rows);
    }
    
    private void updateGameState(CellAction action) {
        int size = blockType.size();
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
                if(blockDef.cell(x,y)) 
                    action.update(gameState.getCell(xpos + x, ypos + y));
    }
    
    private class CellAction {
        private TetrisCellState newState;
        
        public CellAction(TetrisCellState newState) {
            this.newState = newState;
        }
        void update(TetrisCell cell) {
            cell.setState(newState);
            if (newState == TetrisCellState.Blank) 
                cell.setColor(Color.Black);
            else
                cell.setColor(Color.forType(blockType));
        }
    }
    
    private final CellAction BLANK_ACTION = new CellAction(TetrisCellState.Blank);
    private final CellAction ALIVE_ACTION = new CellAction(TetrisCellState.Alive);
    private final CellAction FREEZE_ACTION = new CellAction(TetrisCellState.Frozen);
}
