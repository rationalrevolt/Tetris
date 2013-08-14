/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.state;

import org.newdawn.slick.command.Command;

/**
 *
 * @author minerva
 */
public enum KeyCommand implements Command {
    Left,Right,Down,Rotate,Pause,None;
}
