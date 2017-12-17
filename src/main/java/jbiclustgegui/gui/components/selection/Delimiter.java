package jbiclustgegui.gui.components.selection;

// TODO: Auto-generated Javadoc
/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
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
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */

/**
 * The Enum Delimiter.
 *
 * @author pmaia
 */
public enum Delimiter {
	
	
	/** The comma. */
	COMMA{
		public String getDelimiterString() {
			return ",";
		}
		
		public String getName() {
			return "Comma ( , )";
		}
	},
	
	/** The tab. */
	TAB{
		public String getDelimiterString() {
			return "\\t";
		}
		
		public String getName() {
			return "Tab ( \t )";
		}
	},
	
	/** The white space. */
	WHITE_SPACE{
		public String getDelimiterString() {
			return " ";
		}
		
		public String getName() {
			return "White Space ( )";
		}
	},
	
	/** The semicolon. */
	SEMICOLON{
		public String getDelimiterString() {
			return ";";
		}
		
		public String getName() {
			return "Semi-colon ( ; )";
		}
	};

	/**
	 * Gets the delimiter string.
	 *
	 * @return the delimiter string
	 */
	public String getDelimiterString() {
		return getDelimiterString();
	}
	

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * Gets the delimiter from string.
	 *
	 * @param delimiter the delimiter
	 * @return the delimiter from string
	 */
	public static Delimiter getDelimiterFromString(String delimiter) {
		if(delimiter!=null) {
			for (Delimiter element : Delimiter.values()) {
				if(delimiter.equals(element))
					return element;
			}
		}
		return Delimiter.WHITE_SPACE;
	}

}
