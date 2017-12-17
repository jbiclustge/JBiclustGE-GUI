package jbiclustgegui.gui.components.jfreechart;

import org.jfree.data.xy.AbstractXYZDataset;

public class XYZArrayDataset extends AbstractXYZDataset{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double[][] data;
    int rowCount = 0;
    int columnCount = 0;
    
    public XYZArrayDataset(double[][] data){
       this.data = data;
       rowCount = data.length;
       columnCount = data[0].length;
    }
    public int getSeriesCount(){
       return 1;
    }
    public Comparable getSeriesKey(int series){
       return "serie";
    }
    public int getItemCount(int series){
       return rowCount*columnCount;
    }
    public double getXValue(int series,int item){
       return (int)(item/columnCount);
    }
    public double getYValue(int series,int item){
       return item % columnCount;
    }
    public double getZValue(int series,int item){
       return data[(int)(item/columnCount)][item % columnCount];
    }
    public Number getX(int series,int item){
       return new Double((int)(item/columnCount));
    }
    public Number getY(int series,int item){
       return new Double(item % columnCount);
    }
    public Number getZ(int series,int item){
       return new Double(data[(int)(item/columnCount)][item % columnCount]);
    }
 }
