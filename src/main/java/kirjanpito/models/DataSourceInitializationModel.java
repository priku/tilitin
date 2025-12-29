package kirjanpito.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import kirjanpito.ui.Kirjanpito;

public class DataSourceInitializationModel {
	private static final String MODELS_PATH = "/tilikarttamallit/";
	private Logger logger = Logger.getLogger(Kirjanpito.LOGGER_NAME);
	private Map<String, String> modelNameToDir;

	public DataSourceInitializationModel() {
		modelNameToDir = new HashMap<>();
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream(MODELS_PATH + "mallit.properties"));
			props.forEach((key, value) -> modelNameToDir.put(value.toString(), MODELS_PATH + key.toString()));
		} catch (IOException e) {
			logger.log(Level.WARNING, "Tilikarttamallien hakeminen epäonnistui", e);
		}
	}

	public void update() {
		// No-op for compatibility
	}

	public int getModelCount() {
		return modelNameToDir.size();
	}

	public int getFileCount() {
		// For compatibility with old API
		return getModelCount();
	}

	public String[] getModelNames() {
		return modelNameToDir.keySet().toArray(new String[0]);
	}

	public String getName(int index) {
		// For compatibility with old API
		String[] names = getModelNames();
		return names[index];
	}

	public String getModelNameByIndex(int index) {
		return getName(index);
	}

	public InputStream getModelFileAsStream(String modelName, String fileName) {
		InputStream result = getClass().getResourceAsStream(modelNameToDir.get(modelName) + "/" + fileName);
		if (result == null) {
			throw new IllegalArgumentException("Tilikarttamallia ei löydy: " + modelName + " -> "
					+ modelNameToDir.get(modelName) + "/" + fileName);
		}
		return result;
	}
}
