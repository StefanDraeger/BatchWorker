package de.draegerit.batchworker.util;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class CSVSensorTokenizer extends DelimitedLineTokenizer {
	{
		setNames(new String[] { "ID", "DESCRIPTION", "VALUE", "TIMESTAMP" });
	}
}
