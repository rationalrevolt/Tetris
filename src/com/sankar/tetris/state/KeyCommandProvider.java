/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sankar.tetris.state;

import org.newdawn.slick.Input;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

/**
 *
 * @author minerva
 */
public class KeyCommandProvider extends InputProvider {
    private KeyCommand command = KeyCommand.None;
    
    public KeyCommandProvider(Input inp) {
        super(inp);
        bindCommand(new KeyControl(Input.KEY_LEFT), KeyCommand.Left);
        bindCommand(new KeyControl(Input.KEY_RIGHT), KeyCommand.Right);
        bindCommand(new KeyControl(Input.KEY_DOWN), KeyCommand.Down);
        bindCommand(new KeyControl(Input.KEY_UP), KeyCommand.Rotate);
        bindCommand(new KeyControl(Input.KEY_P), KeyCommand.Pause);
        addListener(new InputProviderListener() {
            @Override
            public void controlPressed(Command c) {
                if(command == KeyCommand.None) command = (KeyCommand)c;
            }
            
            @Override
            public void controlReleased(Command c) {}
        });
    }
    
    public KeyCommand nextCommand() {
        KeyCommand result = command;
        command = KeyCommand.None;
        return result;
    }
}
