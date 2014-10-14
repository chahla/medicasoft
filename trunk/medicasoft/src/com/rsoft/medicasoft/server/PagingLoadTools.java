package com.rsoft.medicasoft.server;
import java.util.ArrayList;
import java.util.List;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class PagingLoadTools<T> {
	private List<T> datas;
	private PagingLoadConfig loadConfig;

	public PagingLoadTools(PagingLoadConfig loadConfig, List<T> datas) {
		this.datas = datas;
		this.loadConfig = loadConfig;
	}

	public PagingLoadResult<T> getNextPage() {
		new ObjectSorter<T>().manageSortInfo(loadConfig, (ArrayList<T>)datas);
		int start = loadConfig.getOffset();
		int limit = datas.size();
		if (loadConfig.getLimit() > 0) {
			limit = Math.min(start + loadConfig.getLimit(), limit);
		}
		return new PagingLoadResultBean<T>(datas, datas.size(),
				loadConfig.getOffset());
	}
}
