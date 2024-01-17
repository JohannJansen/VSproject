package com.mygdx.kotc.inputprocessors;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.kotc.gamecontroller.GameControllerClient;
import com.mygdx.kotc.inputprocessors.inputevents.ButtonPressEvent;

public class BattleScreenInputProcessor implements InputProcessor {
    private GameControllerClient gameControllerClient;

    public BattleScreenInputProcessor(GameControllerClient gameControllerClient) {
        this.gameControllerClient = gameControllerClient;
    }

    @Override
    public boolean keyDown(int keycode) {
        gameControllerClient.sendInputEvent(new ButtonPressEvent(keycode));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
