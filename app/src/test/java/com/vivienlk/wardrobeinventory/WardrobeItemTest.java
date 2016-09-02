package com.vivienlk.wardrobeinventory;

import android.content.Context;

import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import java.util.UUID;

/**
 * Created by Vivien on 8/31/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class WardrobeItemTest {
    @Mock
    Context mContext;

    @Test
    public void canInstantiateWardrobeItem() {
        WardrobeItem item = new WardrobeItem(mContext, UUID.randomUUID(), "Tee", "8/12/2016",
                "Blue, Green", "Cotton", "Casual", "Fall", "5", "short", 21, "Forever21");
        assertThat(item, instanceOf(WardrobeItem.class));
    }


}
