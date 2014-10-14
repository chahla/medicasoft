package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.resizable.ResizableBaseAppearance;

public class EasyResizableAppearance extends ResizableBaseAppearance {

  public interface GrayResizableResources extends ResizableResources, ClientBundle {

    ImageResource handleEast();

    ImageResource handleNortheast();

    ImageResource handleNorthwest();

    ImageResource handleSouth();

    ImageResource handleSoutheast();

    ImageResource handleSouthwest();

    @Source({"com/sencha/gxt/theme/base/client/resizable/Resizable.css", "EasyResizable.css"})
    GrayResizableStyle style();

  }

  public interface GrayResizableStyle extends ResizableStyle {
  }

  public EasyResizableAppearance() {
    this(GWT.<GrayResizableResources> create(GrayResizableResources.class));
  }

  public EasyResizableAppearance(ResizableResources resources) {
    super(resources);
  }

}
