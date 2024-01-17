package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.inputprocessors.BattleScreenInputProcessor;

import java.awt.*;


public class BattleScreen implements Screen, InputProcessor {

    private KingOfTheCastle kingOfTheCastle;
    Texture startButton;
    Texture startButtonUnactive;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private OrthographicCamera camera = new OrthographicCamera();
    public BattleScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        this.startButton = new Texture("startButton.png");
        this.startButtonUnactive = new Texture("startButtonUnactive.png");
        Gdx.input.setInputProcessor(new BattleScreenInputProcessor(kingOfTheCastle.gameControllerClient));
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, kingOfTheCastle.getScreenWidth(), kingOfTheCastle.getScreenHeight());
        kingOfTheCastle.batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1 ,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512) / 2, 905, 512, 30); // x, y, width, height
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512) / 2, 900, 512, 40);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512) / 2, 100, 512, 200);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512) / 2, 100, 256, 100);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512) / 2, 100, 256, 200);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512), 100, 256, 100);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((kingOfTheCastle.getScreenWidth() - 512), 100, 256, 200);
        shapeRenderer.end();

        kingOfTheCastle.batch.begin();
        font.draw(kingOfTheCastle.batch, "Attacke", (kingOfTheCastle.getScreenWidth() - 660), 150);
        font.draw(kingOfTheCastle.batch, "Defense", (kingOfTheCastle.getScreenWidth() - 512) + 100, 150);
        font.draw(kingOfTheCastle.batch, "Aufgeladene Attacke", (kingOfTheCastle.getScreenWidth() - 700), 250);
        font.draw(kingOfTheCastle.batch, "Fliehen", (kingOfTheCastle.getScreenWidth() - 512) +100, 250);
        kingOfTheCastle.batch.end();

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
        shapeRenderer.dispose();
        font.dispose();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Koordinaten von Bildschirm zu Welt umwandeln
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));

        // Überprüfe, welcher Text geklickt wurde
        if (isInBounds(worldCoordinates, "Attacke", (kingOfTheCastle.getScreenWidth() - 660), 150, 0, 10)) {
            handleAttackeClick();
        } else if (isInBounds(worldCoordinates, "Defense", (kingOfTheCastle.getScreenWidth() - 512) + 100, 150, 0, 10)) {
            handleDefenseClick();
        } else if (isInBounds(worldCoordinates, "Aufgeladene Attacke", (kingOfTheCastle.getScreenWidth() - 700), 250, 0, 10)) {
            handleAufgeladeneAttackeClick();
        } else if (isInBounds(worldCoordinates, "Fliehen", (kingOfTheCastle.getScreenWidth() - 512) + 100, 250, 0, 10)) {
            handleFliehenClick();
        }

        return true; // Eingabe wurde verarbeitet
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

    private void handleAttackeClick() {
        Gdx.app.log("BattleScreen", "Attacke geklickt!");
        // Füge hier deine Logik für den Attacke-Klick hinzu
    }

    private void handleDefenseClick() {
        Gdx.app.log("BattleScreen", "Defense geklickt!");
        // Füge hier deine Logik für den Defense-Klick hinzu
    }

    private void handleAufgeladeneAttackeClick() {
        Gdx.app.log("BattleScreen", "Aufgeladene Attacke geklickt!");
        // Füge hier deine Logik für den Aufgeladene Attacke-Klick hinzu
    }

    private void handleFliehenClick() {
        Gdx.app.log("BattleScreen", "Fliehen geklickt!");
        // Füge hier deine Logik für den Fliehen-Klick hinzu
    }

}
