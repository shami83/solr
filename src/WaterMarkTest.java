import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class WaterMarkTest {
	
	 public static void main(String[] args) {

         try {
                 File file = new File("C:/test/test.JPG");
                 if (!file.exists()) {
                         System.out.println("File not Found");
                 }
                 ImageIcon icon = new ImageIcon(file.getPath());
                 BufferedImage bufferedImage = new BufferedImage(
                                 icon.getIconWidth(), icon.getIconHeight(),
                                 BufferedImage.TYPE_INT_RGB);
                 Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
                 g2d.drawImage(icon.getImage(), 0, 0, null);
                 AlphaComposite alpha = AlphaComposite.getInstance(
                                 AlphaComposite.SRC_OVER, 0.5f);
                 g2d.setComposite(alpha);
                 g2d.setColor(Color.white);
                 g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                 g2d.setFont(new Font("Arial", Font.BOLD, 240));
                 String watermark = "by Shamik Mitra";
                 FontMetrics fontMetrics = g2d.getFontMetrics();
                 Rectangle2D rect = fontMetrics.getStringBounds(watermark, g2d);
                 g2d.drawString(watermark, (icon.getIconWidth() - (int) rect
                                 .getWidth()) / 2, (icon.getIconHeight() - (int) rect
                                 .getHeight()) / 2);
                 g2d.dispose();
                 File fileout = new File("C:/test/watermarkedImage.jpg");
                 ImageIO.write(bufferedImage, "jpg", fileout);
                 } catch (IOException ioe) {
         }
 }
}


