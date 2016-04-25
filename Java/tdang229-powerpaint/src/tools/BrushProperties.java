/**
 * 
 */
package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

/**
 * @author Trung Dang
 *
 */
public class BrushProperties {
    
    /***/
    private static final float DEFAULT_THICKNESS = 5f;
    /***/
    private Stroke myStroke;
    
    /***/
    private Shape myShape;
    
    /***/
    private Color myColor;

    /***/
    private Tools myTool;
    /**
     * @param theShape
     * @param theColor
     * @param theStroke
     */
    public BrushProperties() {
        super();
        myShape = new Line2D.Double();
        myColor = Color.BLACK;
        myStroke = new BasicStroke(DEFAULT_THICKNESS);
    }
    /**
     * 
     */
    public BrushProperties(final Tools theTool, 
                           final Color theColor, 
                           final Stroke theStroke) {
        super();
        myShape = theTool.getShape();
        myColor = theColor;
        myStroke = theStroke;
        myTool = theTool;
    }
    public void setStroke(final int theWidth) {
        myStroke = new BasicStroke(theWidth);
    }
    public Color getColor() {
        return myColor;
    }
    public Stroke getStroke() {
        return myStroke;
    }
    public Shape getShape() {
        return myShape;
    }
    public void setShape(final Shape theShape) {
        myShape = theShape;
    }
    public Tools getTool() {
        return myTool;
    }
}
