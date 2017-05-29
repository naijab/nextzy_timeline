package com.naijab.nextzytimeline;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.naijab.nextzytimeline.utility.StringUtility;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StringUtilityTest {

    @Before
    public void initContext(){
    }

    @Test
    public void getString(){
        String success = StringUtility.getSaveSuccess();
        String successError = StringUtility.getSaveError();
        String delete = StringUtility.getDeleteSuccess();
        String deleteError = StringUtility.getDeleteError();
        Assert.assertNotNull(success);
        Assert.assertNotNull(successError);
        Assert.assertNotNull(delete);
        Assert.assertNotNull(deleteError);
    }
}
