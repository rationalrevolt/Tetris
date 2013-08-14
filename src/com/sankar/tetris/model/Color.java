/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

import java.util.EnumMap;

/**
 *
 * @author minerva
 */
public enum Color {
    Cyan, Blue, Orange, Yellow, Green, Purple, Red, Black;
    
    private static final EnumMap<TetrisBlockType,Color> colormap = new EnumMap(TetrisBlockType.class);
    
    static {
        colormap.put(TetrisBlockType.Line    , Color.Cyan);
        colormap.put(TetrisBlockType.LeftL   , Color.Blue);
        colormap.put(TetrisBlockType.RightL  , Color.Orange);
        colormap.put(TetrisBlockType.Square  , Color.Yellow);
        colormap.put(TetrisBlockType.RightZig, Color.Green);
        colormap.put(TetrisBlockType.LeftZig , Color.Red);
        colormap.put(TetrisBlockType.Tee     , Color.Purple);
    }
    
    public static Color forType(TetrisBlockType btype) {
        return colormap.get(btype);
    }
}
