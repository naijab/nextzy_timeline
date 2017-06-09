package com.naijab.nextzytimeline;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.naijab.nextzytimeline.manager.PeopleModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Xiltron on 28/5/2560.
 */
@RunWith(AndroidJUnit4.class)
public class AddPeopleTest {

    AddPeopleFragmentInterface.View mockView;
    AddPeopleFragmentPresenter presenter;
    Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
        presenter = new AddPeopleFragmentPresenter();
        presenter.attachView(mockView);
    }

    @Test
    public void testAddPeople() {
        PeopleModel people = new PeopleModel();
        people.setName("Hello");
        people.setJob("Job");
        people.setBirthDay("16-1-2555");
        people.setJobStart("16-1-2555");
        people.setJobDescription("Job Des");
        people.setGame("Game");
        people.setSmartPhone("iPhone");
        people.setProfile("file://...");
        people.setPhoto("file://...");
        Assert.assertNotNull(people);
    }
}
