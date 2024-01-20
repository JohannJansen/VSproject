package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.kotc.KingOfTheCastle;
import java.awt.*;

public class ConnectionScreen implements Screen, InputProcessor, Input.TextInputListener {
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private KingOfTheCastle kingOfTheCastle;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private OrthographicCamera camera = new OrthographicCamera();

    public ConnectionScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().scale(1.0f);
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, kingOfTheCastle.getScreenWidth(), kingOfTheCastle.getScreenHeight());
        kingOfTheCastle.batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);



    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(250f/255f,150f/255f,50f/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        kingOfTheCastle.batch.begin();

        kingOfTheCastle.thiccFont.draw(kingOfTheCastle.batch,"Choose a Option to Continue...", kingOfTheCastle.getScreenWidth()/3.23f,850);
        font.draw(kingOfTheCastle.batch,"Join a Lobby", kingOfTheCastle.getScreenWidth()/2.5f,650);
        font.draw(kingOfTheCastle.batch,"Host a Lobby", kingOfTheCastle.getScreenWidth()/2.5f,450);
        kingOfTheCastle.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        if (isInBounds(worldCoordinates,"Join a Lobby", kingOfTheCastle.getScreenWidth()/2.5f,650,0,60)){
            handleJoinLobbyClick();
            //Todo: implement logic for hosting/connecting here
        }else if(isInBounds(worldCoordinates,"Host a Lobby", kingOfTheCastle.getScreenWidth()/2.5f,450,0,60)){
            handleHostLobbyCLick();
        }
        return true;
    }

    @Override
    public void input(String text) {

    }

    @Override
    public void canceled() {

    }

    private boolean isInBounds(Vector3 point, String text, float x, float y, float rotation, float additionalRadius) {
        // Setze den Text und erhalte die Abmessungen
        glyphLayout.setText(font, text);

        // Erweitere den Bereich um den Klickpunkt um additionalRadius
        float expandedX = x - additionalRadius;
        float expandedY = y - additionalRadius;
        float expandedWidth = glyphLayout.width + additionalRadius * 2;
        float expandedHeight = glyphLayout.height + additionalRadius * 2;

        // Überprüfe, ob der Punkt innerhalb des erweiterten Bereichs des Texts liegt
        return point.x >= expandedX && point.x <= expandedX + expandedWidth
                && point.y >= expandedY && point.y <= expandedY + expandedHeight;
    }

    private void handleJoinLobbyClick(){
        Gdx.app.log("ConnectionScreen","Join Lobby Pressed");
    }

    private void handleHostLobbyCLick(){
        Gdx.app.log("ConnectionScreen","Host Lobby Pressed");
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
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
}
