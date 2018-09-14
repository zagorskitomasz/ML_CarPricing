package com.zagorskidev.carpricing.service.regression;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class RegressionEngine 
{
	public static Initialized init()
	{
		return new Engine();
	}
	
	public interface Initialized
	{
		public TrainingDatasetHolder setTrainingDataset(Instances trainingDataset);
	}

	public interface TrainingDatasetHolder 
	{
		public PredictDatasetHolder setPredictDataset(Instances predictDataset);
	}

	public interface PredictDatasetHolder 
	{
		public Normalized normalize() throws Exception;
	}

	public interface Normalized 
	{
		public Classifier createClassifier() throws Exception;
	}

	public interface Classifier 
	{
		public BigDecimal predict() throws Exception;
	}
	
	private static class Engine implements Initialized, TrainingDatasetHolder, PredictDatasetHolder, Normalized, Classifier
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
		public PredictDatasetHolder setPredictDataset(Instances predictDataset) 
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
