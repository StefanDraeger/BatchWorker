package de.draegerit.batchworker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import de.draegerit.batchworker.entity.SensorEntity;

public class CustomItemProcessor implements ItemProcessor<SensorEntity, SensorEntity> {

	private static final Logger log = LoggerFactory.getLogger(CustomItemProcessor.class);

	@Override
	public SensorEntity process(SensorEntity item) throws Exception {

		log.info("Processing..." + item);
		return item;
	}

}
