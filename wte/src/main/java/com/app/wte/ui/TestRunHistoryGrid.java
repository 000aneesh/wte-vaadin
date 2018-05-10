package com.app.wte.ui;

import com.app.wte.type.ExecutionStatusType;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data provider that is suitable for
 * small data sets.
 */
public class TestRunHistoryGrid extends Grid<TestRunHistory> {

	public TestRunHistoryGrid() {
		setSizeFull();

		addColumn(TestRunHistory::getName).setCaption("Test Case").setSortProperty("name");
		addColumn(p -> {
			String status = p.getStatus();
			return getTrafficLightIconHtml(status) + " " + status;
		}, new HtmlRenderer()).setCaption("Status").setSortProperty("status");
		// addColumn(TestRunHistory :: getStatus).setCaption("Status")
		// .setSortProperty("status");
		addColumn(TestRunHistory::getDate).setCaption("Date").setSortProperty("date");
	}

	private String getTrafficLightIconHtml(String status) {
		String color = "";
		if (ExecutionStatusType.COMPLETED.toString().equals(status)) {
			color = "#2dd085";
		} else if (ExecutionStatusType.IN_PROGRESS.toString().equals(status)) {
			color = "#ffc66e";
		} else if (ExecutionStatusType.ERROR.toString().equals(status)) {
			color = "#f54993";
		}

		String iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.CIRCLE.getFontFamily()
				+ ";color:" + color + "\">&#x" + Integer.toHexString(VaadinIcons.CIRCLE.getCodepoint()) + ";</span>";
		return iconCode;
	}

	public TestRunHistory getSelectedRow() {
		return asSingleSelect().getValue();
	}

	public void refresh(TestRunHistory testRunHistory) {
		getDataCommunicator().refresh(testRunHistory);
	}
}
