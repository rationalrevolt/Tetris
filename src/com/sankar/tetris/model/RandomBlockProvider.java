/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

import java.util.Random;

/**
 *
 * @author minerva
 */
public class RandomBlockProvider implements BlockProvider {
    private final int count = TetrisBlockType.values().length;
    private Random rnd = new Random();
    
    @Override
    public TetrisBlockType next() {
        return TetrisBlockType.values()[rnd.nextInt(count)];
    }
}
