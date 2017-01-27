package com.topjohnwu.magisk;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topjohnwu.magisk.adapters.TabFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LogFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.container) ViewPager viewPager;
    @BindView(R.id.tab) TabLayout tab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log, container, false);
        unbinder = ButterKnife.bind(this, v);

        TabFragmentAdapter adapter = new TabFragmentAdapter(getChildFragmentManager());

        adapter.addTab(new MagiskLogFragment(), getString(R.string.magisk));

        if (Global.Info.isSuClient) {
            adapter.addTab(new SuLogFragment(), getString(R.string.superuser));
            tab.setupWithViewPager(viewPager);
            tab.setVisibility(View.VISIBLE);
        }

        viewPager.setAdapter(adapter);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
