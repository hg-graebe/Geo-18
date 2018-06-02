package gr2.cips.jsxgraph;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Duong Trung Duong
 *
 */
public class JSXGraph {
	final static Logger logger = Logger.getLogger(JSXGraph.class);
	private List<JSXGraphElement> jsxGraphElements;
	private String title;

	public JSXGraph() {
		jsxGraphElements = new ArrayList<JSXGraphElement>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setJSXGraphElements(List<JSXGraphElement> jsxGraphElements) {
		this.jsxGraphElements = jsxGraphElements;
	}

	public String getTitle() {
		return this.title;
	}

	public List<JSXGraphElement> getJSXGraphElements() {
		return this.jsxGraphElements;
	}

	public void addElement(JSXGraphElement jsxGraphElement) {
		this.jsxGraphElements.add(jsxGraphElement);
	}

	public JSXGraphElement getElementByID(String id) {
		for (JSXGraphElement jsxGraphElement : jsxGraphElements) {
			if (jsxGraphElement.getID().equals(id)) {
				return jsxGraphElement;
			}
		}
		return null;
	}

	public void exportVisualization(String visualizationFilePath) {
		JSXGraphExportVisualization jsxGraphExportVisualization = new JSXGraphExportVisualization(this,
				visualizationFilePath);
		jsxGraphExportVisualization.visualize();
	}
}
