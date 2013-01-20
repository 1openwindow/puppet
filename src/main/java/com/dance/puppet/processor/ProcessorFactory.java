package com.dance.puppet.processor;

public class ProcessorFactory {
	
	public IProcessor createProcessor(String processorType){
		if(processorType.equalsIgnoreCase("NORMAL")){
			return new NormalProcessor();
		}else if(processorType.equalsIgnoreCase("ANSWER")){
			return new AnswerProcessor();
		}else if(processorType.equalsIgnoreCase("REMOVE")){
			return new RemoveProcessor();
		}
		
		return null;
	}
}
