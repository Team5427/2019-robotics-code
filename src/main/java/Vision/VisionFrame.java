package Vision;

import java.awt.*;

import javax.swing.*;

public class VisionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final String TITLE = "2019 Talon Vision";

	/*
	 * Change this if we're using camera or images - Image w:h - 720:502 - Multiply
	 * the width and height by the resize
	 */

	public static final int width = 320;
	public static final int height = 240;

	public GraphicsPanel graphicsPanel;

	public VisionFrame() {
		super(TITLE);

		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Insets insect = getInsets();
		int fWidth = insect.left + insect.right + width;
		int fHeight = insect.top + insect.bottom + height;

		graphicsPanel = new GraphicsPanel(fWidth, fHeight);

		setPreferredSize(new Dimension(fWidth, fHeight));

		add(graphicsPanel);

		// May change this later
		setResizable(false);

		pack();

		setVisible(true);
	}
}
