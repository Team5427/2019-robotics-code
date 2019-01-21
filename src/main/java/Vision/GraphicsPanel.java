package Vision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.*;
import Vision.HalfTarget.TargetSide;

public class GraphicsPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    BufferedImage image;
    MatOfPoint[] contours;
    BufferedImage contourImage;
    GripPipeline pipeline = new GripPipeline();
    URL url;
    public static double focalLen; 
    public static double imageCenterX;
    public static double imageCenterY;

    public static final double FOV = 67f;
    public static final double WIDTH_PIX_RATIO = 0.17; //The ratio of the average half target width to the pixel distance between the two half targets
    public static final double HEIGHT_PIX_RATIO = 0.52; //The ratio of the average half target height to the pixel distance between the two half targets
    public static final double DISTANCE_TOLERANCE = 25;

    public Target t;

    public GraphicsPanel(int w, int h) {
        super();
        setSize(w, h);
        // try {
        //     this.image = ImageIO.read(new File("Images/VisionImages2019/RocketPanelStraightDark24in.jpg"));

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // imageToContours(image);
        // repaint();
        try{
        url = new URL("http://169.254.101.224/axis-cgi/jpg/image.cgi");
        }catch(Exception e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        while(true) {
            try{
            Thread.sleep(1000/20);
            image = ImageIO.read(url);
            imageToContours(image);

            focalLen = image.getWidth()/(2*Math.tan(Math.toRadians(FOV/2)));
            imageCenterX = image.getWidth()/2 - 0.5;
            imageCenterY = image.getHeight()/2 - 0.5;

            repaint();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final int LEFT_COLOR = -15340065;  //Purple
    public static final int RIGHT_COLOR = -15418960; //Cyan
    
    public static ArrayList<Target> targetsInFrame;

    public void imageToContours(BufferedImage image) {
        pipeline.process(bufferedImageToMat(image));
        ArrayList<MatOfPoint> pointsList = pipeline.filterContoursOutput();
        Object[] contours = pointsList.toArray();
        ArrayList<HalfTarget> halfTargetsInFrame = new ArrayList<>();
        targetsInFrame = new ArrayList<>();
        BufferedImage contour = new BufferedImage(320, 240, BufferedImage.TYPE_4BYTE_ABGR);
        for (Object currentContour : contours) {
            Point[] points = ((MatOfPoint) currentContour).toArray();
            HalfTarget currentHalfTarget = new HalfTarget(points);
            if(currentHalfTarget.height<(currentHalfTarget.width*2)||currentHalfTarget.height>(currentHalfTarget.width*5))
            {
                // System.out.println(currentHalfTarget.height/currentHalfTarget.width);
                // System.out.println("Invalid Halftarget!!!");
                break;

            }
            
            
            halfTargetsInFrame.add(currentHalfTarget);
            //All this loop does is draw the current points to the panel. No calculations
            for (Point p : points) {
                contour.setRGB((int) p.x, (int) p.y, (currentHalfTarget.side==TargetSide.Right)?RIGHT_COLOR:LEFT_COLOR);
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<HalfTarget> leftTargets = (ArrayList<HalfTarget>) halfTargetsInFrame.clone();

        for(int x = 0;x<leftTargets.size();x++) {
            if(leftTargets.get(x).side==TargetSide.Right) {
                leftTargets.remove(x);
                x--;
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<HalfTarget> rightTargets = (ArrayList<HalfTarget>) halfTargetsInFrame.clone();
        
        for(int x = 0;x<rightTargets.size();x++) {
            if(rightTargets.get(x).side==TargetSide.Left) {
                rightTargets.remove(x);
                x--;
            }
        }
        
        while(!(leftTargets.isEmpty()||rightTargets.isEmpty())) {
            
            
            HalfTarget leftmostLeftTarget = leftTargets.get(0);
            for(HalfTarget h : leftTargets) {
                if(h.center.x<leftmostLeftTarget.center.x&&h.side==TargetSide.Left)
                    leftmostLeftTarget = h;
            }
            HalfTarget leftmostRightTarget = rightTargets.get(0);
            while(leftmostRightTarget.center.x<leftmostLeftTarget.center.x) {
                rightTargets.remove(leftmostRightTarget);
                if(rightTargets.isEmpty())
                    return;
                leftmostRightTarget = rightTargets.get(0);
            }
            for(HalfTarget h : rightTargets) {
                if(h.center.x<leftmostRightTarget.center.x)
                    leftmostRightTarget = h;
            }
            
            t = new Target(leftmostLeftTarget, leftmostRightTarget, true);
            if(isValidTarget(t)>0)
                leftTargets.remove(leftmostLeftTarget);
            else if(isValidTarget(t)<0)
                rightTargets.remove(leftmostRightTarget);
            else
            {
                targetsInFrame.add(t);
                // System.out.println("\t\t" + targetOffset(t));
                leftTargets.remove(leftmostLeftTarget);
                rightTargets.remove(leftmostRightTarget);
            }
        }
        // this.contours = new MatOfPoint[contours.length];
        // this.contourImage = contour;
    }

    enum DirectionToTurn{Left, Right, None}
    public static final int tolerance = 10;

    public DirectionToTurn DirectionToTurn(Target t) {
        int centerOfScreenX = 160;
        if(t.center.x<centerOfScreenX-tolerance)
            return DirectionToTurn.Right;
        else if(t.center.x>centerOfScreenX+tolerance)
            return DirectionToTurn.Left;
        return DirectionToTurn.None;
    }
    public double targetOffset(Target t)
    {
        double offset = t.center.x - 160; //in pixels;

        return offset;
    }

    //returns -1 if too close, 1 if too far, 0 if valid target
    public int isValidTarget(Target t)
    {

        double idealTapeDist = ((t.getAvgHeight()/HEIGHT_PIX_RATIO)+(t.getAvgWidth()/WIDTH_PIX_RATIO))/2;
        if(Math.abs(idealTapeDist-t.getTapeDist())>DISTANCE_TOLERANCE)
            return 1;
        else if(Math.abs(idealTapeDist-t.getTapeDist())>DISTANCE_TOLERANCE)
            return -1;
        else
            return 0;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.drawLine(0, 120, 320, 120);
        g.drawLine(160, 0, 160, 240);
        // g.drawImage(contourImage, 0, 0, null);
        for(Target t:targetsInFrame)
        {
            HalfTarget left = t.left;
            HalfTarget right = t.right;
            for(Point p : left.points)
            {
                g.setColor(new Color(GraphicsPanel.LEFT_COLOR));
                g.drawLine((int)p.x, (int)p.y, (int)p.x, (int)p.y);
                g.setColor(Color.BLACK);
                if(left.topLeft.x == p.x && left.topLeft.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(left.topRight.x == p.x && left.topRight.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(left.bottomLeft.x == p.x && left.bottomLeft.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(left.bottomRight.x == p.x && left.bottomRight.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                
            }
            for(Point p : right.points)
            {
            g.setColor(new Color(GraphicsPanel.RIGHT_COLOR));
                g.drawLine((int)p.x, (int)p.y, (int)p.x, (int)p.y);
                g.setColor(Color.BLACK);
                if(right.topLeft.x == p.x && right.topLeft.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(right.topRight.x == p.x && right.topRight.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(right.bottomLeft.x == p.x && right.bottomLeft.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
                if(right.bottomRight.x == p.x && right.bottomRight.y == p.y)
                    g.fillOval((int)p.x-2, (int)p.y-2, 4, 4);
            }
            // System.out.println("DifferenceRatio: "+t.differenceRatio);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}