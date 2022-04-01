package org.softwaredesign;

import java.util.List;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.painter.Painter;


public class RoutePainter implements Painter<JXMapViewer>
{
    private final List<GeoPosition> track;

    public RoutePainter(List<GeoPosition> track)
    {
        this.track = new ArrayList<>(track);
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h)
    {
        g = (Graphics2D) g.create();

        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(4));

        draw(g, map, null);

        g.setColor(Color.magenta);
        g.setStroke(new BasicStroke(2));

        draw(g, map, "Route");

        g.setColor(Color.green);
        g.setStroke(new BasicStroke(12));
        draw(g, map, "Start");

        g.setColor(Color.red);
        g.setStroke(new BasicStroke(10));
        draw(g, map, "End");

        g.dispose();
    }

    /**
     * Creates the map image, and puts track points on the image to make up the path.
     * @param g
     * Graphics2D object used as canvas
     * @param map
     * JXMapViewer object that is used to get the snapshot of the activity location
     * @param drawStage
     * String representing what type of drawing needs to be done
     */
    private void draw(Graphics2D g, JXMapViewer map, String drawStage)
    {
        if (!Objects.equals(drawStage, "Route")) {
            int index = (Objects.equals(drawStage, "Start")) ? 0 : track.size() - 1;

            Point2D point = map.getTileFactory().geoToPixel(track.get(index), map.getZoom());

            g.drawOval((int) point.getX(), (int) point.getY(), 10, 10);
            return;
        }

        int lastX = 0;
        int lastY = 0;

        boolean first = true;

        for (GeoPosition gp : track)
        {
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());

            if (first)
            {
                first = false;
            }
            else
            {
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }

            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }
    }
}
