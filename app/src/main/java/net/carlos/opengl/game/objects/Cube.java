package net.carlos.opengl.game.objects;

import net.carlos.opengl.game.objects.interfaces.Geometric;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteOrder;

public class Cube implements Geometric
{
	 private ByteBuffer colorBuffer;
	 private FloatBuffer verticesBuffer;
	 private ByteBuffer indexBuffer;

	 public Cube()
	 {
			byte maxColor = (byte) 255;


			float[] vertices = 
			{
				 -1.0f, -1.0f,
				 1.0f, -1.0f,
				 -1.0f,  1.0f,
				 1.0f,  1.0f
			};

			// Aqui, você pode mudar as cores do quadrado
			byte[] colors = 
			{
				 maxColor, maxColor, 0, maxColor,
				 0,        maxColor, 0, maxColor,
				 maxColor, maxColor, 0, maxColor,
				 0, 0,     maxColor,    maxColor
			};

			byte[] indices = 
			{
				 0, 3, 1,
				 0, 2, 3
			};

			ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
			bb.order(ByteOrder.nativeOrder());

			verticesBuffer = bb.asFloatBuffer();
			verticesBuffer.put(vertices);
			verticesBuffer.position(0);

			colorBuffer = ByteBuffer.allocateDirect(colors.length);
			colorBuffer.put(colors);
			colorBuffer.position(0);

			indexBuffer = ByteBuffer.allocateDirect(indices.length);
			indexBuffer.put(indices);
			indexBuffer.position(0);
	 }

	 @Override
	 public void draw(GL10 gl)
	 {
			gl.glFrontFace(GL10.GL_CW);
			gl.glVertexPointer(2, GL10.GL_FLOAT, 0, verticesBuffer);
			gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, colorBuffer);
			gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE,
			                  indexBuffer);
			gl.glFrontFace(GL10.GL_CCW);
	 }

}
