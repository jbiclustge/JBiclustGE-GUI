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
package jbiclustgegui.gui.analysis.profilecli.components;

import java.util.Properties;

import jbiclustge.methods.algorithms.BiclusteringMethod;

public class ProfileBiclusteringMethodConfiguration {
	
	
	
	private BiclusteringMethod method;
	private String sufixname;
	private Properties methodsettings;
	private int runtimes=1;

	
	

	public ProfileBiclusteringMethodConfiguration(BiclusteringMethod method, String sufixname,
			Properties methodsettings, int numberruns) {
		super();
		this.method = method;
		this.sufixname = sufixname;
		this.methodsettings = methodsettings;
		this.runtimes=numberruns;
	}



	
	

	public BiclusteringMethod getMethod() {
		return method;
	}


	public String getSufixname() {
		return sufixname;
	}


	public Properties getMethodsettings() {
		return methodsettings;
	}
	
	

	public int getRuntimes() {
		return runtimes;
	}






	@Override
	public String toString() {
		return method.getAlgorithmName()+" "+sufixname;
	}
	

}
