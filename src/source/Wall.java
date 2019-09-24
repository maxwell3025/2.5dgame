package source;

import java.awt.image.BufferedImage;

import vectors.Line2D;

public class Wall {
Line2D shape;
BufferedImage tex;
	public Wall(Line2D l, BufferedImage texture) {
		shape=l;
		tex = texture;
	}

}
