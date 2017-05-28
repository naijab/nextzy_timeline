package com.naijab.nextzytimeline;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.utility.StringUtility;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Xiltron on 28/5/2560.
 */
@RunWith(AndroidJUnit4.class)
public class StringUtilityTest {

    Context context;

    @Before
    public void initContext(){
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void getString(){
        String success = StringUtility.getSaveSuccess(context);
        String successError = StringUtility.getSaveError(context);
        String delete = StringUtility.getDeleteSuccess(context);
        String deleteError = StringUtility.getDeleteError(context);
        Assert.assertNotNull(success);
        Assert.assertNotNull(successError);
        Assert.assertNotNull(delete);
        Assert.assertNotNull(deleteError);
    }
}
