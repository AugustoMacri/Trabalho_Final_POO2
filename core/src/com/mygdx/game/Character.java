package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;

public class Character implements ScoreObservable{
    private Texture character, bulletTexture;
    private SpriteBatch batch;
    private float playerPositionX, playerPositionY;
    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;
    private Vector2 previousPosition;
    private int SPEED = 100; 
    private int LIFE = 6000;    
    private int SCORE = 0;
    ArrayList <ScoreObserver> observers;
    protected CharacterStrategy characterStrategy;
    protected float skillCooldown = 0f, runskill = 0f;
    private Sound teleportSound, healSound;


    public Character(Texture texture, float playerPositionX, float playerPositionY){
        batch = new SpriteBatch();
        this.character = texture;
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        rectangle = new Rectangle(playerPositionX + 32, playerPositionY + 32, 25, 60);
        observers = new ArrayList<>();
        teleportSound = Gdx.audio.newSound(Gdx.files.internal("audio/teleport.wav"));
        healSound = Gdx.audio.newSound(Gdx.files.internal("audio/heal.wav"));
    }

    public void move(){
        if(LIFE > 0){

                previousPosition = new Vector2(playerPositionX, playerPositionY);
                
                //Character movimentation
                //------------------------------------------------------------
                if(Gdx.input.isKeyPressed(Keys.W)) {
                    playerPositionY += SPEED * Gdx.graphics.getDeltaTime();
                    character = new Texture("images/character1.UP.png");
                } else if (Gdx.input.isKeyPressed(Keys.S)) {
                    playerPositionY -= SPEED * Gdx.graphics.getDeltaTime();
                    character = new Texture("images/character1.Down.png");
                }

                if(Gdx.input.isKeyPressed(Keys.D)) {
                    playerPositionX += SPEED * Gdx.graphics.getDeltaTime();
                    character = new Texture("images/character1.Right.png");
                } else if (Gdx.input.isKeyPressed(Keys.A)) {
                    playerPositionX -= SPEED * Gdx.graphics.getDeltaTime();
                    character = new Texture("images/character1.Left.png");
                }

                if(playerPositionX < 0){
                    playerPositionX = 0;
                } else if (playerPositionX > Gdx.graphics.getWidth() - 32){
                    playerPositionX = Gdx.graphics.getWidth() - 32;
                }
                if(playerPositionY < 0) {
                    playerPositionY = 0;
                } else if (playerPositionY > Gdx.graphics.getHeight() - 64) {
                    playerPositionY = Gdx.graphics.getHeight() - 64;
                }
                
                //Collision Rectangle position
                //------------------------------------------------------------
                rectangle.setPosition(playerPositionX, playerPositionY);

            }
    }

    public void setStrategy(CharacterStrategy characterStrategy){
        this.characterStrategy = characterStrategy;
    }

    public void executeStrategy(CharacterStrategy characterStrategy){
        characterStrategy.execute(this);
    }

    public void skills(){
        if(Gdx.input.isKeyPressed(Keys.Q) && LIFE > 0 && LIFE < 5000 && skillCooldown <= 0){
            setStrategy(new LifeStrategy());
            characterStrategy.execute(this);
            skillCooldown = 20.0f;
            healSound.play();
        }
        if(Gdx.input.isKeyPressed(Keys.E) && LIFE > 0 && skillCooldown <= 0){
            setStrategy(new TeleportStrategy());
            characterStrategy.execute(this);
            runskill = 0.04f;
            skillCooldown = 30.0f;
            teleportSound.play();
        }
        if (runskill > 0f) {
            runskill -= Gdx.graphics.getDeltaTime();

            if (runskill <= 0){
            SPEED = 100;
            }
        }
        if(skillCooldown > 0f){
            skillCooldown -= Gdx.graphics.getDeltaTime();
        }
        


    }

    public void update(){
        move();
    }


    public void render(){
        if(LIFE > 0)
        {
        batch.begin();
        batch.draw(character, playerPositionX - 20, playerPositionY, 64, 64);
        batch.end();
        }
    }

    

    @Override
    public void addObserver(ScoreObserver enemy){
        observers.add(enemy);
    }

    @Override
    public void removeObserver (ScoreObserver enemy){
        observers.remove(enemy);
    }

    @Override
    public void notifyObservers(){
        for(ScoreObserver observer : observers){
            observer.updateScore(SCORE);
        }
    }

    public void dispose(){
        character.dispose();
    }

    public Texture getCharacter() {
        return character;
    }

    public void setCharacter(Texture character) {
        this.character = character;
    }

    public float getPlayerPositionX() {
        return playerPositionX;
    }

    public void setPlayerPositionX(float playerPositionX) {
        this.playerPositionX = playerPositionX;
    }

    public float getPlayerPositionY() {
        return playerPositionY;
    }

    public void setPlayerPositionY(float playerPositionY) {
        this.playerPositionY = playerPositionY;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int sPEED) {
        SPEED = sPEED;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getLIFE() {
        return LIFE;
    }

    public void setLIFE(int lIFE) {
        LIFE = lIFE;
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public void setBulletTexture(Texture bulletTexture) {
        this.bulletTexture = bulletTexture;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }


    public Vector2 getPreviousPosition() {
        return previousPosition;
    }


    public void setPreviousPosition(Vector2 previousPosition) {
        this.previousPosition = previousPosition;
    }

    public void setX(float x) {
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public float getSkillCooldown() {
        return skillCooldown;
    }

    public void setSkillCooldown(float skillCooldown) {
        this.skillCooldown = skillCooldown;
    }

    public float getRunskill() {
        return runskill;
    }

    public void setRunskill(float runskill) {
        this.runskill = runskill;
    }

    
}
