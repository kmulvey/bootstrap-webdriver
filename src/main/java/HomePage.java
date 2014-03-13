import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Util.ImageCompare;

public class HomePage {
	private final WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	public boolean getShot() {
		InputStream in = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

		try {

			// read image file
			BufferedImage bufferedImage = ImageIO.read(in);

			// create a blank, RGB, same width and height, and a white background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			ImageCompare ic = new ImageCompare(newBufferedImage, newBufferedImage);
			// Set the comparison parameters. 
			//   (num vertical regions, num horizontal regions, sensitivity, stabilizer)
			ic.setParameters(8, 6, 5, 10);
			// Display some indication of the differences in the image.
			ic.setDebugMode(2);
			// Compare.
			ic.compare();
			// Display if these images are considered a match according to our parameters.
			System.out.println("Match: " + ic.match());
			// If its not a match then write a file to show changed regions.
//			if (!ic.match()) {
//				saveJPG(ic.getChangeIndicator(), "c:\\changes.jpg");
//			}
		} catch (IOException e) {

			e.printStackTrace();

		}

		return true;
	}
}
