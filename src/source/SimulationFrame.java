package source;

import javax.swing.JFrame;

public class SimulationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimulationFrame() {
		add(new SimulationPanel(1080, 720, this));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
	}

}
