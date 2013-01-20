package com.dance.puppet.processor;

public class ProcessorFactory {
	
	public IProcessor createProcessor(String processorType){
		if(processorType.equalsIgnoreCase("NORMAL")){
			return new NormalProcessor();
		}if(processorType.equalsIgnoreCase("ANSWER")){
			return new AnswerProcessor();
		}
		return null;
	}
}
