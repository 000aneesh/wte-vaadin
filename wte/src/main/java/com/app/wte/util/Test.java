package com.app.wte.util;

import java.util.ArrayList;
import java.util.List;

import com.app.wte.type.ExecutionStepType;

public class Test {

	public static void main(String[] args) {
		List<String> executionSteps = new ArrayList<String>();
		for(ExecutionStepType type : ExecutionStepType.values()) {
			executionSteps.add(type.getExecutionStep());
		}
	//	System.out.println(enumValues);
		
		//System.out.println(enumNames);
		

	}

}
