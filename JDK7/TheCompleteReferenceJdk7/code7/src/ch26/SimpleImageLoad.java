package ch26;
/*
 * <applet code="SimpleImageLoad" width=248 height=146>
 *  <param name="img" value="seattle.jpg">
 * </applet>
 */
import java.awt.*;
import java.applet.*;

public class SimpleImageLoad extends Applet
{
  Image img;

  public void init() {
    img = getImage(getDocumentBase(), getParameter("img"));
  }

  public void paint(Graphics g) {
    g.drawImage(img, 0, 0, this);
  }


	public boolean imageUpdate(Image img, int flags,
							   int x, int y, int w, int h) {
	  if ((flags & ALLBITS) == 0) {
		System.out.println("Still processing the image.");
		return true;
	  } else {
		System.out.println("Done processing the image.");
		return false;
	  }
	}

}