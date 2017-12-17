/**
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 /**
  * Modifications by ornrocha
  */
package jbiclustgegui.gui.components.panels.chart;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CSVExporter;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.chartpart.ToolTips;




/**
 * A Swing JPanel that contains a Chart
 * <p>
 * Right-click + Save As... or ctrl+S pops up a Save As dialog box for saving the chart as PNG, JPEG, etc. file.
 *
 * @author timmolter
 */
public class XChartPanelJBiclustGE<T extends Chart> extends JPanel {


	private T chart=null;
	private Dimension preferredSize=null;
	private String saveAsString = "Save As...";
	private String exportAsString = "Export To...";

	public XChartPanelJBiclustGE() {
		super();

		// Right-click listener for saving chart
		this.addMouseListener(new PopUpMenuClickListener());

		// Control+S key listener for saving chart
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(ctrlS, "save");
		this.getActionMap().put("save", new SaveAction());

		// Control+E key listener for saving chart
		/*KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(ctrlE, "export");*/
		// this.getActionMap().put("export", new ExportAction());
	}


	public XChartPanelJBiclustGE(final T chart) {
		addChart(chart);
	}

	public void addChart(T chart) {
		this.chart = chart;
		preferredSize = new Dimension(chart.getWidth(), chart.getHeight());

		// Mouse motion listener for data label popup
		ToolTips toolTips = chart.getToolTips();
		if (toolTips != null) {
			MouseMotionListener mml = toolTips.getMouseMotionListener();
			if (mml != null) {
				this.addMouseMotionListener(mml);
			}
		}
		
		updateUI();
	}


	public T getChart() {
		return this.chart;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if(chart!=null) {
			Graphics2D g2d = (Graphics2D) g.create();
			chart.paint(g2d, getWidth(), getHeight());
			g2d.dispose();
		}
	}


	private class SaveAction extends AbstractAction {

		public SaveAction() {

			super("save");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			showSaveAsDialog();
		}
	}

	public void showSaveAsDialog() {

		UIManager.put("FileChooser.saveButtonText", "Save");
		UIManager.put("FileChooser.fileNameLabelText", "File Name:");
		JFileChooser fileChooser = new JFileChooser();
		FileFilter pngFileFilter = new SuffixSaveFilter("png"); // default
		fileChooser.addChoosableFileFilter(pngFileFilter);
		fileChooser.addChoosableFileFilter(new SuffixSaveFilter("jpg"));
		fileChooser.addChoosableFileFilter(new SuffixSaveFilter("bmp"));
		fileChooser.addChoosableFileFilter(new SuffixSaveFilter("gif"));

		// VectorGraphics2D is optional, so if it's on the classpath, allow saving charts as vector graphic
		try {
			Class.forName("de.erichseifert.vectorgraphics2d.VectorGraphics2D");
			// it exists on the classpath
			fileChooser.addChoosableFileFilter(new SuffixSaveFilter("svg"));
			fileChooser.addChoosableFileFilter(new SuffixSaveFilter("eps"));
			fileChooser.addChoosableFileFilter(new SuffixSaveFilter("pdf"));
		} catch (ClassNotFoundException e) {
			// it does not exist on the classpath
		}

		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.setFileFilter(pngFileFilter);

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

			if (fileChooser.getSelectedFile() != null) {
				File theFileToSave = fileChooser.getSelectedFile();
				try {
					if (fileChooser.getFileFilter() == null) {
						BitmapEncoder.saveBitmap(chart, theFileToSave.getCanonicalPath(), BitmapFormat.PNG);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.jpg,*.JPG")) {
						BitmapEncoder.saveJPGWithQuality(chart, BitmapEncoder.addFileExtension(theFileToSave.getCanonicalPath(), BitmapFormat.JPG), 1.0f);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.png,*.PNG")) {
						BitmapEncoder.saveBitmap(chart, theFileToSave.getCanonicalPath(), BitmapFormat.PNG);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.bmp,*.BMP")) {
						BitmapEncoder.saveBitmap(chart, theFileToSave.getCanonicalPath(), BitmapFormat.BMP);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.gif,*.GIF")) {
						BitmapEncoder.saveBitmap(chart, theFileToSave.getCanonicalPath(), BitmapFormat.GIF);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.svg,*.SVG")) {
						VectorGraphicsEncoder.saveVectorGraphic(chart, theFileToSave.getCanonicalPath(), VectorGraphicsFormat.SVG);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.eps,*.EPS")) {
						VectorGraphicsEncoder.saveVectorGraphic(chart, theFileToSave.getCanonicalPath(), VectorGraphicsFormat.EPS);
					} else if (fileChooser.getFileFilter().getDescription().equals("*.pdf,*.PDF")) {
						VectorGraphicsEncoder.saveVectorGraphic(chart, theFileToSave.getCanonicalPath(), VectorGraphicsFormat.PDF);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class SuffixSaveFilter extends FileFilter {

		private final String suffix;

		/**
		 * @param suffix This file filter accepts all files that end with .suffix or the capitalized suffix.
		 */
		public SuffixSaveFilter(String suffix) {

			this.suffix = suffix;
		}

		@Override
		public boolean accept(File f) {

			if (f.isDirectory()) {
				return true;
			}

			String s = f.getName();

			return s.endsWith("." + suffix) || s.endsWith("." + suffix.toUpperCase());
		}

		@Override
		public String getDescription() {

			return "*." + suffix + ",*." + suffix.toUpperCase();
		}
	}

	private class PopUpMenuClickListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			if (e.isPopupTrigger()) {
				doPop(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			if (e.isPopupTrigger()) {
				doPop(e);
			}
		}

		private void doPop(MouseEvent e) {

			XChartPanelPopupMenu menu = new XChartPanelPopupMenu();
			menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	private class XChartPanelPopupMenu extends JPopupMenu {

		final JMenuItem saveAsMenuItem;
		JMenuItem exportAsMenuItem;

		public XChartPanelPopupMenu() {

			saveAsMenuItem = new JMenuItem(saveAsString);
			saveAsMenuItem.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

					showSaveAsDialog();
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {

				}
			});
			add(saveAsMenuItem);

			if (chart instanceof XYChart) {
				exportAsMenuItem = new JMenuItem(exportAsString);
				exportAsMenuItem.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {

						//showExportAsDialog();
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseClicked(MouseEvent e) {

					}
				});
				add(exportAsMenuItem);
			}
		}
	}


}
