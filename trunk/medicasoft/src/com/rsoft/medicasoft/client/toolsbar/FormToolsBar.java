package com.rsoft.medicasoft.client.toolsbar;

/**
 * Sencha GXT 3.0.0 - Sencha for GWT Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Window;
import com.rsoft.medicasoft.client.view.xwidget.EasyTextButton;
import com.rsoft.medicasoft.shared.i18n.I18NMessages;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoaderHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * A specialized toolbar that is bound to a {@link PagingLoader} and provides
 * automatic paging controls.
 * 
 * <p />
 * The tool bar is "bound" to the loader using the {@link #bind(PagingLoader)}
 * method.
 */
public class FormToolsBar extends ToolBar {

	public interface FormToolsBarAppearance extends ClientBundle {

		@Source("com/rsoft/medicasoft/client/images/page-first.gif")
		ImageResource first();

		@Source("com/rsoft/medicasoft/client/images/page-prev.gif")
		ImageResource prev();

		@Source("com/rsoft/medicasoft/client/images/page-next.gif")
		ImageResource next();

		@Source("com/rsoft/medicasoft/client/images/page-last.gif")
		ImageResource last();

		@Source("com/rsoft/medicasoft/client/images/clear.png")
		ImageResource refresh();

		@Source("com/rsoft/medicasoft/client/images/refresh.gif")
		ImageResource loading();

		@Source("com/rsoft/medicasoft/client/images/save.png")
		ImageResource save();// Update and insert

		@Source("com/rsoft/medicasoft/client/images/remove.png")
		ImageResource remove();// Delete

		@Source("com/rsoft/medicasoft/client/images/clear.png")
		ImageResource criteria();// For Criteria

		@Source("com/rsoft/medicasoft/client/images/find.png")
		ImageResource search();// search

		@Source("com/rsoft/medicasoft/client/images/upIcon.gif")
		ImageResource load();// load csv data

		@Source("com/rsoft/medicasoft/client/images/downIcon.gif")
		ImageResource extract();// extract csv data

		@Source("com/rsoft/medicasoft/client/images/print.png")
		ImageResource print();// print

		@Source("com/rsoft/medicasoft/client/images/help.png")
		ImageResource showInfo();// showInfo
	}

	public interface FormToolsBarMessages {

		String afterPageText(int page);

		String beforePageText();

		String displayMessage(int start, int end, int total);

		String emptyMessage();

		String firstText();

		String lastText();

		String nextText();

		String prevText();

		String resetText();

		String saveText();

		String removeText();

		String criteriaText();

		String searchText();

		String loadText();

		String extractText();

		String printText();

		//
		String missingToolBarObjectText();

		String before();

		String after();

		String of();

		String ignoreText();

		String helpText();

		String showInfo();

		String errorLoadingFile();
	}

	protected static class DefaultFormToolsBarMessages implements
			FormToolsBarMessages {

		@Override
		public String of() {
			return I18NMessages.getMessages().of();
		}

		@Override
		public String afterPageText(int page) {
			return I18NMessages.getMessages().pagingToolBar_afterPageText(page);
		}

		@Override
		public String beforePageText() {
			return I18NMessages.getMessages().pagingToolBar_beforePageText();
		}

		@Override
		public String displayMessage(int start, int end, int total) {
			return I18NMessages.getMessages().pagingToolBar_displayMsg(start,
					end, total);
		}

		@Override
		public String emptyMessage() {
			return I18NMessages.getMessages().pagingToolBar_emptyMsg();
		}

		@Override
		public String firstText() {
			return I18NMessages.getMessages().pagingToolBar_firstText();
		}

		@Override
		public String lastText() {
			return I18NMessages.getMessages().pagingToolBar_lastText();
		}

		@Override
		public String nextText() {
			return I18NMessages.getMessages().pagingToolBar_nextText();
		}

		@Override
		public String prevText() {
			return I18NMessages.getMessages().pagingToolBar_prevText();
		}

		@Override
		public String resetText() {
			return I18NMessages.getMessages().toolsBar_reset();
		}

		@Override
		public String saveText() {
			return I18NMessages.getMessages().rowEditor_saveText();
		}

		@Override
		public String removeText() {
			return I18NMessages.getMessages().toolsBar_remove();
		}

		@Override
		public String criteriaText() {
			return I18NMessages.getMessages().toolsBar_criteria();
		}

		@Override
		public String searchText() {
			return I18NMessages.getMessages().toolsBar_search();
		}

		@Override
		public String loadText() {
			return I18NMessages.getMessages().toolsBar_loadCsv();
		}

		@Override
		public String extractText() {
			return I18NMessages.getMessages().toolsBar_extractCsv();
		}

		@Override
		public String printText() {
			return I18NMessages.getMessages().toolsBar_print();
		}

		@Override
		public String missingToolBarObjectText() {
			return I18NMessages.getMessages().missing_toolsbarObject();
		}

		@Override
		public String before() {
			return I18NMessages.getMessages().toolsBar_previous();
		}

		@Override
		public String after() {
			return I18NMessages.getMessages().toolsBar_next();
		}

		@Override
		public String ignoreText() {
			return I18NMessages.getMessages().toolsBar_ignore();
		}

		@Override
		public String helpText() {
			return I18NMessages.getMessages().toolsBar_help();
		}

		@Override
		public String showInfo() {
			return I18NMessages.getMessages().toolsBar_showInfo();
		}

		@Override
		public String errorLoadingFile() {
			return I18NMessages.getMessages().errorLoadingFile();
		}

	}

	protected int activePage = -1, pages;
	protected LabelToolItem beforePage, nbRecordText, displayText;
	protected PagingLoadConfig config;
	protected SeparatorToolItem leftSep, rigthSep, navSep, resetSep,
			persistSep, ignoreSep, loadingSep, helpSep;
	protected TextButton first, prev, next, last, reset, save, remove, ignore,
			search, extract, print, help, showInfo;
	protected EasyTextButton load;
	protected PagingLoader<PagingLoadConfig, ?> loader;
	protected NumberField<?> recordText;
	protected boolean savedEnableState = true;
	protected boolean showToolTips = true;
	protected int start, pageSize, totalLength;
	private final FormToolsBarAppearance appearance;
	private boolean loading;

	protected SingleUploader uploader;

	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		@Override
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
				UploadedInfo info = uploader.getServerInfo();
				Window.alert(info.message);
			} else {
				Window.alert(messages.errorLoadingFile());
			}
		}
	};

	private IUploader.OnStartUploaderHandler onnStartUploaderHandler = new IUploader.OnStartUploaderHandler() {
		@Override
		public void onStart(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
			} else {
				uploader.getStatusWidget().setFileName(" ");
			}
		}
	};

	private String paramInoutSuffix;

	public void setLeftSepEnabled(boolean Enabled) {
		leftSep.setEnabled(Enabled);
	}

	public void setRigthSepEnabled(boolean Enabled) {
		rigthSep.setEnabled(Enabled);
	}

	public void setNavSepEnabled(boolean Enabled) {
		navSep.setEnabled(Enabled);
	}

	public void setResetSepEnabled(boolean Enabled) {
		resetSep.setEnabled(Enabled);
	}

	public void setPersistSepEnabled(boolean Enabled) {
		persistSep.setEnabled(Enabled);
	}

	public void setIgnoreSepEnabled(boolean Enabled) {
		ignoreSep.setEnabled(Enabled);
	}

	public void setLoadingSepEnabled(boolean Enabled) {
		loadingSep.setEnabled(Enabled);
	}

	public void setHelpSepEnabled(boolean Enabled) {
		help.setEnabled(Enabled);
	}

	public void setFirstEnabled(boolean Enabled) {
		first.setEnabled(Enabled);
	}

	public void setPrevEnabled(boolean Enabled) {
		prev.setEnabled(Enabled);
	}

	public void setNextEnabled(boolean Enabled) {
		next.setEnabled(Enabled);
	}

	public void setLastEnabled(boolean Enabled) {
		last.setEnabled(Enabled);
	}

	public void setResetEnabled(boolean Enabled) {
		reset.setEnabled(Enabled);
	}

	public void setSaveEnabled(boolean Enabled) {
		save.setEnabled(Enabled);
	}

	public void setRemoveEnabled(boolean Enabled) {
		remove.setEnabled(Enabled);
	}

	public void setIgnoreEnabled(boolean Enabled) {
		ignore.setEnabled(Enabled);
	}

	public void setSearchEnabled(boolean Enabled) {
		search.setEnabled(Enabled);
	}

	public void setLoadEnabled(boolean Enabled) {
		load.setEnabled(Enabled);
	}

	public void setExtractEnabled(boolean Enabled) {
		extract.setEnabled(Enabled);
	}

	public void setPrintEnabled(boolean Enabled) {
		print.setEnabled(Enabled);
	}

	public void setHelpEnabled(boolean Enabled) {
		help.setEnabled(Enabled);
	}

	public void setShowInfo(boolean Enabled) {
		showInfo.setEnabled(Enabled);
	}

	public void setLeftSepVisible(boolean visible) {
		leftSep.setVisible(visible);
	}

	public void setRigthSepVisible(boolean visible) {
		rigthSep.setVisible(visible);
	}

	public void setNavSepVisible(boolean visible) {
		navSep.setVisible(visible);
	}

	public void setResetSepVisible(boolean visible) {
		resetSep.setVisible(visible);
	}

	public void setPersistSepVisible(boolean visible) {
		persistSep.setVisible(visible);
	}

	public void setIgnoreSepVisible(boolean visible) {
		ignoreSep.setVisible(visible);
	}

	public void setLoadingSepVisible(boolean visible) {
		loadingSep.setVisible(visible);
	}

	public void setHelpSepVisible(boolean visible) {
		help.setVisible(visible);
	}

	public void setFirstVisible(boolean visible) {
		first.setVisible(visible);
	}

	public void setPrevVisible(boolean visible) {
		prev.setVisible(visible);
	}

	public void setNextVisible(boolean visible) {
		next.setVisible(visible);
	}

	public void setLastVisible(boolean visible) {
		last.setVisible(visible);
	}

	public void setResetVisible(boolean visible) {
		reset.setVisible(visible);
	}

	public void setSaveVisible(boolean visible) {
		save.setVisible(visible);
	}

	public void setRemoveVisible(boolean visible) {
		remove.setVisible(visible);
	}

	public void setIgnoreVisible(boolean visible) {
		ignore.setVisible(visible);
	}

	public void setSearchVisible(boolean visible) {
		search.setVisible(visible);
	}

	public void setLoadVisible(boolean visible) {
		load.setVisible(visible);
	}

	public void setExtractVisible(boolean visible) {
		extract.setVisible(visible);
	}

	public void setPrintVisible(boolean visible) {
		print.setVisible(visible);
	}

	public void setHelpVisible(boolean visible) {
		help.setVisible(visible);
	}

	private LoaderHandler<PagingLoadConfig, ?> handler = new LoaderHandler<PagingLoadConfig, PagingLoadResult<?>>() {
		@Override
		public void onBeforeLoad(final BeforeLoadEvent<PagingLoadConfig> event) {
			loading = true;
			savedEnableState = isEnabled();
			setEnabled(false);
			reset.setIcon(appearance.loading());
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					if (event.isCancelled()) {
						reset.setIcon(appearance.refresh());
						setEnabled(savedEnableState);
					}
				}
			});
		}

		@Override
		public void onLoad(
				LoadEvent<PagingLoadConfig, PagingLoadResult<?>> event) {
			reset.setIcon(appearance.refresh());
			setEnabled(savedEnableState);
			FormToolsBar.this.onLoad(event);
			loading = false;
		}

		@Override
		public void onLoadException(LoadExceptionEvent<PagingLoadConfig> event) {
			reset.setIcon(appearance.refresh());
			setEnabled(savedEnableState);
			loading = false;
		}
	};
	private HandlerRegistration handlerRegistration;
	private FormToolsBarMessages messages;
	private boolean reuseConfig = true;

	/**
	 * Creates a new paging tool bar.
	 * 
	 * @param pageSize
	 *            the page size
	 */
	public FormToolsBar() {
		this(null, 50);
	}

	/**
	 * Creates a new paging tool bar.
	 * 
	 * @param pageSize
	 *            the page size
	 */
	@UiConstructor
	public FormToolsBar(IToolsBarAction toolsBarAction, int pageSize) {
		this(toolsBarAction, GWT
				.<ToolBarAppearance> create(ToolBarAppearance.class), GWT
				.<FormToolsBarAppearance> create(FormToolsBarAppearance.class),
				pageSize);
	}

	/**
	 * Creates a new tool bar.
	 * 
	 * @param toolBarAppearance
	 *            the tool bar appearance
	 * @param appearance
	 *            the paging tool bar appearance
	 * @param pageSize
	 *            the page size
	 */
	private IToolsBarAction toolsBarAction;

	public FormToolsBar(final IToolsBarAction toolsBarAction,
			ToolBarAppearance toolBarAppearance,
			FormToolsBarAppearance appearance, int pageSize) {
		super(toolBarAppearance);
		this.appearance = appearance;
		// initialize(toolsBarAction);
	}

	public IToolsBarAction getToolsBarAction() {
		return toolsBarAction;
	}

	public void setToolsBarAction(IToolsBarAction toolsBarAction) {
		this.toolsBarAction = toolsBarAction;
	}

	public void initialize() {
		initialize(this.toolsBarAction);
	}

	public void initialize(IToolsBarAction tba) {
		this.toolsBarAction = tba;
		this.messages = getMessages();
		if (tba == null) {
			throw new java.lang.IllegalArgumentException(
					messages.missingToolBarObjectText());
		}
		showInfo = new TextButton();
		showInfo.setIcon(appearance.last());
		showInfo.setToolTip(messages.showInfo());
		showInfo.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.showInfoBanner();
			}
		});

		reset = new TextButton();
		reset.setIcon(appearance.last());
		reset.setToolTip(messages.resetText());
		reset.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.reset();
			}
		});

		first = new TextButton();
		first.setIcon(appearance.first());
		first.setToolTip(messages.firstText());
		first.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.first();
			}
		});

		prev = new TextButton();
		prev.setToolTip(messages.prevText());
		prev.setIcon(appearance.prev());
		prev.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.previous();
			}
		});

		next = new TextButton();
		next.setToolTip(messages.nextText());
		next.setIcon(appearance.next());
		next.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.next();
			}
		});

		last = new TextButton();
		last.setToolTip(messages.lastText());
		last.setIcon(appearance.last());
		last.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.last();
			}
		});

		save = new TextButton();
		save.setToolTip(messages.saveText());
		save.setIcon(appearance.save());
		save.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.persist();
			}
		});

		remove = new TextButton();
		remove.setToolTip(messages.removeText());
		remove.setIcon(appearance.remove());
		remove.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.remove();
			}
		});

		ignore = new TextButton();
		ignore.setToolTip(messages.ignoreText());
		ignore.setIcon(appearance.refresh());
		ignore.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.ignore();
			}
		});

		search = new TextButton();
		search.setToolTip(messages.searchText());
		search.setIcon(appearance.search());
		search.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.search();
			}
		});
		load = new EasyTextButton();
		load.setToolTip(messages.loadText());
		load.setIcon(appearance.refresh());
		load.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.load();
			}
		});

		extract = new TextButton();
		extract.setToolTip(messages.extractText());
		extract.setIcon(appearance.refresh());
		extract.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.extract();
			}
		});

		print = new TextButton();
		print.setToolTip(messages.printText());
		print.setIcon(appearance.print());
		print.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				toolsBarAction.print();
			}
		});

		help = new TextButton();
		help.setIcon(appearance.showInfo());

		uploader = new SingleUploader(FileInputType.CUSTOM.with(load));
		uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		uploader.addOnStartUploadHandler(onnStartUploaderHandler);
		uploader.setServletPath("/medicasoft/uploadServlet?entityName="
				+ paramInoutSuffix);

		uploader.setAutoSubmit(true);
		uploader.avoidRepeatFiles(true);
		beforePage = new LabelToolItem();
		beforePage.setToolTip(messages.before());
		beforePage.setLabel(getMessages().before());

		nbRecordText = new LabelToolItem();
		nbRecordText.setShadow(false);
		nbRecordText.setSize("40", "30");
		nbRecordText.setTabIndex(-1);
		nbRecordText.setToolTip(messages.after());

		recordText = new NumberField<Integer>(new IntegerPropertyEditor());
		recordText.setWidth("30px");
		recordText.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					totalRecords = toolsBarAction.rechordChanged(recordText
							.getText() != null ? Integer.parseInt(recordText
							.getText()) : 0);
					onRecordChanged(
							recordText.getText() != null ? Integer
									.parseInt(recordText.getText()) : 0,
							totalRecords);
				}
			}
		});

		leftSep = new SeparatorToolItem();
		rigthSep = new SeparatorToolItem();
		navSep = new SeparatorToolItem();
		resetSep = new SeparatorToolItem();
		persistSep = new SeparatorToolItem();
		ignoreSep = new SeparatorToolItem();
		loadingSep = new SeparatorToolItem();
		helpSep = new SeparatorToolItem();

		displayText = new LabelToolItem();
		displayText.addStyleName(CommonStyles.get().nowrap());
		add(first);
		add(prev);
		add(leftSep);
		add(recordText);
		add(nbRecordText);
		add(rigthSep);
		add(next);
		add(last);

		add(navSep);
		add(reset);
		// add(displayText);
		add(search);
		add(resetSep);
		add(remove);
		add(save);
		add(persistSep);
		add(ignore);
		add(ignoreSep);
		add(uploader);
		add(extract);
		add(loadingSep);
		add(print);
		add(helpSep);
		add(showInfo);
		add(help);
	}

	@Override
	public void setToolTipConfig(ToolTipConfig config) {
		if (help != null) {
			help.setToolTipConfig(config);
		}
	}

	/**
	 * Binds the toolbar to the loader.
	 * 
	 * @param loader
	 *            the loader
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bind(PagingLoader<? extends PagingLoadConfig, ?> loader) {
		if (this.loader != null) {
			handlerRegistration.removeHandler();
		}
		this.loader = (PagingLoader) loader;
		if (loader != null) {
			loader.setLimit(pageSize);
			// the loader and the handler have the same generics, the cast is
			// required
			// because neither one cares about the data in the load result.
			// Unsure of
			// a better way to express this.
			handlerRegistration = loader
					.addLoaderHandler((LoaderHandler) handler);
		}
	}

	/**
	 * Clears the current toolbar text.
	 */
	@Override
	public void clear() {
		recordText.setText("");
		nbRecordText.setLabel("");
		displayText.setLabel("");
	}

	/**
	 * Moves to the first page.
	 */
	public void first() {
		if (!loading) {
			doLoadRequest(0, pageSize);
		}
	}

	/**
	 * Returns the active page.
	 * 
	 * @return the active page
	 */
	public int getActivePage() {
		return activePage;
	}

	/**
	 * Returns the toolbar appearance.
	 * 
	 * @return the appearance
	 */
	public FormToolsBarAppearance getAppearance() {
		return appearance;
	}

	/**
	 * Returns the toolbar messages.
	 * 
	 * @return the messages
	 */
	public FormToolsBarMessages getMessages() {
		if (messages == null) {
			messages = new DefaultFormToolsBarMessages();
		}
		return messages;
	}

	/**
	 * Returns the current page size.
	 * 
	 * @return the page size
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Returns the total number of pages.
	 * 
	 * @return the
	 */
	public int getTotalPages() {
		return pages;
	}

	/**
	 * Returns true if the previous load config is reused.
	 * 
	 * @return the reuse config state
	 */
	public boolean isReuseConfig() {
		return reuseConfig;
	}

	/**
	 * Returns true if tooltip are enabled.
	 * 
	 * @return the show tooltip state
	 */
	public boolean isShowToolTips() {
		return showToolTips;
	}

	/**
	 * Moves to the last page.
	 */
	public void last() {
		if (!loading) {
			if (totalLength > 0) {
				int extra = totalLength % pageSize;
				int lastStart = extra > 0 ? (totalLength - extra) : totalLength
						- pageSize;
				doLoadRequest(lastStart, pageSize);
			}
		}
	}

	/**
	 * Moves to the last page.
	 */
	public void next() {
		if (!loading) {
			doLoadRequest(start + pageSize, pageSize);
		}
	}

	/**
	 * Moves the the previous page.
	 */
	public void previous() {
		if (!loading) {
			doLoadRequest(Math.max(0, start - pageSize), pageSize);
		}
	}

	/**
	 * Refreshes the data using the current configuration.
	 */
	public void refresh() {
		if (!loading) {
			doLoadRequest(start, pageSize);
		}
	}

	/**
	 * Sets the active page (1 to page count inclusive).
	 * 
	 * @param page
	 *            the page
	 */
	public void setActivePage(int page) {
		if (page > pages) {
			last();
			return;
		}
		if (page != activePage && page > 0 && page <= pages) {
			doLoadRequest(--page * pageSize, pageSize);
		} else {
			recordText.setText(String.valueOf((int) activePage));
		}
	}

	/**
	 * Sets the toolbar messages.
	 * 
	 * @param messages
	 *            the messages
	 */
	public void setMessages(FormToolsBarMessages messages) {
		this.messages = messages;
	}

	/**
	 * Sets the current page size. This method does not effect the data
	 * currently being displayed. The new page size will not be used until the
	 * next load request.
	 * 
	 * @param pageSize
	 *            the new page size
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * True to reuse the previous load config (defaults to true).
	 * 
	 * @param reuseConfig
	 *            true to reuse the load config
	 */
	public void setReuseConfig(boolean reuseConfig) {
		this.reuseConfig = reuseConfig;
	}

	/**
	 * Sets if the button tool tips should be displayed (defaults to true,
	 * pre-render).
	 * 
	 * @param showToolTips
	 *            true to show tool tips
	 */
	public void setShowToolTips(boolean showToolTips) {
		this.showToolTips = showToolTips;
	}

	protected void doLoadRequest(int offset, int limit) {
		if (reuseConfig && config != null) {
			config.setOffset(offset);
			config.setLimit(pageSize);
			loader.load(config);
		} else {
			loader.setLimit(pageSize);
			loader.load(offset, limit);
		}
	}

	protected void onLoad(LoadEvent<PagingLoadConfig, PagingLoadResult<?>> event) {
		config = event.getLoadConfig();
		PagingLoadResult<?> result = event.getLoadResult();
		start = result.getOffset();
		totalLength = result.getTotalLength();
		activePage = (int) Math.ceil((double) (start + pageSize) / pageSize);

		pages = totalLength < pageSize ? 1 : (int) Math
				.ceil((double) totalLength / pageSize);

		if (activePage > pages && totalLength > 0) {
			last();
			return;
		} else if (activePage > pages) {
			start = 0;
			activePage = 1;
		}

		recordText.setText(String.valueOf((int) activePage));

		String display = null;
		// after = getMessages().afterPageText(pages);
		nbRecordText.setLabel("0");

		first.setEnabled(activePage != 1);
		prev.setEnabled(activePage != 1);
		next.setEnabled(activePage != pages);
		last.setEnabled(activePage != pages);

		int temp = activePage == pages ? totalLength : start + pageSize;

		display = getMessages().displayMessage(start + 1, (int) temp,
				totalLength);

		String msg = display;
		if (totalLength == 0) {
			msg = getMessages().emptyMessage();
		}
		displayText.setLabel(msg);

		forceLayout();
	}

	public void onRecordChanged(int record, int total) {
		recordText.setText(String.valueOf(((int) record)));
		nbRecordText.setLabel(getMessages().of() + " " + total);
		totalRecords = record;
	}

	public void setParamInoutSuffix(String paramInoutSuffix) {
		this.paramInoutSuffix = paramInoutSuffix;
	}

	private int totalRecords = 0;
}
