package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame.NestedDivFrameStyle;
import com.sencha.gxt.theme.base.client.panel.FramedPanelBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class EasyFramedPanelAppearance extends FramedPanelBaseAppearance {

  public interface FramedPanelStyle extends ContentPanelStyle {

  }

  public interface GrayFramePanelResources extends ContentPanelResources {
    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.css", "EasyFramedPanel.css"})
    @Override
    FramedPanelStyle style();
  }

  public interface GrayFramePanelNestedDivFrameStyle extends NestedDivFrameStyle {

  }

  public interface GrayFramedPanelDivFrameResources extends FramedPanelDivFrameResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/frame/NestedDivFrame.css", "EasyFramedPanelDivFrame.css"})
    @Override
    GrayFramePanelNestedDivFrameStyle style();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource background();

    @Override
    ImageResource topLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Override
    ImageResource topBorder();

    @Override
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource topRightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Override
    ImageResource leftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource rightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource bottomLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Override
    ImageResource bottomBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource bottomRightBorder();

  }

  public EasyFramedPanelAppearance() {
    this(GWT.<GrayFramePanelResources> create(GrayFramePanelResources.class));
  }

  public EasyFramedPanelAppearance(GrayFramePanelResources resources) {
    super(resources, GWT.<FramedPanelTemplate> create(FramedPanelTemplate.class), new NestedDivFrame(
        GWT.<FramedPanelDivFrameResources> create(GrayFramedPanelDivFrameResources.class)));
  }

  @Override
  public HeaderDefaultAppearance getHeaderAppearance() {
    return new EasyHeaderFramedAppearance();
  }

}
