/************************************************************************** 
 * Copyright 2017
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 *  
 * Created by Orlando Rocha (ornrocha@gmail.com) inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.gui.components.panels.data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GradientPanel.
 */
public class GradientPanel  extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant HORIZONTAL. */
	public final static int HORIZONTAL = 0;
	
	/** The Constant VERTICAL. */
	public final static int VERTICAL = 1;


	/** The direction. */
	private int direction = HORIZONTAL;
	
	/** The cyclic. */
	private boolean cyclic;
	
	/** The max length. */
	private int maxLength;
	
	/** The start color. */
	private Color startColor=Color.GREEN;
	
	/** The end color. */
	private Color endColor=Color.RED;



	  /**
  	 * Instantiates a new gradient panel.
  	 */
  	public GradientPanel(){
	    this(HORIZONTAL);
	  }
	  
	  /**
  	 * Instantiates a new gradient panel.
  	 *
  	 * @param startColor the start color
  	 * @param endColor the end color
  	 */
  	public GradientPanel(Color startColor , Color endColor){
		    this(HORIZONTAL);
		    this.startColor=startColor;
		    this.endColor=endColor;
	  }

	  /**
  	 * Instantiates a new gradient panel.
  	 *
  	 * @param direction the direction
  	 */
  	public GradientPanel( int direction ){
	    super( new BorderLayout() );
	    setOpaque( false );
	    this.direction = direction;
	  }

	  

	  /**
  	 * Sets the start color.
  	 *
  	 * @param startColor the new start color
  	 */
  	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	/**
	 * Sets the end color.
	 *
	 * @param endColor the new end color
	 */
	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public int getDirection() {
	    return direction;
	  }

	  /**
  	 * Sets the direction.
  	 *
  	 * @param direction the new direction
  	 */
  	public void setDirection( int direction ){
	    this.direction = direction;
	  }

	  /**
  	 * Checks if is cyclic.
  	 *
  	 * @return true, if is cyclic
  	 */
  	public boolean isCyclic(){
	    return cyclic;
	  }

	  /**
  	 * Sets the cyclic.
  	 *
  	 * @param cyclic the new cyclic
  	 */
  	public void setCyclic( boolean cyclic ){
	    this.cyclic = cyclic;
	  }

	  /**
  	 * Sets the max length.
  	 *
  	 * @param maxLength the new max length
  	 */
  	public void setMaxLength( int maxLength )
	  {
	    this.maxLength = maxLength;
	  }


	  /* (non-Javadoc)
  	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
  	 */
  	public void paintComponent( Graphics g )
	  {
	    if( isOpaque() )
	    {
	      super.paintComponent( g );
	      return;
	    }

	    int width = getWidth();
	    int height = getHeight();

	    // Create the gradient paint
	    GradientPaint paint = null;


	    switch( direction ){
	    	case HORIZONTAL :
	    	{
	    		paint = new GradientPaint( 0, height / 2, startColor, width, height / 2, endColor, cyclic );
	    		break;
	    	}
	    	case VERTICAL :
	    	{
	    		paint = new GradientPaint( width / 2, 0, startColor, width / 2, maxLength > 0 ? maxLength : height, endColor, cyclic );
	    		break;
	    	}
	
	    }

	    // we need to cast to Graphics2D for this operation
	    Graphics2D g2d = ( Graphics2D )g;

	    // save the old paint
	    Paint oldPaint = g2d.getPaint();

	    // set the paint to use for this operation
	    g2d.setPaint( paint );

	    // fill the background using the paint
	    g2d.fillRect( 0, 0, width, height );

	    // restore the original paint
	    g2d.setPaint( oldPaint );

	    super.paintComponent( g );
	  }
	  
	  
	  /**
  	 * The main method.
  	 *
  	 * @param args the arguments
  	 */
  	public static void main(String args[]) {
		    JFrame frame = new JFrame("Oval Sample");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    GradientPanel g=new GradientPanel();
		    g.setDirection(0);

		
		    frame.add(g);
		    frame.setSize(300, 200);
		    frame.setVisible(true);
		  }
}
