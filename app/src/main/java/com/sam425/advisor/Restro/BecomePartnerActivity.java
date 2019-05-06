package com.sam425.advisor.Restro;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.sam425.advisor.R;
import com.sam425.advisor.Restro.NonSwipeableViewPager;
import com.sam425.advisor.Restro.BePartnerStepOneFragment;
import com.sam425.advisor.Restro.BePartnerStepThreeFragment;
import com.sam425.advisor.Restro.BePartnerStepFourFragment;


public class BecomePartnerActivity extends AppCompatActivity implements BePartnerStepOneFragment.OnStepOneListener, BePartnerStepTwoFragment.OnStepTwoListener, BePartnerStepFourFragment.OnStepFourListener,BePartnerStepFiveFragment.OnStepFiveListener, BePartnerStepSixFragment.OnStepSixListener, BePartnerStepSevenFragment.OnStepSevenListener,BePartnerStepEightFragment.OnStepEightListener, BePartnerStepThreeFragment.OnStepThreeListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;


    private NonSwipeableViewPager mViewPager;

    private StepperIndicator stepperIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_partner);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        stepperIndicator = findViewById(R.id.stepperIndicator);


        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);
        // or keep last page as "end page"
        stepperIndicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount() - 1); //

        /*// or manual change
        indicator.setStepCount(3);
        indicator.setCurrentStep(2);
*/

    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_become_partner;
    }



    public static class SectionsPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return BePartnerStepOneFragment.newInstance("", "");
                case 1:
                    return BePartnerStepTwoFragment.newInstance("", "");
                case 2:
                    return BePartnerStepFourFragment.newInstance("", "");
                case 3:
                    return BePartnerStepFiveFragment.newInstance("", "");
                case 4:
                    return BePartnerStepSixFragment.newInstance("", "");
                case 5:
                    return BePartnerStepSevenFragment.newInstance("", "");
                case 6:
                    return BePartnerStepEightFragment.newInstance("", "");
                case 7:
                    return BePartnerStepThreeFragment.newInstance("", "");
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Third Level";
                case 3:
                    return "Fourth Level";
                case 4:
                    return "Fifth Level";
                case 5:
                    return "Sixth Level";
                case 6:
                    return "Seven Level";

                case 7:
                    return "Finish";
            }
            return null;
        }
    }


    @Override
    public void onNextPressed(Fragment fragment) {
        if (fragment instanceof BePartnerStepOneFragment) {
            mViewPager.setCurrentItem(1, true);
        } else if (fragment instanceof BePartnerStepTwoFragment) {
            mViewPager.setCurrentItem(2, true);
        } else if (fragment instanceof BePartnerStepFourFragment) {
            mViewPager.setCurrentItem(3, true);
        } else if (fragment instanceof BePartnerStepFiveFragment) {
                    mViewPager.setCurrentItem(4, true);
        } else if (fragment instanceof BePartnerStepSixFragment) {
                    mViewPager.setCurrentItem(5, true);
        }else if (fragment instanceof BePartnerStepSevenFragment) {
                        mViewPager.setCurrentItem(6, true);
                    } else if (fragment instanceof BePartnerStepEightFragment) {
                        mViewPager.setCurrentItem(7, true);
        } else if (fragment instanceof BePartnerStepThreeFragment) {
            Toast.makeText(this, "Thanks For Registering", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onBackPressed(Fragment fragment){
                if (fragment instanceof BePartnerStepTwoFragment) {
                    mViewPager.setCurrentItem(0, true);
                } else if (fragment instanceof BePartnerStepFourFragment) {
                    mViewPager.setCurrentItem(3, true);
                }if (fragment instanceof BePartnerStepFiveFragment) {
                        mViewPager.setCurrentItem(4, true);
                    } else if (fragment instanceof BePartnerStepSixFragment) {
                        mViewPager.setCurrentItem(5, true);
                    }if (fragment instanceof BePartnerStepSevenFragment) {
                            mViewPager.setCurrentItem(6, true);
                        } else if (fragment instanceof BePartnerStepEightFragment) {
                            mViewPager.setCurrentItem(7, true);
                        } else if (fragment instanceof BePartnerStepThreeFragment) {
                            mViewPager.setCurrentItem(1, true);
                        }
                    }
                }
