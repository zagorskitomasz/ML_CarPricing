package com.zagorskidev.carpricing.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.service.regression.RegressionEngine;

import weka.core.Instances;

@Service
public class RegressionServiceImpl implements RegressionService 
{
	@Autowired
	private DataService dataService;
	
	@Autowired
	private DataMapper dataMapper;
	
	@Override
	public BigDecimal predict(CarParameters params) 
	{
		Collection<CarParams> cars = dataService.loadCarTypeData(params.getCategory());
		
		Instances trainingDataset = dataMapper.map(cars);
		Instances predictDataset = dataMapper.map(params);
		
		return predict(trainingDataset, predictDataset);
	}
	
	private BigDecimal predict(Instances trainingDataset, Instances predictDataset)
	{
		try
		{
		return RegressionEngine
				.init()
				.setTrainingDataset(trainingDataset)
				.setPredictDataset(predictDataset)
				.normalize()
				.createClassifier()
				.predict();
		}
		catch(Exception ex)
		{
			Logger.getGlobal().log(Level.INFO, "Error occured while predicting");
			ex.printStackTrace();
			return BigDecimal.valueOf(-1);
		}
	}
}
