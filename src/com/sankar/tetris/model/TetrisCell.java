/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

/**
 *
 * @author minerva
 */
public class TetrisCell {
    private TetrisCellState state;
    private Color color;
    
    public TetrisCell() {
        reset();
    }
    
    public boolean isBlank() {
        return state == TetrisCellState.Blank;
    }

    public boolean isFrozen() {
        return state == TetrisCellState.Frozen;
    }

    public boolean isAlive() {
        return state == TetrisCellState.Alive;
    }
    
    public void setState(TetrisCellState state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public final void reset() {
        state = TetrisCellState.Blank;
        color = Color.Black;
    }
    
    public void becomeLike(TetrisCell other) {
        setState(other.state);
        setColor(other.color);
    }
}
