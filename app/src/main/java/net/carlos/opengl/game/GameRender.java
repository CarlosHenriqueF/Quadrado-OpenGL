/*

 *@author Carlos Henrique - twitter - @CarlosIdeScript
 *Follow me!

 */

package net.carlos.opengl.game;

import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLU;
import net.carlos.opengl.game.objects.interfaces.Geometric;
import net.carlos.opengl.game.objects.Cube;
import android.util.Log;

public class GameRender implements Renderer
{
	 private float mTransY;
	 private Cube cube;
	 private boolean takeScreenshot = true;
	 public static int WIDTH, HEIGHT;

	 public GameRender()
	 {
			this.cube = new Cube();
	 }

	 @Override
	 public void onSurfaceCreated(GL10 gl, EGLConfig conf)
	 {
      gl.glDisable(GL10.GL_DITHER);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

			gl.glClearColor(0, 0, 0, 0);

			gl.glEnable(GL10.GL_CULL_FACE);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glEnable(GL10.GL_DEPTH_TEST);
	 }

	 @Override
	 public void onSurfaceChanged(GL10 gl, int width, int height)
	 {
			this.WIDTH = width;
			this.HEIGHT = height;

			gl.glViewport(0, 0, width, height);

			float ratio = width / height;

			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	 }

	 @Override
	 public void onDrawFrame(GL10 gl)
	 {
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

			drawGeometric(cube, gl);

			if (takeScreenshot)
			{
				 try
				 {
						GameView.SaveImage(GameView.createBitmapFromGLSurface(0, 0, WIDTH, HEIGHT, gl));
						takeScreenshot = false;
				 }
				 catch (Exception e)
				 {
						Log.e("GameRender", e.toString());
				 }
			}

			//GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
	 }

	 private void drawGeometric(Geometric geometric, GL10 gl)
	 {
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f, (float)Math.sin(mTransY), -3.0f);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

			geometric.draw(gl);

			mTransY += 0.75f;
	 }
}
