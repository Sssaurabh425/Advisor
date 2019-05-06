package com.sam425.advisor.Restro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class mSectionsPagerAdapter extends FragmentPagerAdapter {
    public mSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BePartnerStepOneFragment bo=new BePartnerStepOneFragment();
                return bo;
            case 1:
                BePartnerStepTwoFragment bt=new BePartnerStepTwoFragment();
                return bt;
            case 2:
                BePartnerStepFourFragment nt=new BePartnerStepFourFragment();
                return nt;
            case 3:
                BePartnerStepFiveFragment qt=new BePartnerStepFiveFragment();
                return qt;
            case 4:
                BePartnerStepSixFragment rt=new BePartnerStepSixFragment();
                return rt;
            case 5:
                BePartnerStepSevenFragment at=new BePartnerStepSevenFragment();
                return at;
            case 6:
                BePartnerStepEightFragment zt=new BePartnerStepEightFragment();
                return zt;
            case 7:
                BePartnerStepThreeFragment xt=new BePartnerStepThreeFragment();
                return xt;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 8;
    }
}