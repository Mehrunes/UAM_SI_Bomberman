package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Tiles extends ApplicationAdapter implements InputProcessor {
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    Sprite sprite;
    
    
    TiledMap map = new TiledMap();
    MapLayers layers = map.getLayers();
    
    float bombermanSpeed = 50f;
    float speedX;
    float speedY;
    
    @Override public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
       

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        camera = new OrthographicCamera(700f, 700f * (h/w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
       

      TiledMapTileLayer layer1 = new TiledMapTileLayer(500, 600, 700, 800);
        Cell cell = new Cell();
 
        Texture t = new Texture("tileset.png");
        TextureRegion r = new TextureRegion(t, 0,0,100,100);
       
        cell.setTile(new StaticTiledMapTile(r));
       
        for(int i = 0; i < 1000; i++){
        	 layer1.setCell(i, i, cell);

        }
        
        layers.add(layer1);
        
        //TiledMapTileLayer x = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        
     //   camera = new OrthographicCamera();
        //camera.setToOrtho(false,0.7f*w,0.94f*h);
      //  camera.update();
        tiledMap = new TmxMapLoader().load("bomber.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        Gdx.input.setInputProcessor(this);

        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bomb.png"));
        sprite = new Sprite(texture);
    }
    
    @Override public void render () {
    	if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
    		if((int)speedX  % 60 != 0)
    	      speedX -= Gdx.graphics.getDeltaTime() * bombermanSpeed;
    	
    	}
    	   if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
    	      speedX += Gdx.graphics.getDeltaTime() * bombermanSpeed;
    	   if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
    	      speedY += Gdx.graphics.getDeltaTime() * bombermanSpeed;
    	   if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
    	      speedY -= Gdx.graphics.getDeltaTime() * bombermanSpeed;
    	
    	
    	  Gdx.gl.glClearColor(0, 0, 0, 0);
    	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	    camera.update();
    	    tiledMapRenderer.setView(camera);
    	    tiledMapRenderer.render();
    	    sb.setProjectionMatrix(camera.combined);
    	    sb.begin();
    	    sb.draw(sprite, (int)speedX, (int)speedY);
    	  //  sprite.draw(sb);
    	    sb.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
        Vector3 position = camera.unproject(clickCoordinates);
        sprite.setPosition(position.x, position.y);
        return true;
    }
    
    @Override public boolean keyDown(int keycode) {
    	if(keycode == Input.Keys.W){
    		sprite.translateX(0);
    		sprite.translateY(32);
    	}
    	if(keycode == Input.Keys.A){
    		sprite.translateX(-32);
    		sprite.translateY(0);
    	}
    	if(keycode == Input.Keys.D){
    		sprite.translateX(32);
    		sprite.translateY(0);
    	}
    	if(keycode == Input.Keys.S){
    		sprite.translateX(0);
    		sprite.translateY(-32);
    	}
        return true;
    }

    @Override public boolean keyUp(int keycode) {
       /* if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
            */
        return false;
    }

    @Override public boolean keyTyped(char character) {

        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override public boolean scrolled(int amount) {
        return false;
    }
}
 