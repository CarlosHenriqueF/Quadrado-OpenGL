package net.carlos.opengl;

import android.app.Activity;
import android.os.Bundle;
import net.carlos.opengl.game.GameView;

public class MainActivity extends Activity 
{
	 private GameView game;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
				
				this.game = new GameView(this);
				
        setContentView(game);
    }
}
