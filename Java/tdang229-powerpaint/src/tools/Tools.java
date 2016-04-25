/**
 * 
 */
package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.List;

/**
 * @author Trung
 *
 */
public interface Tools {
    
  
    Point getStartingPoint();
    void setStartingPoint(final Point thePoint);
    Point getEndingPoint();
    void setEndingPoint(final Point thePoint);
    Shape getShape();
    /**
     * @return A list
     */
    List<Shape> getShapes();
    void paintTool(Graphics2D g2d, Color theColor, Stroke theStroke);
}
