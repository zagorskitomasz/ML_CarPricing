package com.zagorskidev.carpricing.service.regression;

import weka.core.Instances;

public interface TrainingDatasetHolder 
{
	public PredictionDatasetHolder setPredictDataset(Instances predictDataset);
}
