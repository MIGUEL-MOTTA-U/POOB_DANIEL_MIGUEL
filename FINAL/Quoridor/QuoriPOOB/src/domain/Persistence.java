package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Persistence {
    /**
	 * Save the game in a file
	 * 
	 * @param file the file to save
	 */
	public void saveFile(File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(QuoriPOOB.getQuoriPOOB());
			out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Open a file given by the user
	 * 
	 * @param file the file to open
	 * @return the garden saved in the file
	 * @throws QuoriPOOBException
	 */
	public static QuoriPOOB openFile(File file) throws QuoriPOOBException {
		QuoriPOOB quoriPOOB = null;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			quoriPOOB = (QuoriPOOB) in.readObject();
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
		}

		QuoriPOOB.setQuoriPOOB(quoriPOOB);
		return QuoriPOOB.getQuoriPOOB();
	}
}
