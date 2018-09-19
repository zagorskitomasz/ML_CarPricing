package com.zagorskidev.carpricing.service.regression;

import weka.core.Instances;

public interface Initialized
{
	public TrainingDatasetHolder setTrainingDataset(Instances trainingDataset);
}
