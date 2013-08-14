/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris;

import com.sankar.tetris.state.TetrisGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author minerva
 */
public class Launcher {
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new TetrisGame());
        app.setDisplayMode(Tetris.WINDOW_WIDTH,Tetris.WINDOW_HEIGHT,false);
        app.start();
    }
}
