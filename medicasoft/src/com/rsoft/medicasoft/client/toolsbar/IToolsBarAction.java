package com.rsoft.medicasoft.client.toolsbar;

public interface IToolsBarAction {

    public void persist();//Save,update,remove

    public void first();

    public void last();

    public void next();

    public void previous();

    public void print();

    public void load();

    public void extract();

    public void criteria();

    public void search();

    public void remove();

    public void ignore();

    public int rechordChanged(int record);

    public void reset();

    public void help();

    public void showInfoBanner();

    public void setBtnSaveVisible(boolean aFlag);

	boolean stopCurrentAction();
}
