package com.d.refreshlayout.module.lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonAdapter for ListView
 * Created by D on 2017/4/25.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLayoutId;
    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mInflater = LayoutInflater.from(mContext);
        mLayoutId = layoutId;
    }

    public CommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mInflater = LayoutInflater.from(mContext);
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    public void setDatas(List<T> datas) {
        if (mDatas != null && datas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiItemTypeSupport != null) {
            multiItemTypeSupport.getItemViewType(position, position < mDatas.size() ? mDatas.get(position) : null);
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (multiItemTypeSupport != null) {
            return multiItemTypeSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas == null ? null : mDatas.size() == 0 ? null : mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonHolder holder = getViewHolder(position, convertView, parent);
        convert(position, holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * @param position Position
     * @param holder   Holder
     * @param item     Position?????????????????????Item
     */
    public abstract void convert(int position, CommonHolder holder, T item);

    private CommonHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        if (multiItemTypeSupport != null) {
            if (mDatas != null && mDatas.size() > 0) {
                return CommonHolder.get(mContext, convertView, parent,
                        multiItemTypeSupport.getLayoutId(position, mDatas.get(position)), position);
            }
            return CommonHolder.get(mContext, convertView, parent,
                    multiItemTypeSupport.getLayoutId(position, null), position);
        }
        return CommonHolder.get(mContext, convertView, parent, mLayoutId, position);
    }
}
