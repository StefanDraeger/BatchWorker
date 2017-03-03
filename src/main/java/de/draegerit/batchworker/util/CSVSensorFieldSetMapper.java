package de.draegerit.batchworker.util;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import de.draegerit.batchworker.entity.SensorEntity;

public class CSVSensorFieldSetMapper extends BeanWrapperFieldSetMapper<SensorEntity>{
	{
		setTargetType(SensorEntity.class);
	}
}
