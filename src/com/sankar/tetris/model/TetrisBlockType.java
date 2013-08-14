/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.model;

/**
 *
 * @author minerva
 */
public enum TetrisBlockType {
      Line(MakeBlock("    ",
                     "    ",
                     "####",
                     "    "),
/*----------------------------*/            
           MakeBlock("  # ",
                     "  # ",
                     "  # ",
                     "  # ")),
      
/******************************/                
      
     LeftL(MakeBlock("   ",
                     "###",
                     "  #"),
/*----------------------------*/            
           MakeBlock(" # ",
                     " # ",
                     "## "),
/*----------------------------*/            
           MakeBlock("#  ",
                     "###",
                     "   "),
/*----------------------------*/            
           MakeBlock(" ##",
                     " # ",
                     " # ")),
    
/******************************/    
    
    RightL(MakeBlock("   ",
                     "###",
                     "#  "),
/*----------------------------*/            
           MakeBlock("## ",
                     " # ",
                     " # "),
/*----------------------------*/            
           MakeBlock("  #",
                     "###",
                     "   "),
/*----------------------------*/            
           MakeBlock(" # ",
                     " # ",
                     " ##")),
     
/******************************/        
     
    Square(MakeBlock("##",
                     "##")),
    
/******************************/            
    
    RightZig(MakeBlock("   ",
                       " ##",
                       "## "),
/*----------------------------*/                        
            MakeBlock(" # ",
                      " ##",
                      "  #")),
    
/******************************/                
    
    LeftZig(MakeBlock("   ",
                      "## ",
                      " ##"),
/*----------------------------*/                                    
             MakeBlock("  #",
                       " ##",
                       " # ")),
    
/******************************/                    
    
    Tee(MakeBlock("   ",
                  "###",
                  " # "),
/*----------------------------*/                                                
        MakeBlock(" # ",
                  "## ",
                  " # "),
/*----------------------------*/                                                
        MakeBlock(" # ",
                  "###",
                  "   "),
/*----------------------------*/                                                
        MakeBlock(" # ",
                  " ##",
                  " # "));
    
/******************************/                    
    
    private static TBlockDef MakeBlock(String... lines) {
        int size = lines.length;
        TBlockDef bd = new TBlockDef();
        
        bd.size = size;
        bd.cells = new boolean[size][size];
        for(int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                bd.cells[x][y] = lines[y].charAt(x) == '#' ? true : false;
        
        return bd;
    }
    
    private TBlockDef[] blockDefs;
    private int size;
    
    private TetrisBlockType(TBlockDef... blockDefs) {
        this.size = blockDefs[0].size;
        this.blockDefs = blockDefs;
        
        int blockCount = blockDefs.length;
        
        for(int i = 0; i < blockCount - 1; i++) 
            this.blockDefs[i].next = this.blockDefs[i+1];
        
        this.blockDefs[blockCount - 1].next = this.blockDefs[0];
    }
    
    public TBlockDef getInitialBlockDef() {
        return blockDefs[0];
    }
    
    public int size() {
        return size;
    }
    
    public static class TBlockDef {
        private int size;
        private boolean[][] cells;
        private TBlockDef next;
        
        public int size() {
            return size;
        }
        
        public boolean cell(int x, int y) {
            return cells[x][y];
        }
        
        public TBlockDef next() {
            return next;
        }
    }
}
