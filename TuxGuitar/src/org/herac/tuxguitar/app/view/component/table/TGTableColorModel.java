package org.herac.tuxguitar.app.view.component.table;

import java.util.HashMap;

import org.herac.tuxguitar.app.system.icons.TGSkinManager;
import org.herac.tuxguitar.app.system.properties.TGPropertiesUIUtil;
import org.herac.tuxguitar.app.ui.TGApplication;
import org.herac.tuxguitar.ui.UIFactory;
import org.herac.tuxguitar.ui.appearance.UIAppearance;
import org.herac.tuxguitar.ui.appearance.UIColorAppearance;
import org.herac.tuxguitar.ui.resource.UIColor;
import org.herac.tuxguitar.ui.resource.UIColorModel;
import org.herac.tuxguitar.util.TGContext;
import org.herac.tuxguitar.util.properties.TGProperties;

public class TGTableColorModel {
	
	public static final int DEFAULT = 0;
	public static final int CARET = 1;
	public static final int HEADER = 2;
	public static final int EVEN_LINE_BACKGROUND = 3;
	public static final int EVEN_LINE_FOREGROUND = 4;
	public static final int ODD_LINE_BACKGROUND = 5;
	public static final int ODD_LINE_FOREGROUND = 6;
	public static final int SELECTED_LINE_BACKGROUND = 7;
	public static final int SELECTED_LINE_FOREGROUND = 8;
	public static final int CELL_BACKGROUND = 9;
	public static final int CELL_REST_MEASURE = 10;
	
	private UIColor[] colors;
	
	public TGTableColorModel() {
		super();
	}
	
	public void resetColors(TGContext context) {
		UIAppearance appearance = TGApplication.getInstance(context).getAppearance();;
		TGProperties properties = TGSkinManager.getInstance(context).getCurrentSkinProperties();
		UIFactory factory = TGApplication.getInstance(context).getFactory();
		UIColorModel[] colorModels = new UIColorModel[11];

		// names of properties which are configurable by skin
		HashMap<Integer, String> colorProperties = new HashMap<Integer, String>();
		colorProperties.put(CARET, "table.caret");
		colorProperties.put(EVEN_LINE_BACKGROUND, "table.even-line.background");
		colorProperties.put(EVEN_LINE_FOREGROUND, "table.even-line.foreground");
		colorProperties.put(ODD_LINE_BACKGROUND, "table.odd-line.background");
		colorProperties.put(ODD_LINE_FOREGROUND, "table.odd-line.foreground");
		colorProperties.put(CELL_BACKGROUND, "table.cell.background");
		colorProperties.put(CELL_REST_MEASURE, "table.cell.rest-measure");
		
		// defaults
		colorModels[DEFAULT] = new UIColorModel(0x00, 0x00, 0x00);	// BLACK
		colorModels[CARET] = new UIColorModel(0x00, 0x00, 0x00);	// BLACK
		colorModels[HEADER] = appearance.getColorModel(UIColorAppearance.WidgetBackground);
		colorModels[EVEN_LINE_BACKGROUND] = appearance.getColorModel(UIColorAppearance.WidgetHighlightBackground);
		colorModels[EVEN_LINE_FOREGROUND] = appearance.getColorModel(UIColorAppearance.WidgetHighlightForeground);
		colorModels[ODD_LINE_BACKGROUND] = appearance.getColorModel(UIColorAppearance.WidgetLightBackground);
		colorModels[ODD_LINE_FOREGROUND] = appearance.getColorModel(UIColorAppearance.WidgetLightForeground);
		colorModels[SELECTED_LINE_BACKGROUND] = appearance.getColorModel(UIColorAppearance.WidgetSelectedBackground);
		colorModels[SELECTED_LINE_FOREGROUND] = appearance.getColorModel(UIColorAppearance.WidgetSelectedForeground);
		colorModels[CELL_BACKGROUND] = appearance.getColorModel(UIColorAppearance.WidgetLightBackground);
		colorModels[CELL_REST_MEASURE] = appearance.getColorModel(UIColorAppearance.WidgetSelectedBackground);
		
		// (re)define colors (override defaults by user parameter if relevant)
		if (this.colors!=null) {
			for (int i=0; i<this.colors.length; i++) {
				if (this.colors[i]!=null) {
					this.colors[i].dispose();
				}
			}
		}
		this.colors = new UIColor[colorModels.length];
		for (int i=0; i<colorModels.length; i++) {
			if (colorProperties.get(i)==null) {
				this.colors[i] = factory.createColor(colorModels[i]);
			} else {
				this.colors[i] = factory.createColor(TGPropertiesUIUtil.getColorModelValue(context, properties, colorProperties.get(i), colorModels[i]));
			}
		}
	}
	
	public UIColor getColor(int colorIndex) {
		if (colorIndex < 0 || colorIndex >= this.colors.length) return (this.colors[DEFAULT]);
		return(this.colors[colorIndex]);
	}
}
