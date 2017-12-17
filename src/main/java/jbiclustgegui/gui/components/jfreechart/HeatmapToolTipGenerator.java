package jbiclustgegui.gui.components.jfreechart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.labels.CustomXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.PublicCloneable;

public class HeatmapToolTipGenerator implements XYToolTipGenerator,
Cloneable, PublicCloneable, Serializable {

	/** For serialization. */
	private static final long serialVersionUID = 8636030004670141362L;

	/** Storage for the tooltip lists. */
	private List toolTipSeries = new java.util.ArrayList();
    private XYZDataset dataset;
    private double[][] datamatrix;
    private ArrayList<String> rowids;
    private ArrayList<String> columnsids;
	/**
	 * Default constructor.
	 */
	/*public HeatmapToolTipGenerator() {
		super();
	}*/
	
	public HeatmapToolTipGenerator(XYZDataset dataset, double[][] datamatrix, ArrayList<String> rowids, ArrayList<String> columnsids) {
		super();
		this.dataset=dataset;
		this.datamatrix=datamatrix;
		this.rowids=rowids;
		this.columnsids=columnsids;
	}


	/**
	 * Returns the number of tool tip lists stored by the renderer.
	 *
	 * @return The list count.
	 */
	public int getListCount() {
		return this.toolTipSeries.size();
	}

	/**
	 * Returns the number of tool tips in a given list.
	 *
	 * @param list  the list index (zero based).
	 *
	 * @return The tooltip count.
	 */
	public int getToolTipCount(int list) {

		int result = 0;
		List tooltips = (List) this.toolTipSeries.get(list);
		if (tooltips != null) {
			result = tooltips.size();
		}
		return result;
	}

	/**
	 * Returns the tool tip text for an item.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The tool tip text.
	 */
	public String getToolTipText(int series, int item) {

		String result = null;
		
		if(dataset!=null) {
			
			Double drowindex=(Double) dataset.getY(series, item);
			int rowindex=drowindex.intValue();
			
			Double dcolindex=(Double) dataset.getX(series, item);
			int colindex=dcolindex.intValue();
			if(rowids!=null && columnsids!=null) {
				result="("+rowids.get(rowindex)+", "+columnsids.get(colindex)+")";
			}

			if(datamatrix!=null) {
				if(result!=null)
					result=result+" = "+String.format("%.3f", datamatrix[rowindex][colindex]);
				else
					result="Value = "+String.format("%.3f", datamatrix[rowindex][colindex]);
			}
			
			
		}

		return result;
	}

	/**
	 * Adds a list of tooltips for a series.
	 *
	 * @param toolTips  the list of tool tips.
	 */
	public void addToolTipSeries(List toolTips) {
		this.toolTipSeries.add(toolTips);
	}

	/**
	 * Generates a tool tip text item for a particular item within a series.
	 *
	 * @param data  the dataset (ignored in this implementation).
	 * @param series  the series (zero-based index).
	 * @param item  the item (zero-based index).
	 *
	 * @return The tooltip text.
	 */
	@Override
	public String generateToolTip(XYDataset data, int series, int item) {
		return getToolTipText(series, item);
	}

	/**
	 * Returns an independent copy of the generator.
	 *
	 * @return A clone.
	 *
	 * @throws CloneNotSupportedException if cloning is not supported.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		HeatmapToolTipGenerator clone
		= (HeatmapToolTipGenerator) super.clone();
		if (this.toolTipSeries != null) {
			clone.toolTipSeries = new java.util.ArrayList(this.toolTipSeries);
		}
		return clone;
	}

	/**
	 * Tests if this object is equal to another.
	 *
	 * @param obj  the other object.
	 *
	 * @return A boolean.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof CustomXYToolTipGenerator) {
			CustomXYToolTipGenerator generator = (CustomXYToolTipGenerator) obj;
			boolean result = true;
			for (int series = 0; series < getListCount(); series++) {
				for (int item = 0; item < getToolTipCount(series); item++) {
					String t1 = getToolTipText(series, item);
					String t2 = generator.getToolTipText(series, item);
					if (t1 != null) {
						result = result && t1.equals(t2);
					}
					else {
						result = result && (t2 == null);
					}
				}
			}
			return result;
		}
		return false;
	}

}
