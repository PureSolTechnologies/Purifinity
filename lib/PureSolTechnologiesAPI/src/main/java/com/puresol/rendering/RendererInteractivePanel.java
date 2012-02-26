package com.puresol.rendering;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RendererInteractivePanel extends JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;

    private final RendererPanel rendererPanel;
    private final JSlider size = new JSlider(JSlider.VERTICAL);

    public RendererInteractivePanel(Renderer renderer) {
	rendererPanel = new RendererPanel(renderer);
	initUI();
    }

    private void initUI() {
	setLayout(new BorderLayout());

	JPanel sliderPanel = new JPanel();
	sliderPanel.add(new JLabel("Size:"));
	sliderPanel.add(size);

	size.setMinimum(100);
	int max = GraphicsEnvironment.getLocalGraphicsEnvironment()
		.getDefaultScreenDevice().getDisplayMode().getHeight();
	size.setMaximum(max);
	size.addChangeListener(this);

	add(new JScrollPane(rendererPanel), BorderLayout.CENTER);
	add(sliderPanel, BorderLayout.WEST);
    }

    public Renderer getRenderer() {
	return rendererPanel.getRenderer();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
	if (e.getSource() == size) {
	    Dimension dimension = new Dimension(size.getValue() * 4 / 3,
		    size.getValue());
	    rendererPanel.setSize(dimension);
	    rendererPanel.setPreferredSize(dimension);
	    rendererPanel.setMinimumSize(dimension);
	    rendererPanel.setMaximumSize(dimension);
	}
    }
}
