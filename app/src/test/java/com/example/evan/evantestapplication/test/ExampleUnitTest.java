package com.example.evan.evantestapplication.test;

import android.content.Context;
import android.media.audiofx.EnvironmentalReverb;

import com.example.evan.evantestapplication.BuildConfig;
import com.example.evan.evantestapplication.R;
import com.google.protobuf.DescriptorProtos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.internal.bytecode.RobolectricInternals;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, packageName = "com.example.evan.evantestapplication")
public class ExampleUnitTest {
    private Context mContext;
    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
    }
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println(mContext.getResources().getString(R.string.test_format, 8));
        assertEquals(4, 2 + 2);

    }
}