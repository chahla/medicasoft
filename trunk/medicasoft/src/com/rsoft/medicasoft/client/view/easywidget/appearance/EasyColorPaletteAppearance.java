package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.colorpalette.ColorPaletteBaseAppearance;

public class EasyColorPaletteAppearance extends ColorPaletteBaseAppearance {

	public interface GrayColorPaletteResources extends
			ColorPaletteBaseAppearance.ColorPaletteResources, ClientBundle {

		@Source({
				"com/sencha/gxt/theme/base/client/colorpalette/ColorPalette.css",
				"EasyColorPalette.css" })
		GrayColorPaletteStyle style();

	}

	public interface GrayColorPaletteStyle extends ColorPaletteStyle {
	}

	public EasyColorPaletteAppearance() {
		this(
				GWT.<GrayColorPaletteResources> create(GrayColorPaletteResources.class),
				GWT.<BaseColorPaletteTemplate> create(BaseColorPaletteTemplate.class));
	}

	public EasyColorPaletteAppearance(GrayColorPaletteResources resources,
			BaseColorPaletteTemplate template) {
		super(resources, template);
	}

}
