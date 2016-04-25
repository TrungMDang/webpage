/**
 * 
 */
package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 * @author Trung Dang
 * @version November 4, 2104
 */
public class Line extends AbstractAction implements Tools {

    private Point myStartingPoint;
    private Point myEndingPoint;
    private List<Shape> myShapes;
    /**
     * @param thePanel
     */
    public Line() {
        super("Line");
        putValue(Action.SELECTED_KEY, true);
        myStartingPoint = null;
        myEndingPoint = null;
        myShapes = new ArrayList<>();
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public Point getStartingPoint() {
        return (Point) myStartingPoint.clone();
    }

    @Override
    public Point getEndingPoint() {
        return (Point) myEndingPoint.clone();
    }

    @Override
    public Shape getShape() {
        if (myStartingPoint != null) {
            return new Line2D.Double(myStartingPoint, myEndingPoint);
        } 
        return null;
    }

    @Override
    public void setStartingPoint(final Point thePoint) {
        if (thePoint == null) {
            myStartingPoint = null;
        } else {
            myStartingPoint = (Point) thePoint.clone();
        }

    }

    @Override
    public void setEndingPoint(final Point thePoint) {
        if (thePoint == null) {
            myEndingPoint = null;
        } else {
            myEndingPoint = (Point) thePoint.clone();
        }

    }

    @Override
    public List<Shape> getShapes() {
        final List<Shape> shapes = new ArrayList<>();
        for (final Shape shape : myShapes) {
            shapes.add(shape);
        }
        return shapes;
    }
    /** 
     * 
     */
    @Override
    public void paintTool(final Graphics2D theGraphics, 
                          final Color theColor, 
                          final Stroke theStroke) {
        theGraphics.setColor(theColor);
        theGraphics.setStroke(theStroke);
        
        theGraphics.draw(new Line2D.Double(myStartingPoint.x, myStartingPoint.y, 
                             myEndingPoint.x, myEndingPoint.y));
    }
}
