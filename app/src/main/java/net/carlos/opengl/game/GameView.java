package net.carlos.opengl.game;

import android.opengl.GLSurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.util.Random;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLException;

public class GameView extends GLSurfaceView
{
	 public static Context GAME_CONTEXT;

	 public GameView(Context ctx)
	 {
			super(ctx);
			this.GAME_CONTEXT = ctx;
			setRenderer(new GameRender());
	 }

	 public static void SaveImage(Bitmap finalBitmap)
	 {

			String root = Environment.getExternalStorageDirectory().toString();
			File myDir = new File(root);
			Random generator = new Random();
			int n = 10000;
			n = generator.nextInt(n);
			String fname = "Image-" + n + ".jpg";
			File file = new File(myDir, fname);

			if (file.exists()) 
				 file.delete(); 

			try
			{
				 FileOutputStream out = new FileOutputStream(file);
				 finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				 out.flush();
				 out.close();

			}
			catch (Exception e)
			{
				 e.printStackTrace();
			}
	 }

	 public static Bitmap createBitmapFromGLSurface(int x, int y, int w, int h, GL10 gl)
	 throws OutOfMemoryError
	 {
			int bitmapBuffer[] = new int[w * h];
			int bitmapSource[] = new int[w * h];
			IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
			intBuffer.position(0);

			try
			{
				 gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer);
				 int offset1, offset2;
				 for (int i = 0; i < h; i++)
				 {
            offset1 = i * w;
            offset2 = (h - i - 1) * w;
            for (int j = 0; j < w; j++)
						{
							 int texturePixel = bitmapBuffer[offset1 + j];
							 int blue = (texturePixel >> 16) & 0xff;
							 int red = (texturePixel << 16) & 0x00ff0000;
							 int pixel = (texturePixel & 0xff00ff00) | red | blue;
							 bitmapSource[offset2 + j] = pixel;
            }
				 }
			}
			catch (GLException e)
			{
				 return null;
			}

			return Bitmap.createBitmap(bitmapSource, w, h, Bitmap.Config.ARGB_8888);
	 }
}
