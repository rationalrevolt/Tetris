/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.ui;

import com.sankar.tetris.model.GameState;
import com.sankar.tetris.model.TetrisCell;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import static com.sankar.tetris.model.Color.*;
import com.sankar.tetris.model.GameConstants;

/**
 *
 * @author minerva
 */
public class TetrisGridRenderer {
    private int width;
    private int height;
    private GameState state;
    
    private static final Map <com.sankar.tetris.model.Color,Color> COLORMAP = new HashMap<com.sankar.tetris.model.Color,Color>();
    
    static {
        COLORMAP.put(Black, Color.black);
        COLORMAP.put(Blue, Color.blue);
        COLORMAP.put(Cyan, Color.cyan);
        COLORMAP.put(Green, Color.green);
        COLORMAP.put(Orange, Color.orange);
        COLORMAP.put(Purple, Color.magenta);
        COLORMAP.put(Red, Color.red);
        COLORMAP.put(Yellow, Color.yellow);
    }
    
    public TetrisGridRenderer(GameState state, int width, int height) {
        this.state = state;
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics g) {
        final int w = width/GameConstants.WIDTH;
        final int h = height/GameConstants.HEIGHT;
        
        for(int x = 0, xpos = 0; x < GameConstants.WIDTH; x++, xpos+=w) {
            for(int y = 0, ypos = 0; y < GameConstants.HEIGHT; y++, ypos+=h) {
                TetrisCell cell = state.getCell(x,y);
                renderCell(g,xpos,ypos,w,h,cell.getColor());
            }
        }       
    }
    
    private void renderCell(Graphics g, int xpos, int ypos, int w, int h, com.sankar.tetris.model.Color color) {
        int w6 = (int)(0.6 * w);
        int h6 = (int)(0.6 * h);
        
        int dx = (w - w6) / 2;
        int dy = (h - h6) / 2;
        
        g.setColor(COLORMAP.get(color).darker());
        g.fillRect(xpos, ypos, w - 1, h - 1);
        g.setColor(COLORMAP.get(color).brighter());
        g.fillRect(xpos + dx, ypos + dy, w6, h6);
    }
}
