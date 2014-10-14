package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel.PlainTabPanelAppearance;

/**
 * A Easy-colored appearance for {@link PlainTabPanel}. This appearance differs
 * from {@link EasyTabPanelAppearance} in that it has a simplified tab strip.
 */
public class EasyPlainTabPanelAppearance extends EasyTabPanelAppearance
		implements PlainTabPanelAppearance {

	public interface EasyPlainTabPanelResources extends EasyTabPanelResources {

		@Source({ "com/sencha/gxt/theme/base/client/tabs/TabPanel.css",
				"EasyTabPanel.css",
				"com/sencha/gxt/theme/base/client/tabs/PlainTabPanel.css",
				"EasyPlainTabPanel.css" })
		EasyPlainTabPanelStyle style();

	}

	public interface EasyPlainTabPanelStyle extends EasyTabPanelStyle {

		String tabStripSpacer();

	}

	public interface PlainTabPanelTemplates extends Template {

		@XTemplate(source = "com/sencha/gxt/theme/base/client/tabs/TabPanel.html")
		SafeHtml render(TabPanelStyle style);

		@XTemplate(source = "com/sencha/gxt/theme/base/client/tabs/PlainTabPanel.html")
		SafeHtml renderPlain(EasyPlainTabPanelStyle style);

	}

	private final PlainTabPanelTemplates template;
	private final EasyPlainTabPanelStyle style;

	public EasyPlainTabPanelAppearance() {
		this(
				GWT.<EasyPlainTabPanelResources> create(EasyPlainTabPanelResources.class),
				GWT.<PlainTabPanelTemplates> create(PlainTabPanelTemplates.class),
				GWT.<ItemTemplate> create(ItemTemplate.class));
	}

	public EasyPlainTabPanelAppearance(EasyPlainTabPanelResources resources,
			PlainTabPanelTemplates template, ItemTemplate itemTemplate) {
		super(resources, template, itemTemplate);
		this.style = resources.style();
		this.template = template;
	}

	@Override
	public void render(SafeHtmlBuilder builder) {
		builder.append(template.renderPlain(style));
	}

}
