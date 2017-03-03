package de.draegerit.batchworker.util;

import org.springframework.batch.item.ItemProcessor;

import de.draegerit.batchworker.entity.SensorEntity;

public class CustomItemProcessor implements ItemProcessor<SensorEntity, SensorEntity> {

	@Override
	public SensorEntity process(SensorEntity item) throws Exception {

		System.out.println("Processing..." + item);
		return item;
	}

}
