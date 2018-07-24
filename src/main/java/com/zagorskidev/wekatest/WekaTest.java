package com.zagorskidev.wekatest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class WekaTest {
	
	private enum DataSetType {TRAIN, TEST, PREDICTION};
	
	private static final String DATA_SET_HEADER = ""
			+ "@RELATION text\n"
			+ "\n"
			+ "@ATTRIBUTE feature1 NUMERIC\n"
			+ "@ATTRIBUTE feature2 NUMERIC\n"
			+ "@ATTRIBUTE value   NUMERIC\n"
			+ "\n"
			+ "@DATA\n";
	
	private static final double[][] PREDICTION_DATA = 
		{
			{3.0,17.0,0.0}
		};
	
	private static final double[][] TRAINING_DATA = 
		{
			{2.0,4.0,4.4},
			{1.0,1.0,1.6},
			{8.0,50.0,15.0},
			{4.0,16.0,6.0},
			{15.0,100.0,38.0},
			{2.1,4.9,4.4},
			{1.3,1.7,1.6},
			{7.9,60.1,15.0},
			{3.3,10.7,6.0},
			{17.0,110.0,38.0}
		};
	
	private static final double[][] TEST_DATA = 
		{
			{10.0,104.0,20.0},
			{2.25,5.75,5.0},
			{12.0,200.0,28.0},
			{11.0,130.0,20.0},
			{1.9,4.1,4.0},
			{12.1,99.9,22.0}
		};
	
	private Instances trainingDataSet;
	private Instances testingDataSet;
	private Instances predicationDataSet;
	
	private LinearRegression classifier;

	public static void main(String[] args) throws Exception {

		WekaTest wekaTest = new WekaTest();
		
		wekaTest.loadData();
		wekaTest.normalize();
		wekaTest.createClassifier();
		wekaTest.crossValidation();
		wekaTest.predict();
	}
	
	private void loadData() throws IOException
	{
		trainingDataSet = getDataSet(DataSetType.TRAIN);
		testingDataSet = getDataSet(DataSetType.TEST);
		predicationDataSet = getDataSet(DataSetType.PREDICTION);
	}
	
	private Instances getDataSet(DataSetType dsType) throws IOException {
		
		InputStream streamData = getHeader();
		
		ArffLoader loader = new ArffLoader();
		loader.setSource(streamData);
		
		Instances dataSet = loader.getDataSet();
		
		dataSet.setClassIndex(dataSet.numAttributes() - 1);
		
		addData(dataSet, dsType);
		
		return dataSet;
	}

	private InputStream getHeader()
	{
		String data = DATA_SET_HEADER;
		
		return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
	}
	
	private void addData(Instances dataSet, DataSetType dsType) 
	{
		switch(dsType)
		{
		case PREDICTION:
			addData(dataSet, PREDICTION_DATA);
			break;
		case TEST:
			addData(dataSet, TEST_DATA);
			break;
		case TRAIN:
			addData(dataSet, TRAINING_DATA);
			break;
		}	
	}

	private void addData(Instances dataSet, double[][] data) 
	{
		for(double[] row : data)
		{
			Instance instance = new DenseInstance(1.0, row);
			dataSet.add(instance);
		}
	}
	
	private void normalize() throws Exception
	{
		Normalize normalizer = new Normalize();
		
        normalizer.setInputFormat(trainingDataSet);
        trainingDataSet = Filter.useFilter(trainingDataSet, normalizer);
        testingDataSet = Filter.useFilter(testingDataSet, normalizer);
        predicationDataSet = Filter.useFilter(predicationDataSet, normalizer);
	}

	private void createClassifier() throws Exception 
	{
		classifier = new LinearRegression();
        
		classifier.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
		classifier.setEliminateColinearAttributes(false);
		
		classifier.buildClassifier(trainingDataSet);
		
		showClassifier();
	}
	
	private void showClassifier()
	{
		System.out.println(classifier.toString().replaceAll("\n", " ").replaceAll("\t", " "));
	}
	
	private void crossValidation() throws Exception
	{
		Evaluation eval = new Evaluation(trainingDataSet);
		eval.crossValidateModel(classifier, testingDataSet, testingDataSet.size(), new Random(1));
		
		System.out.println(eval.toSummaryString());
	}
	
	private void predict() throws Exception
	{
		double value = classifier.classifyInstance(predicationDataSet.firstInstance());
		System.out.println(value);
	}
}
