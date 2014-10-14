package com.rsoft.medicasoft.shared.menu;
import com.sencha.gxt.data.shared.ModelKeyProvider;
public class MenuModel {
	public static ModelKeyProvider<MenuModel> KP = new ModelKeyProvider<MenuModel>() {
		@Override
		public String getKey(MenuModel item) {
			return item.getName();
		}
	};

	private String name;
	private String image;

	public String getImage() {
		return image;
	}

	public MenuModel(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}