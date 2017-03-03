package de.draegerit.batchworker.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import de.draegerit.batchworker.entity.SensorEntity;

public class PDFWriter implements ItemWriter<SensorEntity> {

	@Override
	public void write(List<? extends SensorEntity> arg0) throws Exception {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Diese Funktion ist noch nicht implementiert!");
	}

}
