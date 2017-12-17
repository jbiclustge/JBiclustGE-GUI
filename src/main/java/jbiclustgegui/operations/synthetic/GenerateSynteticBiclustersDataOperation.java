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
package jbiclustgegui.operations.synthetic;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.javatuples.Pair;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.core.operation.annotation.Progress;
import jbiclustge.analysis.syntheticdata.distribution.NormalDistributionDataFactory;
import jbiclustge.analysis.syntheticdata.factory.AbstractSyntheticDataFactory;
import jbiclustge.analysis.syntheticdata.factory.props.SyntheticDataProperties;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.datatools.utils.nd4j.ND4JUtils;
import jbiclustgegui.gui.synthetic.containers.IBiclusterModelDataFactory;
import pt.ornrocha.fileutils.MTUDirUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateSynteticBiclustersDataOperation.
 */
@Operation(name="Generate Synthetic Data",description="Generate Synthetic Data with diferent bicluster models",enabled = false)
public class GenerateSynteticBiclustersDataOperation {
	
	
	/** The modelfactories. */
	private ArrayList<IBiclusterModelDataFactory> modelfactories;
	
	/** The backdist. */
	private NormalDistributionDataFactory backdist;
	
	/** The repeatbackground. */
	private boolean repeatbackground=true;
	
	/** The parameters. */
	private SyntheticDataProperties parameters;
	
	/** The saveindir. */
	private String saveindir;
	
	/** The symmetricoverlap. */
	private ArrayList<Integer> symmetricoverlap;
	
	/** The asymmetricoverlap. */
	private ArrayList<ArrayList<Pair<Integer, Integer>>> asymmetricoverlap;
	
	/** The experimentsnoise. */
	private ArrayList<Double> experimentsnoise;
	
	/** The numbersamples. */
	private int numbersamples=20;
	
	/** The changesupport. */
	private PropertyChangeSupport changesupport=new PropertyChangeSupport(this);
	
	/** The processlistener. */
	private SyntheticDataOperationListener processlistener=new SyntheticDataOperationListener();
	
	/** The firepropertygseachangetaskstatus. */
	public static String FIREPROPERTYGSEACHANGETASKSTATUS="FIREPROPERTYCHANGETASKSTATUS";
	
	/** The firepropertygseachangesubtaskstatus. */
	public static String FIREPROPERTYGSEACHANGESUBTASKSTATUS="FIREPROPERTYCHANGESUBTASKSTATUS";
	
	/**
	 * Sets the factories.
	 *
	 * @param modelfactories the new factories
	 */
	@Port(name="List Model Factories",direction=Direction.INPUT,order=1)
	public void setFactories(ArrayList<IBiclusterModelDataFactory> modelfactories) {
		this.modelfactories=modelfactories;
	}
	
	/**
	 * Sets the background distribution.
	 *
	 * @param backdist the new background distribution
	 */
	@Port(name="Background dist",direction=Direction.INPUT,order=2)
	public void setBackgroundDistribution(NormalDistributionDataFactory backdist) {
		this.backdist=backdist;
	}
	
	/**
	 * Sets the repeat background.
	 *
	 * @param repeatbackground the new repeat background
	 */
	@Port(name="Repeat background",direction=Direction.INPUT,order=3)
	public void setRepeatBackground(boolean repeatbackground) {
		this.repeatbackground=repeatbackground;
	}
	
	/**
	 * Sets the data properties.
	 *
	 * @param parameters the new data properties
	 */
	@Port(name="Data Properties",direction=Direction.INPUT,order=4)
	public void setDataProperties(SyntheticDataProperties parameters) {
		this.parameters=parameters;
	}
	
	/**
	 * Sets the symmetric overlap.
	 *
	 * @param symmetricoverlap the new symmetric overlap
	 */
	@Port(name="symmetric overlap",direction=Direction.INPUT,order=5)
	public void setSymmetricOverlap(ArrayList<Integer> symmetricoverlap) {
		this.symmetricoverlap=symmetricoverlap;
	}
	
	/**
	 * Sets the asymmetric overlap.
	 *
	 * @param asymmetricoverlap the asymmetricoverlap
	 */
	@Port(name="asymmetric overlap",direction=Direction.INPUT,order=6)
	public void setAsymmetricOverlap(ArrayList<ArrayList<Pair<Integer, Integer>>> asymmetricoverlap) {
		this.asymmetricoverlap=asymmetricoverlap;
	}
	
	/**
	 * Sets the noise in experiments.
	 *
	 * @param experimentsnoise the new noise in experiments
	 */
	@Port(name="Experiments Noise",direction=Direction.INPUT,order=7)
	public void setNoiseInExperiments(ArrayList<Double> experimentsnoise) {
		this.experimentsnoise=experimentsnoise;
	}
	
	/**
	 * Sets the number samples.
	 *
	 * @param numbersamples the new number samples
	 */
	@Port(name="Number Samples",direction=Direction.INPUT,order=8)
	public void setNumberSamples(int numbersamples) {
		this.numbersamples=numbersamples;
	}
	
	/**
	 * Sets the save dir.
	 *
	 * @param savedirpath the new save dir
	 */
	@Port(name="Save to folder",direction=Direction.INPUT,order=9)
	public void setSaveDir(String savedirpath) {
		this.saveindir=savedirpath;
		try {
			run();
		} catch (Exception e) {
			e.printStackTrace();
			
			//Workbench.getInstance().error(e);
		}
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Progress()
	public SyntheticDataOperationListener getStatus() {
		return processlistener;
	}
	
	
	/**
	 * Run.
	 *
	 * @throws Exception the exception
	 */
	private void run() throws Exception {
		
		changesupport.addPropertyChangeListener(processlistener);
		
		if(experimentsnoise!=null) {
				GenerateExperimentsWithNoise();
		}
		
		if(symmetricoverlap!=null) {
			makeDatasetsWithSymmetricOverlap();
		}
		else if(asymmetricoverlap!=null) {
			makeDatasetsWithASymmetricOverlap();
		}
        
		if(experimentsnoise==null && symmetricoverlap==null && asymmetricoverlap==null) {
			
			String folder=FilenameUtils.concat(saveindir, "Datasets_without_perturbations");
			MTUDirUtils.makeDirectory(folder);
			changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiments");
			GenerateExperiments(folder, null);
		}
		
		
		
		/*saveindir="/home/orocha/discodados/ApenasTrabalho/Testes/syntheticData";
		
		for (int i = 0; i <numberdatasets; i++) {
			
			INDArray background=AbstractSyntheticDataFactory.generateBackgroundOfDataset(backdist,parameters.getNumberrowsdata(), parameters.getNumbercolumnsdata());
			
			for (IBiclusterModelDataFactory factorycontainer : modelfactories) {
				
				AbstractSyntheticDataFactory factory=factorycontainer.getSyntheticDataBuilder();
				factory.setBackgroundOfDataset(background);
				
				saveDataset(factory, i, MTUDirUtils.checkandsetDirectory(FilenameUtils.concat(saveindir, factorycontainer.getName())));
			}
		}*/
	}
	
	
	/**
	 * Generate experiments.
	 *
	 * @param saveinfolder the saveinfolder
	 * @param overlapprofile the overlapprofile
	 * @return the linked hash map
	 * @throws Exception the exception
	 */
	private LinkedHashMap<String, String> GenerateExperiments(String saveinfolder, ArrayList<Pair<Integer, Integer>> overlapprofile) throws Exception {
		
		LinkedHashMap<String, String> modeldatasetsdirs=new LinkedHashMap<>();

		for (int i = 0; i <numbersamples; i++) {

			INDArray background=AbstractSyntheticDataFactory.generateBackgroundOfDataset(backdist,parameters.getNumberrowsdata(), parameters.getNumbercolumnsdata());

			for (IBiclusterModelDataFactory factorycontainer : modelfactories) {

				changesupport.firePropertyChange(FIREPROPERTYGSEACHANGESUBTASKSTATUS, null, "Generating dataset "+String.valueOf((i+1))+" for model: "+factorycontainer.getName());
				if(!repeatbackground)
					background=AbstractSyntheticDataFactory.generateBackgroundOfDataset(backdist,parameters.getNumberrowsdata(), parameters.getNumbercolumnsdata());

				AbstractSyntheticDataFactory factory=factorycontainer.getSyntheticDataBuilder();
				factory.setBackgroundOfDataset(background);
				if(overlapprofile!=null) {
					factory.getSyntheticDataPropertiescontainer().resetOverlapListBetweenTwoBiclusters();
					factory.getSyntheticDataPropertiescontainer().setOverlapBetweenBiclusters(overlapprofile);
				}
				else if(overlapprofile==null && factory.getSyntheticDataPropertiescontainer().getOverlapBetweenBiclusters()!=null)
					factory.getSyntheticDataPropertiescontainer().resetOverlapListBetweenTwoBiclusters();

				String saveto=MTUDirUtils.checkandsetDirectory(FilenameUtils.concat(saveinfolder, factorycontainer.getName()));
				saveDataset(factory, i, saveto);
				
				if(!modeldatasetsdirs.containsKey(factorycontainer.getName()))
					modeldatasetsdirs.put(factorycontainer.getName(), saveto);
			}
		}

        return modeldatasetsdirs;
	}
	
	
	/*private void GenerateExperimentsWithOverlap() {
		
		ArrayList<Pair<Integer, Integer>> overlapprofile=parameters.getOverlapBetweenBiclusters();
		
		if(isSymmetricOverlap(overlapprofile))
		
		
	}*/
	
	
	
	/**
	 * Make datasets with symmetric overlap.
	 *
	 * @throws Exception the exception
	 */
	public void makeDatasetsWithSymmetricOverlap() throws Exception{

		String symmfolder=FilenameUtils.concat(saveindir, "Datasets_with_Symmetric_Overlap");
		String zerooverlap=FilenameUtils.concat(symmfolder, "0x0_overlap");
		MTUDirUtils.makeDirectory(zerooverlap);
		changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: 0x0_overlap");
		GenerateExperiments(zerooverlap,null);
        

		for (int i = 0; i < symmetricoverlap.size(); i++) {

			int dim=symmetricoverlap.get(i);
			if(dim!=0) {
				ArrayList<Pair<Integer, Integer>> overlapprofile=getSymmetricBiclusterProfile(symmetricoverlap.get(i));

				String dirname=String.valueOf(dim)+"x"+String.valueOf(dim)+"_overlap";
				String dirpath=FilenameUtils.concat(symmfolder, dirname);
				MTUDirUtils.makeDirectory(dirpath);
				 changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: "+dirname);
				GenerateExperiments(dirpath, overlapprofile);
			}

		}
	}
	
	
	
	
	/**
	 * Gets the symmetric bicluster profile.
	 *
	 * @param overlapdim the overlapdim
	 * @return the symmetric bicluster profile
	 */
	public ArrayList<Pair<Integer, Integer>> getSymmetricBiclusterProfile(int overlapdim){
		
		ArrayList<Pair<Integer, Integer>> res=new ArrayList<>();
		
		int nbics=parameters.getNumberbiclusters();
		
		for (int i = 0; i < nbics; i++) {
			res.add(new Pair<Integer, Integer>(overlapdim, overlapdim));
		}
		
		return res;
	}
	
	
	
	/**
	 * Make datasets with A symmetric overlap.
	 *
	 * @throws Exception the exception
	 */
	public void makeDatasetsWithASymmetricOverlap() throws Exception{
		
		String asymmfolder=FilenameUtils.concat(saveindir, "Datasets_with_Asymmetric_Overlap");
		String zerooverlap=FilenameUtils.concat(asymmfolder, "0x0_overlap");
		MTUDirUtils.makeDirectory(zerooverlap);
		changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: 0x0_overlap");
		GenerateExperiments(zerooverlap,null);
		
		
		for (int i = 0; i < asymmetricoverlap.size(); i++) {
			
			ArrayList<Pair<Integer, Integer>> setoverlap=asymmetricoverlap.get(i);

			StringBuilder str=new StringBuilder();
			//parameters.resetOverlapListBetweenTwoBiclusters();
			for (int j = 0; j < setoverlap.size(); j++) {
				Pair<Integer, Integer> overlaptwobics=setoverlap.get(j);
				str.append(overlaptwobics.getValue0()+"x"+overlaptwobics.getValue1());
				if(j<setoverlap.size()-1)
					str.append("_and_");
			}
			str.append("_overlaps");
			
			String dirname=str.toString();
			String dirpath=FilenameUtils.concat(asymmfolder, dirname);
			MTUDirUtils.makeDirectory(dirpath);
			changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: "+dirname);
			GenerateExperiments(dirpath, setoverlap);
			
		}
	}
	
	
	/*private boolean isSymmetricOverlap(ArrayList<Pair<Integer, Integer>> overlapprofile) {
		
		int rowvalue=0;
		int colvalue=0;
		
		boolean issym=true;
		for (int i = 0; i < overlapprofile.size(); i++) {
			int currrowvalue=overlapprofile.get(i).getValue0();
			int currcolvalue=overlapprofile.get(i).getValue1();
			if(i>0) {
				
				if(currrowvalue!=rowvalue) {
					issym=false;
					break;
				}
				if(currcolvalue!=colvalue) {
					issym=false;
					break;
				}
			}
			
		    rowvalue=currrowvalue;
		    colvalue=currcolvalue;
		}
		
		return issym;
	}*/
	
	
	/**
	 * Generate experiments with noise.
	 *
	 * @throws Exception the exception
	 */
	private void GenerateExperimentsWithNoise() throws Exception {

		String noisefolder=FilenameUtils.concat(saveindir, "Datasets_with_noise");
		String zeronoisefolder=FilenameUtils.concat(noisefolder, "0_noise");
		MTUDirUtils.makeDirectory(zeronoisefolder);
		changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: 0_noise");
		LinkedHashMap<String, String> modeldatasetsdirs=GenerateExperiments(zeronoisefolder,null);

		for (int i = 0; i < experimentsnoise.size(); i++) {

			String dirnoisename=FilenameUtils.concat(noisefolder, experimentsnoise.get(i)+"_noise");
			MTUDirUtils.makeDirectory(dirnoisename);
			changesupport.firePropertyChange(FIREPROPERTYGSEACHANGETASKSTATUS, null, "Generating experiment: "+experimentsnoise.get(i)+"_noise");

			for (String model : modeldatasetsdirs.keySet()) {

				String dirmodel=modeldatasetsdirs.get(model);

				String outdirmodel=FilenameUtils.concat(dirnoisename, model);
				MTUDirUtils.makeDirectory(outdirmodel);

				for (int j = 0; j < numbersamples; j++) {

					String modeldatasetx=FilenameUtils.concat(dirmodel, "dataset_"+(j+1)+".matrix");
					String expectedfilepath=FilenameUtils.concat(dirmodel, "expected_"+(j+1)+".txt");
					changesupport.firePropertyChange(FIREPROPERTYGSEACHANGESUBTASKSTATUS, null, "Generating dataset "+String.valueOf((j+1))+" for model: "+model);
					ExpressionData dataset=ExpressionData.loadDataset(modeldatasetx, null);

					INDArray noiseadded=addNoiseToData(Nd4j.create(dataset.getexpressionDataMatrix()), experimentsnoise.get(i), dataset.numberGenes(), dataset.numberConditions());
					ExpressionData noisedataset=new ExpressionData(dataset.getGeneNamesList(), dataset.getConditionsList(), ND4JUtils.getDoubleMatrix(noiseadded)).load();
					String outputdir=FilenameUtils.concat(outdirmodel, "dataset_"+(j+1)+".matrix");
					String outputcopyexpected=FilenameUtils.concat(outdirmodel, "expected_"+(j+1)+".txt");
					FileUtils.copyFile(new File(expectedfilepath), new File(outputcopyexpected));
					noisedataset.writeExpressionDatasetToFile(outputdir);

				}
			}

		}
	}
	
	
	/**
	 * Adds the noise to data.
	 *
	 * @param datamatrix the datamatrix
	 * @param datanoise the datanoise
	 * @param nrows the nrows
	 * @param ncols the ncols
	 * @return the IND array
	 */
	public  static INDArray addNoiseToData(INDArray datamatrix, double datanoise, int nrows, int ncols){
		 NormalDistributionDataFactory datadistributionfactory =new NormalDistributionDataFactory();
		 datadistributionfactory.setStandardDeviation(datanoise);
		 INDArray noisematrix=datadistributionfactory.generateDataMatrix(nrows, ncols);
		 INDArray addmatrix=datamatrix.add(noisematrix);
        return addmatrix;
	}
	
	

	/**
	 * Save dataset.
	 *
	 * @param builder the builder
	 * @param datasetnumber the datasetnumber
	 * @param savedir the savedir
	 * @throws Exception the exception
	 */
	private void saveDataset(AbstractSyntheticDataFactory builder, int datasetnumber, String savedir) throws Exception{
		
		builder.makeDataset();
		String datasetname="dataset_"+(datasetnumber+1)+".matrix";
		String expectedbicname="expected_"+(datasetnumber+1)+".biclusters";
		
		String datasetfilepath=FilenameUtils.concat(savedir, datasetname);
		String expectedbicfilepath=FilenameUtils.concat(savedir, expectedbicname);
		
		builder.writeExpressionDatasetToFile(datasetfilepath);
	    builder.writeExpectedBiclustersToFile(expectedbicfilepath);
	}
	
	

	

}
