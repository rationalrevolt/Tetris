/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

/**
 *
 * @author minerva
 */
public interface TetrisGameEventListener {
    void linesCleared(int count);
    void needBlock();
    void gameOver();
}
