package com.zagorskidev.carpricing.service.regression;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

@Component
public class RegressionEngine
{
	public Initialized getEngine()
	{
		return new Engine();
	}
	
	private class Engine implements Initialized, TrainingDatasetHolder, PredictionDatasetHolder, Normalized, Classifier
	{
		private LinearRegression classifier;
		
		private Instances trainingDataset;
		private Instances predictDataset;
		
		public TrainingDatasetHolder setTrainingDataset(Instances trainingDataset) 
		{
			this.trainingDataset = trainingDataset;
			return this;
		}

		@Override
		public PredictionDatasetHolder setPredictDataset(Instances predictDataset) 
		{
			this.predictDataset = predictDataset;
			return this;
		}

		@Override
		public Normalized normalize() throws Exception 
		{		
			Normalize normalizer = new Normalize();
		
			normalizer.setInputFormat(trainingDataset);
			trainingDataset = Filter.useFilter(trainingDataset, normalizer);
			predictDataset = Filter.useFilter(predictDataset, normalizer);
        
			return this;
		}

		@Override
		public Classifier createClassifier() throws Exception 
		{
			classifier = new LinearRegression();
	        
			classifier.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
			classifier.setEliminateColinearAttributes(false);
			
			classifier.buildClassifier(trainingDataset);
			
			showClassifier();
			
			return this;
		}
		
		private void showClassifier()
		{
			Logger.getGlobal().log(Level.INFO, classifier.toString().replaceAll("\n", " ").replaceAll("\t", " "));
		}
		
		@Override
		public BigDecimal predict() throws Exception 
		{
			return BigDecimal.valueOf(classifier.classifyInstance(predictDataset.firstInstance()));
		}
	}
}
