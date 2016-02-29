package net.carlos.opengl;

import android.app.Activity;
import android.os.Bundle;
import net.carlos.opengl.game.GameView;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
