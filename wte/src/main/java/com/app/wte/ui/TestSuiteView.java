package com.app.wte.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.app.wte.model.ExecutionContext;
import com.app.wte.model.TestRecord;
import com.app.wte.testengine.TestEngine;
import com.app.wte.util.WTEUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@SpringView(name = TestSuiteView.VIEW_PATH)
public class TestSuiteView extends CssLayout implements View {

	public static final String VIEW_PATH = "testSuite";

	@Autowired
	TestEngine testEngine;

	@Value("${upload-path}")
	private String uploadPath;
	
	private TestSuiteGrid grid;

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() != null) {
			// split at "/", add each part as a label
			String[] params = event.getParameters().split("/");
			String testCase = params[0];

			List<TestRecord> testRecords = new ArrayList<TestRecord>();

			ExecutionContext executionContext = testEngine.getTestResult(testCase);
			if (executionContext == null) {
				executionContext = WTEUtils.jaxbXMLToObject(testCase, uploadPath);
			}
			if (executionContext != null && executionContext.getTestRecordList() != null) {
				testRecords = executionContext.getTestRecordList();
			}
			setSizeFull();
			addStyleName("crud-view");
			grid = new TestSuiteGrid();
			grid.setItems(testRecords);				
			
			VerticalLayout barAndGridLayout = new VerticalLayout();
			HorizontalLayout horLayOut = new HorizontalLayout();
			Label title = new Label();
			title.setValue("Test Case : "+testCase);
			title.setStyleName("titleLabel");
			horLayOut.addComponent(title);
			barAndGridLayout.addComponent(horLayOut);
			barAndGridLayout.addComponent(grid);
			barAndGridLayout.setSizeFull();
			barAndGridLayout.setMargin(false);
			barAndGridLayout.setSpacing(false);
			barAndGridLayout.setExpandRatio(grid, 1);
			barAndGridLayout.setStyleName("crud-main-layout");
			addComponent(barAndGridLayout);
		}
		

	}

}
