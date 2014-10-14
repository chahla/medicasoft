 
package com.rsoft.medicasoft.client.view.easywidget.appearance;

import static com.google.gwt.resources.client.ImageResource.RepeatStyle.Both;
import static com.google.gwt.resources.client.ImageResource.RepeatStyle.Horizontal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.sencha.gxt.theme.base.client.tabs.TabPanelBaseAppearance;

public class EasyTabPanelAppearance extends TabPanelBaseAppearance {

  public interface EasyTabPanelResources extends TabPanelResources, ClientBundle {

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource bottomInactiveLeftBackground();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource bottomInactiveRightBackground();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource bottomLeftBackground();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource bottomRightBackground();

    ImageResource scrollerLeft();

    ImageResource scrollerLeftOver();

    ImageResource scrollerRight();

    ImageResource scrollerRightOver();

    @Source({"com/sencha/gxt/theme/base/client/tabs/TabPanel.css", "EasyTabPanel.css"})
    EasyTabPanelStyle style();

    @ImageOptions(repeatStyle = Horizontal)
    ImageResource tabCenter();

    @ImageOptions(repeatStyle = Horizontal)
    ImageResource tabCenterActive();

    @ImageOptions(repeatStyle = Horizontal)
    ImageResource tabCenterOver();

    ImageResource tabClose();

    ImageResource tabLeft();

    ImageResource tabLeftActive();

    ImageResource tabLeftOver();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource tabRight();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource tabRightActive();

    // Prevent sprite sheet inclusion to allow background positioning in IE 6-7
    @ImageOptions(repeatStyle = Both)
    ImageResource tabRightOver();

    @ImageOptions(repeatStyle = Horizontal)
    ImageResource tabStripBackground();

    @ImageOptions(repeatStyle = Horizontal)
    ImageResource tabStripBottomBackground();

  }

  public interface EasyTabPanelStyle extends TabPanelStyle {
  }

  public EasyTabPanelAppearance() {
    this(GWT.<EasyTabPanelResources> create(EasyTabPanelResources.class), GWT.<Template> create(Template.class),
        GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public EasyTabPanelAppearance(EasyTabPanelResources resources, Template template, ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);
  }

}
