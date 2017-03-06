package de.draegerit.batchworker.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import de.draegerit.batchworker.entity.SensorEntity;

public class CSVWriter implements ItemWriter<SensorEntity> {

	private static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss:SS";
	private static final String STR_FORMAT = "%d;%s;%f;%s \r\n";
	private static final String EXPORT = "export_";
	private static final String CSV = ".csv";

	private DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);

	private long timestamp = 0L;

	/**
	 * Konstruktor
	 * @param timestamp - der Zeitpunkt wann die Stapelverarbeitung gestartet wurde
	 */
	public CSVWriter(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void write(List<? extends SensorEntity> sensoreValues) throws Exception {
		String filename = EXPORT.concat(String.valueOf(this.timestamp)).concat(CSV);
		File file = new File(filename);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			for (SensorEntity s : sensoreValues) {
				bw.append(getCSVLine(s));
			}
			bw.flush();
		}
	}

	/**
	 * Liefert eine Zeile für die CSV Datei
	 * @param s - die {@link SensorEntity}
	 * @return ein String Wert im Format {@link CSVWriter#STR_FORMAT}
	 */
	private CharSequence getCSVLine(SensorEntity s) {
		return String.format(STR_FORMAT, s.getId(), s.getDescription(), s.getValue(),
				dateFormat.format(new Date(s.getTimestamp())));
	}

}
