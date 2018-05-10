package com.app.wte.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.wte.type.ExecutionStatusType;
import com.app.wte.ui.TestRunHistory;

@Component
public class TestResultComponent {
	private static final Logger logger = LoggerFactory.getLogger(TestResultComponent.class);
	
	@Value("${upload-path}")
	private String uploadPath;
	
	List<String> directoryNames=new ArrayList<String>();
	
	List<TestRunHistory> testRunHistoryList = new ArrayList<TestRunHistory>();

	@PostConstruct
	public void initialize() throws IOException {
		
		File[] directories = new File(uploadPath).listFiles(File::isDirectory);
		if(directories != null) {
			for (File dir : directories) {
				TestRunHistory testRunHistory = new TestRunHistory();
				testRunHistory.setName(dir.getName());
				testRunHistory.setStatus(ExecutionStatusType.COMPLETED.toString());
				testRunHistory.setDate(new Date());
				testRunHistoryList.add(testRunHistory);
				directoryNames.add(dir.getName());
			}
		}		
	}

	public List<String> getDirectoryNames() {
		return directoryNames;
	}

	public void setDirectoryNames(List<String> directoryNames) {
		this.directoryNames = directoryNames;
	}

	/**
	 * @return the testRunHistoryList
	 */
	public List<TestRunHistory> getTestRunHistoryList() {
		return testRunHistoryList;
	}

	/**
	 * @param testRunHistoryList the testRunHistoryList to set
	 */
	public void setTestRunHistoryList(List<TestRunHistory> testRunHistoryList) {
		this.testRunHistoryList = testRunHistoryList;
	}

}
