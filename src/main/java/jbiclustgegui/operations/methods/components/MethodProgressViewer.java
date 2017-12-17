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
package jbiclustgegui.operations.methods.components;

import es.uvigo.ei.aibench.core.operation.annotation.ProgressProperty;
import pt.ornrocha.timeutils.MTUTimeUtils;

public class MethodProgressViewer {

	public String algorithmname = null;
	public String started=null;
	
	public MethodProgressViewer() {
		this.started=MTUTimeUtils.getCurrentDateAndTime("yyyy-MM-dd HH:mm.ss");
		}
	
	public MethodProgressViewer(String algorithm) {
		this.algorithmname=algorithm;
		this.started=MTUTimeUtils.getCurrentDateAndTime("yyyy-MM-dd HH:mm.ss");;
	}
	
	@ProgressProperty(order = 1, label = "Algorithm: ")
	synchronized public String getName(){
		return algorithmname;
	}
	
	
	@ProgressProperty(order = 2, label = "Started at: ")
	synchronized public String getStart(){
		return started;
	}
	
	
	synchronized public void setName(String algorithm){
		this.algorithmname=algorithm;
	}
	
}
