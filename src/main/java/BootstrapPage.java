import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BootstrapPage {
	public void writeBuffImg(BufferedImage bi, String filename) {
		try { 
			ImageIO.write(bi, "jpg", new File(filename));
		} catch (IOException io) { 
			System.out.println("Error saving file"); 
		}
	}
}
