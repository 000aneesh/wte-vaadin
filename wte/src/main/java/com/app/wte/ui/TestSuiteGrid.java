package com.app.wte.ui;

import com.app.wte.model.TestRecord;
import com.vaadin.ui.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data provider that is suitable for
 * small data sets.
 */
public class TestSuiteGrid extends Grid<TestRecord> {

	public TestSuiteGrid() {
		setSizeFull();

		addColumn(TestRecord::getTestCaseNumber).setCaption("Test Case #").setSortProperty("id");
		// addColumn(TestRunHistory :: getStatus).setCaption("Status")
		// .setSortProperty("status");
		addColumn(TestRecord::getTestCaseDescription).setCaption("Description").setSortProperty("description");
		addColumn(TestRecord::getStatus).setCaption("Status").setSortProperty("status");
		addColumn(TestRecord::getTestresult).setCaption("Test Result").setSortProperty("result");
	}


	public TestRecord getSelectedRow() {
		return asSingleSelect().getValue();
	}

	public void refresh(TestRecord testRecord) {
		getDataCommunicator().refresh(testRecord);
	}
}
