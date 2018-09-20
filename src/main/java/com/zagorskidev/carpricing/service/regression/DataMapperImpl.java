package com.zagorskidev.carpricing.service.regression;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.service.CarParamType;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@Component
public class DataMapperImpl implements DataMapper 
{
	private static final String DATA_SET_HEADER = ""
			+ "@RELATION text\n"
			+ "\n"
			+ "@ATTRIBUTE year     NUMERIC\n"
			+ "@ATTRIBUTE capacity NUMERIC\n"
			+ "@ATTRIBUTE mileage  NUMERIC\n"
			+ "@ATTRIBUTE power    NUMERIC\n"
			+ "@ATTRIBUTE price    NUMERIC\n"
			+ "\n"
			+ "@DATA\n";

	@Override
	public Instances map(CarParams params) 
	{
		Instances dataSet = prepareDataset();
		addData(dataSet, params);
			
		return dataSet;
	}
	
	@Override
	public Instances map(Collection<CarParams> cars) 
	{
		Instances dataSet = prepareDataset();
			
		if(dataSet != null)
			cars.forEach(car -> addData(dataSet, car));
			
		return dataSet;
	}

	private Instances prepareDataset()
	{
		try
		{
			InputStream streamData = getHeader();

			ArffLoader loader = new ArffLoader();
			loader.setSource(streamData);

			Instances dataSet = loader.getDataSet();
			dataSet.setClassIndex(dataSet.numAttributes() - 1);
			
			return dataSet;
		}
		catch(IOException ex)
		{
			Logger.getGlobal().log(Level.INFO, "Error occured while predicting");
			ex.printStackTrace();
			return null;
		}
	}

	private void addData(Instances dataSet, CarParams car) 
	{
		double[] row = createRow(car);
		
		if(row == null)
			return;
		
		Instance instance = new DenseInstance(1.0, row);
		dataSet.add(instance);
	}

	private double[] createRow(CarParams car) 
	{		
		if(!fullDataAvailable(car))
			return null;
		
		double[] row = new double[CarParamType.values().length];
			
		for(int i = 0; i < row.length; i++)
			row[i] = car.getParams().get(CarParamType.values()[i].name());
			
		return row;
	}

	private boolean fullDataAvailable(CarParams car) 
	{
		for(CarParamType param : CarParamType.values())
		{
			if(!car.getParams().containsKey(param.name()))
				return false;
		}
		return true;
	}

	private InputStream getHeader()
	{
		String data = DATA_SET_HEADER;
		
		return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
	}
}
